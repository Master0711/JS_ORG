package com.jsorg.mapper;

import java.util.List;

import com.jsorg.pojo.Refund;

public interface RefundMapper {
	public void add(String uuid,String bookingid,String applicantid,String time,String reason,Boolean is_approval);
	public List<Refund> getRefundBydoubleid(String bookingid,String applicantid);
	public List<Refund> getRefundByapplicantid(String applicantid);
	public List<Refund> getRefundListUncheck();
	public void updateRefundUncheck(String uuid,Boolean is_approval,String approval_id,
			String approval_time,String approval_result,String approval_reason);
}
