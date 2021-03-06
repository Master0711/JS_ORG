package com.jsorg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.RefundMapper;
import com.jsorg.pojo.Refund;
import com.jsorg.service.RefundService;
@Service
public class RefundServiceImpl implements RefundService{
	@Autowired
	RefundMapper refundMapper;

	@Override
	public void add(String uuid, String bookingid, String applicantid, String time,String reason, Boolean is_approval) {
		// TODO Auto-generated method stub
		refundMapper.add(uuid, bookingid, applicantid, time,reason, is_approval);
		
	}

	@Override
	public List<Refund> getRefundBydoubleid(String bookingid, String applicantid) {
		// TODO Auto-generated method stub
		return refundMapper.getRefundBydoubleid(bookingid, applicantid);
	}

	@Override
	public List<Refund> getRefundByapplicantid(String applicantid) {
		// TODO Auto-generated method stub
		return refundMapper.getRefundByapplicantid(applicantid);
	}

	@Override
	public List<Refund> getRefundListUncheck() {
		// TODO Auto-generated method stub
		return refundMapper.getRefundListUncheck();
	}

	@Override
	public void updateRefundUncheck(String uuid, Boolean is_approval, String approval_id, String approval_time,
			String approval_result, String approval_reason) {
		// TODO Auto-generated method stub
		refundMapper.updateRefundUncheck(uuid, is_approval, approval_id, approval_time, approval_result, approval_reason);
	}
	
}
