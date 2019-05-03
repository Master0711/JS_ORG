package com.jsorg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.AnnouncementMapper;
import com.jsorg.pojo.Announcement;
import com.jsorg.service.AnnouncementService;
@Service
public class AnnouncementServiceImpl implements AnnouncementService{
	@Autowired
	AnnouncementMapper announcementMapper;
	@Override
	public void addannouncement(String uuid, String theme, String sponsor, String time, String content) {
		// TODO Auto-generated method stub
		announcementMapper.addannouncement(uuid, theme, sponsor, time, content);
	}

	@Override
	public List<Announcement> announcementList() {
		// TODO Auto-generated method stub
		return announcementMapper.announcementList();
	}

	@Override
	public void delAnnouncement(String uuid) {
		// TODO Auto-generated method stub
		announcementMapper.delAnnouncement(uuid);
	}

}
