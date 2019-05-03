package com.jsorg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.EventsGalleryMapper;
import com.jsorg.pojo.EventsGallery;
import com.jsorg.service.EventsGalleryService;

@Service
public class EventsGalleryServiceImpl implements EventsGalleryService{
	@Autowired
	EventsGalleryMapper eventsGalleryMapper;
	@Override
	public List<EventsGallery> getAllEvents(Boolean is_write) {
		// TODO Auto-generated method stub
		return eventsGalleryMapper.getAllEvents(is_write);
	}

	@Override
	public void perfect(String activityContent, String headFigure, String picture_list, String author_id, String time,
			Boolean is_write) {
		eventsGalleryMapper.perfect(activityContent, headFigure, picture_list, author_id, time, is_write);
		
	}

	@Override
	public void addEvents(String activityId, String activityName, String initiate_time) {
		eventsGalleryMapper.addEvents(activityId, activityName, initiate_time);
	}

}
