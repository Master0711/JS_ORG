package com.jsorg.service;

import java.util.List;

import com.jsorg.pojo.Reptile;

public interface ReptileService {
	
	void add(String reptileid,String title,String time,String location,
			String content,String url,Boolean is_approval);
	void updateTips(String reptileid,String tips,Boolean is_approval,
			String approval_id,String approval_time,String approval_result,String approval_reason);
	List<Reptile> list();
	List<Reptile> getReptilesListByid(String reptileid);
}
