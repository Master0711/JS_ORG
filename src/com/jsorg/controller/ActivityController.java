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
import com.jsorg.service.ActivityService;
import com.jsorg.service.EventsGalleryService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	@Autowired
	ActivityService activityService;
	@Autowired
	EventsGalleryService eventsGalleryService;
	
	@ResponseBody
	@RequestMapping("initActivity")
	public Object initActivity(@RequestBody Map map,HttpServletRequest request) {
		String activityId = UUID.randomUUID().toString();
		String activityName = (String) map.get("activityName");
		String deadline = (String) map.get("deadline");
		String startingtime = (String) map.get("startingtime");
		String place = (String) map.get("place");
		String introduction = (String) map.get("introduction");
		Boolean isteam = true;
		if ((Boolean) map.get("isteam").equals("false")) {
			isteam = false;
		}
		Console.log(startingtime);
		Console.log(deadline);
		String sponsor_id = "221500410";
		String addtime = DateUtil.now();
		startingtime = startingtime.substring(5, 16);
		startingtime =  startingtime.replace("T", " ");
		deadline = deadline.substring(5, 16);
		deadline =  deadline.replace("T", " ");
		Console.log(startingtime);
		Console.log(deadline);
//		String activityName = "5人篮球赛";
//		String deadline = DateUtil.now();
//		String startingtime = DateUtil.now();
//		String place = "风雨操场";
//		String introduction = "打篮球咯";
//		Boolean isteam = false;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("status", "success");
			activityService.add(activityId, activityName, sponsor_id, addtime, deadline, 
					startingtime, place, introduction, isteam);
			eventsGalleryService.addEvents(activityId, activityName);
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
		String student_id = "221500410";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Activity> participatelist = new ArrayList<Activity>();
		resultMap.put("status", "success");
		for (Activity activity : activityService.getacticitylist(1)) {
			if (activity.getList().indexOf(student_id) != -1) {
				participatelist.add(activity);
			}
		}
		participatelist = new ArrayList<Activity>();
		resultMap.put("in_registering", participatelist);
		for (Activity activity : activityService.getacticitylist(2)) {
			if (activity.getList().indexOf(student_id) != -1) {
				participatelist.add(activity);
			}
		}
		resultMap.put("in_over", participatelist);
		resultMap.put("registering", activityService.getacticitylist(1));
		resultMap.put("over", activityService.getacticitylist(2));
		Console.log(DateUtil.now());
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("signup")
	public Object signup(HttpServletRequest request) {
		String activityId = "b39b18d4-b8ef-4552-aaf1-2b7e192d7484";
		String student_id = "221500409";
		String list = activityService.getsignuplist(activityId);
		list = list + "."+student_id;
		activityService.updatesignuplist(activityId, list);
		return activityService.getsignuplist(activityId);
	}
	@ResponseBody
	@RequestMapping("dropout")
	public Object dropout(HttpServletRequest request) {
		String activityId = "b39b18d4-b8ef-4552-aaf1-2b7e192d7484";
		String student_id = "221500409";
		String list = activityService.getsignuplist(activityId);
		list.replace("."+student_id, "");
		activityService.updatesignuplist(activityId, list);
		return activityService.getsignuplist(activityId);
	}
}
