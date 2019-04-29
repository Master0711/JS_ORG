package com.jsorg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.RegisterMapper;
import com.jsorg.pojo.Register;
import com.jsorg.service.RegisterService;
@Service
public class RegisterServiceImpl implements RegisterService{
	@Autowired
	RegisterMapper registerMapper;
	
	@Override
	public void apply(String student_id,String realname,String college,
			String discipline,String password,String grade,int sex,String birthday,
			String email,String telephone,String register_time,String register_ip) {
		registerMapper.apply(student_id, realname, college, discipline, password, 
				grade, sex, birthday, email, telephone, register_time, register_ip);
	}

	@Override
	public List<Register> selectbycollege(String college) {
		return registerMapper.selectbycollege(college);
	}

	@Override
	public List<Register> selectall() {
		return registerMapper.selectall();
	}

	@Override
	public void check(String student_id, String approval_id, String approval_time, Boolean approval_result,
			String approval_reason) {
		registerMapper.check(student_id, approval_id, approval_time, approval_result, approval_reason);
	}

	@Override
	public Register getRegisterByid(String student_id) {
		return registerMapper.getRegisterByid(student_id);
	}

	@Override
	public void delectById(String student_id) {
		registerMapper.delectById(student_id);
	}
}
