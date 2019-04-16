package com.jsorg.service;

public interface RegisterService {
	void apply(String student_id,String realname,String password,
			String college,String grade,String discipline,String telephone,
			String register_time,String register_ip);
}
