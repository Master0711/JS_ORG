package com.jsorg.service;

import java.util.List;

import com.jsorg.pojo.Register;

public interface RegisterService {
	void apply(String student_id,String realname,String college,
			String discipline,String password,String grade,int sex,String birthday,
			String email,String telephone,String register_time,String register_ip);
	List<Register> selectbycollege(String college);
	List<Register> selectall();
	void check(String student_id,String approval_id,String approval_time,
			Boolean approval_result,String approval_reason);
}
