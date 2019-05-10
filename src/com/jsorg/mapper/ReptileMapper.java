package com.jsorg.mapper;

import java.util.List;

import com.jsorg.pojo.Reptile;

public interface ReptileMapper {
	public void add(String reptileid,String title,String time,String location,
			String content,String url,Boolean is_approval);
	public void updateTips(String reptileid,String tips,Boolean is_approval,
			String approval_id,String approval_time,String approval_result,String approval_reason);
	public List<Reptile> list();
	public List<Reptile> getReptilesListByid(String reptileid);
	public List<Reptile> getcheckedlist();
}
