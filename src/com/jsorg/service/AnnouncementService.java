package com.jsorg.service;

import java.util.List;

import com.jsorg.pojo.Announcement;

public interface AnnouncementService {
	void addannouncement(String uuid,String theme,String sponsor,String time,
			String content);
	List<Announcement> announcementList();
	void delAnnouncement(String uuid);
}
