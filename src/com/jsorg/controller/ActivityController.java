package com.jsorg.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.jsorg.pojo.Activity;
import com.jsorg.pojo.EventsGallery;
import com.jsorg.pojo.Member;
import com.jsorg.pojo.User;
import com.jsorg.service.ActivityService;
import com.jsorg.service.EventsGalleryService;
import com.jsorg.service.UserService;
import com.jsorg.util.FileUtil;
import com.jsorg.util.RedisUtil;
import com.jsorg.util.SendEmail;
import com.xuxueli.poi.excel.ExcelExportUtil;

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
	@Autowired
	FileUtil fileUtil;
	@Autowired
	UserService userService;
	@Autowired
	SendEmail sendEmail;
	
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
			resultMap.put("cancel", activityService.getacticitylist(4));
			List<Activity> activities = new ArrayList<Activity>();
			for (Activity activity : activityService.getacticitylist(3)) {
				if (!eventsGalleryService.getEventsGallery(activity.getActivityId()).getIs_write()) {
					activities.add(activity);
				}
			}
			resultMap.put("notwritten", activities);
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
		HttpSession session = request.getSession();
		Console.log("sessionid is:"+session.getId());
		session.setAttribute("hhh", "gggg");
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
	@ResponseBody
	@RequestMapping("Addeventpicture")
	public Object Addeventpicture(HttpServletRequest request) {
		String activityId = (String) redisUtil.get("activityId");
		
		List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("file");
		String pictureString = "";
		String headFile = "";
		int j = 0;
//		for (int i = 0; i < fileList.size(); i++) {
//			Console.log(i);
//			if (i != 0) {
//				String filename = DateUtil.now().replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "") 
//	            		+ activityId +fileList.get(i).getOriginalFilename();
//				pictureString = pictureString + "static/uploadimage/" + filename + ".";
//				fileUtil.saveFile(fileList.get(i), filename);
//			}else {
//				String filename = DateUtil.now().replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "") 
//	            		+ activityId +fileList.get(i).getOriginalFilename();
//				fileUtil.saveFile(fileList.get(0), filename);
//				headFile = "static/uploadimage/" + filename;
//			}
//		}
		for (MultipartFile multipartFile : fileList) {
			if (j == 0) {
				String filename = DateUtil.now().replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "") 
	            		+ activityId +multipartFile.getOriginalFilename();
				fileUtil.saveFile(multipartFile, filename);
				headFile = "static/uploadimage/" + filename;
			}else {
				String filename = DateUtil.now().replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "") 
	            		+ activityId +multipartFile.getOriginalFilename();
				pictureString = pictureString + "static/uploadimage/" + filename + ".";
				fileUtil.saveFile(multipartFile, filename);
			}
			j++;
		}
		Console.log(headFile);
		Console.log(pictureString);
		eventsGalleryService.uploadimage(activityId, headFile, pictureString);
		return 0;
	}
	@ResponseBody
	@RequestMapping("storageactivityid")
	public Object storageactivityid(@RequestBody Map map,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		String activityId = (String) map.get("activityId");
		try {
			redisUtil.set("activityId", activityId, 60*30);
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
	@RequestMapping("cancelactivity")
	public Object cancelactivity(@RequestBody Map map,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		String activityId = (String) map.get("activityId");
		try {
			Activity activity = activityService.getActivity(activityId);
			String content = "：同学你好.活动" + activity.getActivityName() + "已经取消。如有疑问，请咨询管理员";
			String liString = activity.getList();
			String[] idliStrings = liString.split("\\.");
			for (String string : idliStrings) {
				if (!string.equals("")) {
					sendEmail.send(userService.getInformation(string).getEmail(), content);
				}
			}
			activityService.updatestatus(activityId, 4);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
		
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("exportlist")
	public Object exportlist(@RequestBody Map map,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		String activityId = (String) map.get("activityId");
		Activity activity = activityService.getActivity(activityId);
		String acticityname = activity.getActivityName();
		String filePath = "D:\\my-project\\static\\uploadimage\\" + acticityname + ".xls";
		try {
			List<Member> members = new ArrayList<Member>();
			String liString = activity.getList();
			String[] idliStrings = liString.split("\\.");
			for (String string : idliStrings) {
				if (!string.equals("")) {
					User user = userService.getInformation(string);
					String student_id = user.getStudent_id();
					String realname = user.getRealname();
					String college = user.getCollege();
					String discipline = user.getDiscipline();
					String grade = user.getGrade();
					String email = user.getEmail();
					String telephone = user.getTelephone();
					Member member = new Member(student_id, realname, college, discipline, 
							grade, email, telephone);
					members.add(member);
				}
			}
			ExcelExportUtil.exportToFile(filePath, members);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
        
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("getShowActivity")
	public Object getShowActivity(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		
		
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
}
