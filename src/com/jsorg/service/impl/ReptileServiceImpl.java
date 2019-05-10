package com.jsorg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.ReptileMapper;
import com.jsorg.pojo.Reptile;
import com.jsorg.service.ReptileService;

@Service
public class ReptileServiceImpl implements ReptileService{
	@Autowired
	ReptileMapper reptileMapper;

	@Override
	public void add(String reptileid, String title, String time, String location, String content, String url,
			Boolean is_approval) {
		// TODO Auto-generated method stub
		reptileMapper.add(reptileid, title, time, location, content, url, is_approval);
		
	}

	@Override
	public void updateTips(String reptileid, String tips, Boolean is_approval, String approval_id, String approval_time,
			String approval_result, String approval_reason) {
		// TODO Auto-generated method stub
		reptileMapper.updateTips(reptileid, tips, is_approval, approval_id, approval_time, approval_result, approval_reason);
		
	}

	@Override
	public List<Reptile> list() {
		// TODO Auto-generated method stub
		return reptileMapper.list();
	}

	@Override
	public List<Reptile> getReptilesListByid(String reptileid) {
		// TODO Auto-generated method stub
		return reptileMapper.getReptilesListByid(reptileid);
	}

	@Override
	public List<Reptile> getcheckedlist() {
		// TODO Auto-generated method stub
		return reptileMapper.getcheckedlist();
	}
	
}
