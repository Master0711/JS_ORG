package com.jsorg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.UserMapper;
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
	
}
