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
import com.jsorg.pojo.Announcement;
import com.jsorg.pojo.User;
import com.jsorg.service.AnnouncementService;
import com.jsorg.util.RedisUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

@Controller
@RequestMapping("announcement")
public class AnnouncementController {
	@Autowired
	AnnouncementService announcementService;
	@Autowired
	RedisUtil redisUtil;
	@ResponseBody
	@RequestMapping("addannouncement")
	public Object addannouncement(@RequestBody Map map,HttpServletRequest request) {
		
		User user = (User) redisUtil.get("user");
		String uuid = UUID.randomUUID().toString();
		String theme = (String) map.get("theme");
		String content = (String) map.get("content");
		String time = DateUtil.now();
		String sponsor = "";
		if (user != null) {
			sponsor = user.getStudent_id();
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			announcementService.addannouncement(uuid, theme, sponsor, time, content);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("listannouncement")
	public Object listannouncement(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			resultMap.put("list", announcementService.announcementList());
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("delannouncement")
	public Object delannouncement(@RequestBody Map map,HttpServletRequest request) {
		String uuid = (String) map.get("uuid");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			announcementService.delAnnouncement(uuid);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("getShowNotice")
	public Object getShowNotice(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			int k = 0;
			List<Announcement> announcementlist = new ArrayList<Announcement>();
			List<Announcement> announcements = announcementService.announcementList();
			for (int i = announcements.size() - 1; i > -1; i--) {
				if (k < 6) {
//					announcementlist.add(e)
				}
			}
			resultMap.put("announcementlist", announcements);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
		
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
}
