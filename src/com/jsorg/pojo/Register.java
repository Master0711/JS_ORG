package com.jsorg.pojo;

import java.sql.Time;

public class Register {
	private String student_id;
	private String realname;
	private String password;
	private String college;
	private String discipline;
	private String grade;
	private String telephone;
	private Time register_time;
	private Time register_ip;
	private Boolean is_approval;
	private String approval_id;
	private Time approval_time;
	private Boolean approval_result;
	private String approval_reason;
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Time getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Time register_time) {
		this.register_time = register_time;
	}
	public Time getRegister_ip() {
		return register_ip;
	}
	public void setRegister_ip(Time register_ip) {
		this.register_ip = register_ip;
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
	public Time getApproval_time() {
		return approval_time;
	}
	public void setApproval_time(Time approval_time) {
		this.approval_time = approval_time;
	}
	public Boolean getApproval_result() {
		return approval_result;
	}
	public void setApproval_result(Boolean approval_result) {
		this.approval_result = approval_result;
	}
	public String getApproval_reason() {
		return approval_reason;
	}
	public void setApproval_reason(String approval_reason) {
		this.approval_reason = approval_reason;
	}
	
	
}
