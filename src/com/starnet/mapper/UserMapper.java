package com.starnet.mapper;

import java.util.List;


import com.starnet.pojo.User;

public interface UserMapper {
	
	public List<User> select();
	
	public void delete(int id);
	
	public void add(String name,String sex,int age,String telephone,String address);
}
