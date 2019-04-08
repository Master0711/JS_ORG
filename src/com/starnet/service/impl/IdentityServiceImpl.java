package com.starnet.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starnet.mapper.IdentityMapper;
import com.starnet.pojo.Identity;
import com.starnet.service.IdentityService;

@Service
public class IdentityServiceImpl implements IdentityService{
	@Autowired
	IdentityMapper identityMapper;

	@Override
	public List<Identity> list() {
		
		return identityMapper.list();
	}
	
	
}
