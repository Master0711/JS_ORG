package com.jsorg.mapper;

import java.util.List;

import com.jsorg.pojo.EventsGallery;

public interface EventsGalleryMapper {
	public void addEvents(String activityId,String activityName,String initiate_time);
	public List<EventsGallery> getAllEvents(Boolean is_write);
	public void perfect(String activityContent,String headFigure,String picture_list,
			String author_id,String time,Boolean is_write);
}
