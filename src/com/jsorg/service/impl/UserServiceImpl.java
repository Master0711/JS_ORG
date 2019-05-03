package com.jsorg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.UserMapper;
import com.jsorg.pojo.User;
import com.jsorg.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;

	@Override
	public void add(String student_id, String realname, String college, String discipline, String password,
			String grade, int sex, String email, String telephone, String birthday, int role, String avatar,
			int status) {
		
		userMapper.add(student_id, realname, college, discipline, password, grade, 
				sex, email, telephone, birthday, role, avatar, status);
	}

	@Override
	public void updateinformation(String student_id, String college, String discipline, String password, String grade,
			int sex, String telephone, String birthday) {
		userMapper.updateinformation(student_id, college, discipline, password, grade, sex, telephone, birthday);
	}

	@Override
	public void uprole(String student_id, int role) {
		
		userMapper.uprole(student_id, role);
	}

	@Override
	public User getInformation(String student_id) {
		return userMapper.getInformation(student_id);
	}
	
}
