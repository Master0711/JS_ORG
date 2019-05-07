package com.jsorg.mapper;

import java.util.List;

import com.jsorg.pojo.Booking;

public interface BookingMapper {
	public void add(String uuid,String trainid,String applicantid,String applicationtime,
			int count,Boolean isrefund,int status);
	public List<Booking> getBookingByDoubleID(String trainid,String applicantid);
	public List<Booking> getBookingUnFinish(String applicantid);
	public List<Booking> getBookingFinish(String applicantid);
	public List<Booking> getBookingModerated(String applicantid);
	public Booking getBookingBybookingid(String uuid);
	public List<Booking> getBookingUnCheck();
	public void updateBookingCheck(String uuid,int status,Boolean is_approval,
			String approval_id,String approval_time,String approval_result,String approval_reason);
}
