package com.jsorg.mapper;

import java.util.List;

import com.jsorg.pojo.Activity;

public interface ActivityMapper {
	public void add(String activityId,String activityName,String sponsor_id,String addtime,
			String deadline,String startingtime,String place,String introduction,Boolean isteam);
	public List<Activity> getacticitylist(int status);
	public String getsignuplist(String activityId);
	public void updatesignuplist(String activityId,String list);
}
