package com.jsorg.pojo;

public class Booking {
	private String uuid;
	private String trainid;
	private String applicantid;
	private String applicationtime;
	private int count;
	private Boolean isrefund;
	private String refundid;
	private int status;
	
	private Boolean is_approval;
	private String approval_id;
	private String approval_time;
	private String approval_result;
	private String approval_reason;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTrainid() {
		return trainid;
	}
	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public String getApplicationtime() {
		return applicationtime;
	}
	public void setApplicationtime(String applicationtime) {
		this.applicationtime = applicationtime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Boolean getIsrefund() {
		return isrefund;
	}
	public void setIsrefund(Boolean isrefund) {
		this.isrefund = isrefund;
	}
	public String getRefundid() {
		return refundid;
	}
	public void setRefundid(String refundid) {
		this.refundid = refundid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
