package com.jsorg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.EmploymentMapper;
import com.jsorg.service.EmploymentService;

@Service
public class EmploymentServiceImpl implements EmploymentService{
	@Autowired
	EmploymentMapper employmentMapper;

	@Override
	public void add(String uuid, String time, String urllist, int count) {
		// TODO Auto-generated method stub
		employmentMapper.add(uuid, time, urllist, count);
		
	}
	
}
