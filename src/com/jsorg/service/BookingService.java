package com.jsorg.service;

import java.util.List;

import com.jsorg.pojo.Booking;

public interface BookingService {
	void add(String uuid,String trainid,String applicantid,String applicationtime,
			int count,Boolean isrefund,int status);
	List<Booking> getBookingByDoubleID(String trainid,String applicantid);
	List<Booking> getBookingUnFinish(String applicantid);
	List<Booking> getBookingFinish(String applicantid);
	List<Booking> getBookingModerated(String applicantid);
	Booking getBookingBybookingid(String uuid);
	List<Booking> getBookingUnCheck();
	void updateBookingCheck(String uuid,int status,Boolean is_approval,
			String approval_id,String approval_time,String approval_result,String approval_reason);
}
