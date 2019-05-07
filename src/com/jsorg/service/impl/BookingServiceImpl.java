package com.jsorg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.BookingMapper;
import com.jsorg.pojo.Booking;
import com.jsorg.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService{
	@Autowired
	BookingMapper bookingMapper;

	@Override
	public void add(String uuid, String trainid, String applicantid, String applicationtime, int count,
			Boolean isrefund, int status) {
		// TODO Auto-generated method stub
		bookingMapper.add(uuid, trainid, applicantid, applicationtime, count, isrefund, status);
		
	}

	@Override
	public List<Booking> getBookingByDoubleID(String trainid, String applicantid) {
		// TODO Auto-generated method stub
		return bookingMapper.getBookingByDoubleID(trainid, applicantid);
	}

	@Override
	public List<Booking> getBookingUnFinish(String applicantid) {
		// TODO Auto-generated method stub
		return bookingMapper.getBookingUnFinish(applicantid);
	}

	@Override
	public List<Booking> getBookingFinish(String applicantid) {
		// TODO Auto-generated method stub
		return bookingMapper.getBookingFinish(applicantid);
	}

	@Override
	public List<Booking> getBookingModerated(String applicantid) {
		// TODO Auto-generated method stub
		return bookingMapper.getBookingModerated(applicantid);
	}

	@Override
	public Booking getBookingBybookingid(String uuid) {
		// TODO Auto-generated method stub
		return bookingMapper.getBookingBybookingid(uuid);
	}

	@Override
	public List<Booking> getBookingUnCheck() {
		// TODO Auto-generated method stub
		return bookingMapper.getBookingUnCheck();
	}

	@Override
	public void updateBookingCheck(String uuid, int status, Boolean is_approval, String approval_id,
			String approval_time, String approval_result, String approval_reason) {
		// TODO Auto-generated method stub
		bookingMapper.updateBookingCheck(uuid, status, is_approval, approval_id, 
				approval_time, approval_result, approval_reason);
		
	}
	
}
