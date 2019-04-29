package com.jsorg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jsorg.pojo.Register;
import com.jsorg.service.RegisterService;
import com.jsorg.util.IpUtil;
import com.jsorg.util.RedisUtil;
import com.jsorg.util.SendEmail;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	RegisterService registerService;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	SendEmail sendEmail;
	@Autowired
	IpUtil ipUtil;
	
	@ResponseBody
	@RequestMapping("register")
	public Object register(@RequestBody Map map,HttpServletRequest request) {
		String student_id = (String) map.get("student_id");
		String realname = (String) map.get("realname");
		String college = (String) map.get("college");
		String discipline = (String) map.get("discipline");
		String password = (String) map.get("password");
		String grade = (String) map.get("grade");
		int sex = (int) map.get("sex");
		String birthday = (String) map.get("birthday");
		String email = (String) map.get("email");
		String telephone = (String) map.get("telephone");
		String vervode = (String) map.get("vervode");
		
		String register_ip = request.getHeader("x-forwarded-for");
		if(register_ip == null || register_ip.length() == 0 ||"unknown".equalsIgnoreCase(register_ip)) {
			register_ip = request.getHeader("Proxy-Client-register_ip");
		}
		if(register_ip == null || register_ip.length() == 0 ||"unknown".equalsIgnoreCase(register_ip)) {
			register_ip = request.getHeader("WL-Proxy-Client-register_ip");
		}
		if(register_ip == null || register_ip.length() == 0 ||"unknown".equalsIgnoreCase(register_ip)) {
			register_ip = request.getRemoteAddr();
		}
		String vervodekey = student_id + register_ip;
		String register_time = DateUtil.now();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Console.log(vervode);
		Console.log(vervodekey);
		Console.log(redisUtil.get(vervodekey));
		if (!vervode.equals(redisUtil.get(vervodekey))) {
			resultMap.put("status", "codeerror");
			resultMap.put("message", "邮箱验证码错误，请重写输入");
		}else {
			try {
				registerService.apply(student_id, realname, college, discipline, password, 
						grade, sex, birthday, email, telephone, register_time, register_ip);
				resultMap.put("status", "success");
				resultMap.put("message", "注册成功，请耐心等待审核，我们将以邮箱通知你！");
				sendEmail.send(email, "您已经提交注册请求.请耐心等待审核。来自 JS Association .  时间："+register_time);
			} catch (Exception e) {
				resultMap.put("status", "registered");
				resultMap.put("message", "你已经提交过注册申请，请耐心等待审核，请勿重复申请！");
				Console.log(e);
			}
		}
		Object jsonObject = JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("sendvervode")
	public Object sendvervode(@RequestBody Map map,HttpServletRequest request) {
		String email = (String) map.get("email");
		String student_id = (String) map.get("student_id");
		String register_ip = request.getHeader("x-forwarded-for");
		if(register_ip == null || register_ip.length() == 0 ||"unknown".equalsIgnoreCase(register_ip)) {
			register_ip = request.getHeader("Proxy-Client-register_ip");
		}
		if(register_ip == null || register_ip.length() == 0 ||"unknown".equalsIgnoreCase(register_ip)) {
			register_ip = request.getHeader("WL-Proxy-Client-register_ip");
		}
		if(register_ip == null || register_ip.length() == 0 ||"unknown".equalsIgnoreCase(register_ip)) {
			register_ip = request.getRemoteAddr();
		}
		String vervodekey = student_id + register_ip;
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if (redisUtil.hasKey(vervodekey)) {
			resultMap.put("status", "existed");
			resultMap.put("message", "勿频繁请求，请稍后重试。发送至邮箱的验证码尚可使用。");
		}else {
			Random random = new Random();
			String vervode = random.nextInt(1000000) + "";
			int length = vervode.length();
			if (length < 6) {
				for (int i = 1; i <= 6-length; i++) {
					vervode = "0" + vervode;
				}
			}
			
			redisUtil.set(vervodekey, vervode, 5*60);
			
			String now = DateUtil.now();
			String content = "您正在进行注册操作，您的邮箱验证码是"+vervode+" .请妥善保管。来自 JS Association .  时间："+now;
			sendEmail.send(email, content);
			resultMap.put("status", "success");
			resultMap.put("message", "验证码已发送，请到邮箱中查看，有效时间五分钟。");
		}
		
		Object jsonObject = JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	
	@ResponseBody
	@RequestMapping("getregisterlist")
	public Object getregisterlist(HttpServletRequest request) {
		String role = "1";
		String college = "数学与计算机科学";
		
		List<Register> registers = null;
		
		if (role.equals("1")) {
			registers = registerService.selectbycollege(college);
		}else if (role.equals("2") || role.equals("3")) {
			registers = registerService.selectall();
		}
		
		Object jsonObject = JSONObject.toJSON(registers);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("checkregister")
	public Object checkregister(HttpServletRequest request) {
		
//		String student_id = (String) map.get("student_id");
//		String approval_id = (String) map.get("approval_id");
//		Boolean approval_result = (Boolean) map.get("approval_result");
//		String approval_reason = (String) map.get("approval_reason");
		String approval_time = DateUtil.now();
		
		String student_id = "22";
		String approval_id = "221500410";
		Boolean approval_result = false;
		String approval_reason = "可以啦";
		registerService.check(student_id, approval_id, approval_time, 
				approval_result, approval_reason);
		
		return 0;
	}
}
