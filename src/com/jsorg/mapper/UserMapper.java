package com.jsorg.mapper;

import java.util.List;

import com.jsorg.pojo.User;

public interface UserMapper {
	public void add(String student_id,String realname,String college,String discipline,
			String password,String grade,int sex,String email,String telephone,String birthday,
			int role,String avatar,int status);
	public void updateinformation(String student_id,String college,String discipline,
			String grade,String telephone,String birthday);
	public void uprole(String student_id,int role);
	public User getInformation(String student_id);
	public User getInformationByemail(String email);
	public void updateavatarimage(String student_id,String avatar);
	public List<User> list();
	public List<User> listbycollege(String college);
	public void setlabel(String student_id,String label);
}
