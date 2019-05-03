package com.jsorg.service;

import java.util.List;

import com.jsorg.pojo.Activity;

public interface ActivityService {
	void add(String activityId,String activityName,String sponsor_id,String addtime,
			String deadline,String startingtime,String place,String introduction,Boolean isteam);
	List<Activity> getacticitylist(int status);
	String getsignuplist(String activityId);
	void updatesignuplist(String activityId,String list);
	void updatestatus(String activityId,int status);
}
