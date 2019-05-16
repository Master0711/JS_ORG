package com.jsorg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.ActivityMapper;
import com.jsorg.pojo.Activity;
import com.jsorg.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{
	@Autowired
	ActivityMapper activityMapper;

	@Override
	public void add(String activityId, String activityName, String sponsor_id, String addtime, String deadline,
			String startingtime, String place, String introduction, Boolean isteam) {
		activityMapper.add(activityId, activityName, sponsor_id, addtime, deadline, 
				startingtime, place, introduction, isteam);
	}
	@Override
	public List<Activity> getacticitylist(int status) {
		return activityMapper.getacticitylist(status);
	}
	@Override
	public String getsignuplist(String activityId) {
		return activityMapper.getsignuplist(activityId);
	}
	@Override
	public void updatesignuplist(String activityId, String list) {
		activityMapper.updatesignuplist(activityId, list);
	}
	@Override
	public void updatestatus(String activityId, int status) {
		activityMapper.updatestatus(activityId, status);
	}
	@Override
	public Activity getActivity(String activityId) {
		// TODO Auto-generated method stub
		return activityMapper.getActivity(activityId);
	}
	
}
