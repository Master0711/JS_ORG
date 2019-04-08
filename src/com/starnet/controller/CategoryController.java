package com.starnet.controller;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starnet.pojo.Identity;
import com.starnet.service.IdentityService;
import com.starnet.service.UserService;


@Controller
@RequestMapping("")
public class CategoryController {

	@Autowired
	UserService userService;
	@Autowired
	IdentityService identityService;

	@ResponseBody
	@RequestMapping("showuser")
	public Object listUser(HttpServletRequest request) throws JsonProcessingException {
		String callback = request.getParameter("callback");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("userlist", userService.select());
		String back = callback+"("+mapper.writeValueAsString(listMap)+")";
		
		return back;
	}
	@ResponseBody
	@RequestMapping("login")
	public Object login(String username,String password,HttpServletRequest request) throws JsonProcessingException {
		
		List<Identity> identities = identityService.list();
		
		String loginmsg;
		Map<String, Object> result = new HashMap<String, Object>();
		
		String callback = request.getParameter("callback");
		
		ObjectMapper mapper = new ObjectMapper();
		
		for (Identity identity : identities) {
			if (username.equals(identity.getUsername())) {
				if (password.equals(identity.getPassword())) {
					loginmsg = "loginsuccess";
				}else {
					loginmsg = "passworderror";
				}
				result.put("loginmsg", loginmsg);
				String back = callback+"("+mapper.writeValueAsString(result)+")";
				return back;
			}
		}
		
		loginmsg = "nosuchuser";
		result.put("loginmsg", loginmsg);
		String back = callback+"("+mapper.writeValueAsString(result)+")";
		return back;
	}
	@ResponseBody
	@RequestMapping("adduser")
	public Object adduser(String name,String sex,int age,String telephone,String address,HttpServletRequest request) throws JsonProcessingException {
		System.out.println(name+sex+age+telephone+address);
		userService.add(name, sex, age, telephone, address);
		
		String callback = request.getParameter("callback");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("status", "success");
		
		String back = callback+"("+mapper.writeValueAsString(result)+")";
		
		return back;
	}
	@ResponseBody
	@RequestMapping("deleteuser")
	public Object deleteuser(String stringids,HttpServletRequest request) throws JsonProcessingException {
		System.out.println(stringids);
		
		String[] strarr = stringids.split(",");
		int[] ids = new int[strarr.length];
		for(int i=0;i<strarr.length;i++){
			ids[i]=Integer.parseInt(strarr[i]);
			System.out.println(ids[i]);
			userService.delete(ids[i]);
		}
		String callback = request.getParameter("callback");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("status", "success");
		String back = callback+"("+mapper.writeValueAsString(listMap)+")";
		
		return back;
	}
}
