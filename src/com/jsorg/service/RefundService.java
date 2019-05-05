package com.jsorg.service;

import java.util.List;

import com.jsorg.pojo.Refund;

public interface RefundService {
	void add(String uuid,String bookingid,String applicantid,String time,String reason,Boolean is_approval);
	List<Refund> getRefundBydoubleid(String bookingid,String applicantid);
	List<Refund> getRefundByapplicantid(String applicantid);
}
