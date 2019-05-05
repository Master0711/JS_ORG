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
	
	
}
