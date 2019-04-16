package com.jsorg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsorg.service.RegisterService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	RegisterService registerService;
	
//	@ResponseBody
//	@RequestMapping("register")
//	public Object register(String student_id,String realname,String password,
//			String college,String grade,String discipline,String telephone) {
//		return registerService.apply(student_id, realname, password, 
//				college, grade, discipline, telephone);
//	}
	@ResponseBody
	@RequestMapping("register")
	public String register() {
		
		String student_id = "221500410";
		String realname = "王少剑";
		String password = "221500410";
		String college = "数学与计算机科学学院";
		String grade = "2015";
		String discipline = "软件工程";
		String telephone = "13003829227";
		String register_time = "13003829227";
		String register_ip = ".........";
		try {
			registerService.apply(student_id, realname, password, 
					college, grade, discipline, telephone,register_time,register_ip);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "success!";
	}
}
