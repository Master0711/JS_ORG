package com.starnet.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starnet.mapper.UserMapper;
import com.starnet.pojo.User;
import com.starnet.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	
	public List<User> select() {
		
		return userMapper.select();
	}

	public void delete(int id) {
		
		userMapper.delete(id);
	}

	public void add(String name, String sex, int age, String telephone, String address) {
		
		userMapper.add(name, sex, age, telephone, address);
	}

}
