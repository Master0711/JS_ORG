package com.jsorg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.EventsGalleryMapper;
import com.jsorg.service.EventsGalleryService;

@Service
public class EventsGalleryServiceImpl implements EventsGalleryService{
	@Autowired
	EventsGalleryMapper eventsGalleryMapper;

	@Override
	public void addEvents(String activityId, String activityName) {
		eventsGalleryMapper.addEvents(activityId, activityName);
		
	}
}
