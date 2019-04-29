package com.jsorg.mapper;

import java.util.List;

import com.jsorg.pojo.Register;

public interface RegisterMapper {
	public void apply(String student_id,String realname,String college,
			String discipline,String password,String grade,int sex,String birthday,
			String email,String telephone,String register_time,String register_ip);
	public List<Register> selectbycollege(String college);
	public List<Register> selectall();
	public void check(String student_id,String approval_id,String approval_time,
			Boolean approval_result,String approval_reason);
	public Register getRegisterByid(String student_id);
	public void delectById(String student_id);
}
