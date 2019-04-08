package com.starnet.service;

import java.util.List;


import com.starnet.pojo.User;

public interface UserService {

	List<User> select();
	
	void delete(int id);
	
	void add(String name,String sex,int age,String telephone,String address);
}
