package com.jsorg.service;

import java.util.List;

import com.jsorg.pojo.Booking;

public interface BookingService {
	void add(String uuid,String trainid,String applicantid,String applicationtime,
			int count,Boolean isrefund,int status);
	Booking getBookingByDoubleID(String trainid,String applicantid);
	List<Booking> getBookingUnFinish(String applicantid);
	List<Booking> getBookingFinish(String applicantid);
	List<Booking> getBookingModerated(String applicantid);
	Booking getBookingBybookingid(String uuid);
}
