package com.jsorg.mapper;

import java.util.List;
import com.jsorg.pojo.Announcement;

public interface AnnouncementMapper {
	public void addannouncement(String uuid,String theme,String sponsor,String time,
			String content);
	public List<Announcement> announcementList();
	public void delAnnouncement(String uuid);
}
