package com.jsorg.service;

import java.util.List;

import com.jsorg.pojo.EventsGallery;

public interface EventsGalleryService {
	void addEvents(String activityId,String activityName,String initiate_time);
	List<EventsGallery> getAllEvents(Boolean is_write);
	void perfect(String activityContent,String headFigure,String picture_list,
			String author_id,String time,Boolean is_write);
	EventsGallery getEventsGallery(String activityId);
	void uploadimage(String activityId,String headFigure,String picture_list);
	
}
