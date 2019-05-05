package com.jsorg.service;

import com.jsorg.pojo.User;

public interface UserService {
	void add(String student_id,String realname,String college,String discipline,
			String password,String grade,int sex,String email,String telephone,String birthday,
			int role,String avatar,int status);
	void updateinformation(String student_id,String college,String discipline,
			String grade,String telephone,String birthday);
	void uprole(String student_id,int role);
	User getInformation(String student_id);
}
