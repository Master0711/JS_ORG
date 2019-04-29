package com.jsorg.mapper;

public interface UserMapper {
	public void add(String student_id,String realname,String college,String discipline,
			String password,String grade,int sex,String email,String telephone,String birthday,
			int role,String avatar,int status);
	public void updateinformation(String student_id,String college,String discipline,
			String password,String grade,int sex,String telephone,String birthday);
	public void uprole(String student_id,int role);
}
