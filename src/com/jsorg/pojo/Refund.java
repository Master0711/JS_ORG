package com.jsorg.pojo;

public class Refund {
	private String uuid;
	private String bookingid;
	private String applicantid;
	private String time;
	private String reason;
	private Boolean is_approval;
	private String approval_id;
	private String approval_time;
	private String approval_result;
	private String approval_reason;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getBookingid() {
		return bookingid;
	}
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Boolean getIs_approval() {
		return is_approval;
	}
	public void setIs_approval(Boolean is_approval) {
		this.is_approval = is_approval;
	}
	public String getApproval_id() {
		return approval_id;
	}
	public void setApproval_id(String approval_id) {
		this.approval_id = approval_id;
	}
	public String getApproval_time() {
		return approval_time;
	}
	public void setApproval_time(String approval_time) {
		this.approval_time = approval_time;
	}
	public String getApproval_result() {
		return approval_result;
	}
	public void setApproval_result(String approval_result) {
		this.approval_result = approval_result;
	}
	public String getApproval_reason() {
		return approval_reason;
	}
	public void setApproval_reason(String approval_reason) {
		this.approval_reason = approval_reason;
	}
	
	
}
