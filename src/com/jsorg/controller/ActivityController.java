package com.jsorg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jsorg.pojo.Activity;
import com.jsorg.pojo.EventsGallery;
import com.jsorg.pojo.User;
import com.jsorg.service.ActivityService;
import com.jsorg.service.EventsGalleryService;
import com.jsorg.util.RedisUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	@Autowired
	ActivityService activityService;
	@Autowired
	EventsGalleryService eventsGalleryService;
	@Autowired
	RedisUtil redisUtil;
	
	@ResponseBody
	@RequestMapping("initActivity")
	public Object initActivity(@RequestBody Map map,HttpServletRequest request) {
		String activityId = UUID.randomUUID().toString();
		String activityName = (String) map.get("activityName");
		String deadlinedate = (String) map.get("deadlinedate");
		String deadlinetime = (String) map.get("deadlinetime");
		String startingtime = (String) map.get("startingtime");
		String startingdate = (String) map.get("startingdate");
		String place = (String) map.get("place");
		String introduction = (String) map.get("introduction");
		Boolean isteam = false;
		
		String addtime = DateUtil.now();
		String starting = startingdate.substring(0, 10) + " " + startingtime;
		String deadline = deadlinedate.substring(0, 10) + " " + deadlinetime;
		String sponsor_id = "";
		User user = (User) redisUtil.get("user");
		if (user != null) {
			sponsor_id = user.getStudent_id();
		}
		Console.log(starting);
		Console.log(deadline);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			activityService.add(activityId, activityName, sponsor_id, addtime, deadline, 
					starting, place, introduction, isteam);
			addtime = addtime.substring(0, 10);
			eventsGalleryService.addEvents(activityId, activityName, addtime);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		
		Object jsonObject = JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("getactivitylist")
	public JSONObject getactivitylist(HttpServletRequest request) {
		String student_id = "221500409";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Activity> participatelist = new ArrayList<Activity>();
		resultMap.put("status", "success");
		try {
			for (Activity activity : activityService.getacticitylist(1)) {
				if (activity.getList().indexOf(student_id) != -1) {
					participatelist.add(activity);
				}
			}
			resultMap.put("in_registering", participatelist);
			participatelist = new ArrayList<Activity>();
			for (Activity activity : activityService.getacticitylist(2)) {
				if (activity.getList().indexOf(student_id) != -1) {
					participatelist.add(activity);
				}
			}
			resultMap.put("in_about_to_start", participatelist);
			participatelist = new ArrayList<Activity>();
			for (Activity activity : activityService.getacticitylist(3)) {
				if (activity.getList().indexOf(student_id) != -1) {
					participatelist.add(activity);
				}
			}
			resultMap.put("in_over", participatelist);
			resultMap.put("registering", activityService.getacticitylist(1));
			resultMap.put("nostarted", activityService.getacticitylist(2));
			resultMap.put("over", activityService.getacticitylist(3));
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("signup")
	public Object signup(@RequestBody Map map,HttpServletRequest request) {
		String activityId = (String) map.get("activityId");
		String student_id = "221500409";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			String list = activityService.getsignuplist(activityId);
			list = list + "."+student_id;
			activityService.updatesignuplist(activityId, list);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("dropout")
	public Object dropout(@RequestBody Map map,HttpServletRequest request) {
		String activityId = (String) map.get("activityId");
		String student_id = "221500409";
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			String list = activityService.getsignuplist(activityId);
			list = list.replace("."+student_id, "");
			activityService.updatesignuplist(activityId, list);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("participatedProjectsList")
	public Object participatedProjectsList(HttpServletRequest request) {
		String student_id = "221500409";
		String list = "";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			for (Activity activity : activityService.getacticitylist(1)) {
				if (activity.getList().indexOf(student_id) != -1) {
					list = list+"."+activity.getActivityId();
				}
			}
			resultMap.put("list", list);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("adjustActivity")
	public Object adjustActivity(HttpServletRequest request) {
		String datanow = DateUtil.now();
		datanow = datanow.substring(0, 16);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			for (Activity activity : activityService.getacticitylist(1)) {
				if (datanow.compareTo(activity.getDeadline()) > 0) {
					activityService.updatestatus(activity.getActivityId(), 2);
				}
			}
			for (Activity activity : activityService.getacticitylist(2)) {
				if (datanow.compareTo(activity.getStartingtime()) > 0) {
					activityService.updatestatus(activity.getActivityId(), 3);
				}
			}
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("getActivityShow")
	public Object getActivityShow(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			List<EventsGallery> list = eventsGalleryService.getAllEvents(true);
			for (EventsGallery eventsGallery : list) {
				String time = eventsGallery.getTime();
				Console.log(time);
//				time = time.substring(5, 10);
				eventsGallery.setTime(time);
			}
			resultMap.put("activityList", list);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("getActivityShow_nowrite")
	public Object getActivityShow_nowrite(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			resultMap.put("activity_nowrite", eventsGalleryService.getAllEvents(false));
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("Addeventoverview")
	public Object Addeventoverview(HttpServletRequest request) {
		return 0;
	}
}
