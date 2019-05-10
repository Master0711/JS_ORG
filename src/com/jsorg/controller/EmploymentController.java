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
import com.jsorg.pojo.Reptile;
import com.jsorg.pojo.User;
import com.jsorg.service.EmploymentService;
import com.jsorg.service.ReptileService;
import com.jsorg.service.UserService;
import com.jsorg.util.RedisUtil;
import com.jsorg.util.ReptileUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

@Controller
@RequestMapping("employment")
public class EmploymentController {
	@Autowired
	ReptileUtil reptileUtil;
	@Autowired
	EmploymentService employmentService;
	@Autowired
	ReptileService reptileService;
	@Autowired
	UserService userService;
	@Autowired
	RedisUtil redisUtil;
	
	@ResponseBody
	@RequestMapping("reptileInit")
	public void reptileInit() {
		String urllist = "";
		int count = 0;
		for (String string : reptileUtil.getRecruitmentList()) {
			urllist = string + ".";
			count ++ ;
		}
		String datenow = DateUtil.now();
		String uuid = UUID.randomUUID().toString();
		employmentService.add(uuid, datenow, urllist, count);
		for (String string : reptileUtil.getRecruitmentList()) {
			if (reptileService.getReptilesListByid(string).size() == 0) {
				Map<String, String> map = reptileUtil.getRecruitmentDetailed(string);
				String title = map.get("title");
				String time = map.get("time");
				String location = map.get("location");
				String content = map.get("content");
				String url = map.get("url");
				Console.log(string);
				reptileService.add(string, title, time, location, content, url, false);
			}
		}
	}
	@ResponseBody
	@RequestMapping("getreptileListUnCheck")
	public Object getreptileListUnCheck() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			resultMap.put("reptileList", reptileService.list());
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("addTips")
	public Object addTips(@RequestBody Map map,HttpServletRequest request) {
		String reptileid = (String) map.get("reptileid"); 
		String label = "";
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) map.get("label"); 
		for (String string : list) {
			label = label + string + ".";
		}
		String approval_result = (String) map.get("result"); 
		String approval_reason = (String) map.get("reason"); 
		String approval_id = "221500410";
		String approval_time = DateUtil.now();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			reptileService.updateTips(reptileid, label, true, approval_id, approval_time, approval_result, approval_reason);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("recommend")
	public Object recommend() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("getShowReptile")
	public Object getShowReptile(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		List<String> imageList = new ArrayList<String>();
		imageList.add("static/image/jobbg1.jpg");
		imageList.add("static/image/jobbg2.jpg");
		imageList.add("static/image/jobbg3.jpg");
		imageList.add("static/image/jobbg4.jpg");
		imageList.add("static/image/jobbg5.jpg");
		try {
			List<Map<String, String>> maps = new ArrayList<Map<String,String>>();
			List<Reptile> reptiles = reptileService.getcheckedlist();
			int index = 0;
			for (Reptile reptile : reptiles) {
				Map<String, String> map = new HashMap<String, String>();
				if (maps.size() <= 5) {
					map.put("title", reptile.getTitle());
					map.put("time", reptile.getTime());
					map.put("location", reptile.getLocation());
					map.put("content", reptile.getContent());
					map.put("url", reptile.getUrl());
					map.put("tips", reptile.getTips());
					map.put("bgimage", imageList.get(index));
					index ++ ;
					maps.add(map);
				}
			}
			resultMap.put("reptiles", maps);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("setlabel")
	public Object setlabel(@RequestBody Map map,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		User user = (User) redisUtil.get("user");
		String studentid = "";
		if (user != null) {
			studentid = user.getStudent_id();
		}
		String label = "";
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) map.get("label"); 
		for (String string : list) {
			label = label + string + ".";
		}
		try {
			userService.setlabel(studentid, label);
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("getlabel")
	public Object getlabel(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		User user = (User) redisUtil.get("user");
		String studentid = "";
		if (user != null) {
			studentid = user.getStudent_id();
		}
		try {
			String label = userService.getInformation(studentid).getLabel();
			label = label.replace(".", " ");
			resultMap.put("label", label);
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
		
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
}
