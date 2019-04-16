package com.jsorg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.RegisterMapper;
import com.jsorg.service.RegisterService;
@Service
public class RegisterServiceImpl implements RegisterService{
	@Autowired
	RegisterMapper registerMapper;
	
	@Override
	public void apply(String student_id,String realname,String password,
			String college,String grade,String discipline,String telephone,
			String register_time,String register_ip) {
		registerMapper.apply(student_id, realname, password, college,grade, discipline, telephone,register_time,register_ip);
	}
}
