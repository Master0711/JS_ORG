package com.jsorg.service.impl;

import java.util.List;

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
	public void uprole(String student_id, int role) {
		
		userMapper.uprole(student_id, role);
	}

	@Override
	public User getInformation(String student_id) {
		return userMapper.getInformation(student_id);
	}

	@Override
	public void updateinformation(String student_id, String college, String discipline, String grade, String telephone,
			String birthday) {
		userMapper.updateinformation(student_id, college, discipline, grade, telephone, birthday);
		
	}

	@Override
	public User getInformationByemail(String email) {
		// TODO Auto-generated method stub
		return userMapper.getInformationByemail(email);
	}

	@Override
	public void updateavatarimage(String student_id, String avatar) {
		// TODO Auto-generated method stub
		userMapper.updateavatarimage(student_id, avatar);
		
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return userMapper.list();
	}

	@Override
	public List<User> listbycollege(String college) {
		// TODO Auto-generated method stub
		return userMapper.listbycollege(college);
	}

	@Override
	public void setlabel(String student_id, String label) {
		// TODO Auto-generated method stub
		userMapper.setlabel(student_id, label);
	}
	
}
