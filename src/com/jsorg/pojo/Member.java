package com.jsorg.pojo;

public class Member {
	private String student_id;
	private String realname;
	private String college;
	private String discipline;
	private String grade;
	private String email;
	private String telephone;
	
	
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	
	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public Member(String student_id, String realname, String college, String discipline, String grade, String email,
			String telephone) {
		super();
		this.student_id = student_id;
		this.realname = realname;
		this.college = college;
		this.discipline = discipline;
		this.grade = grade;
		this.email = email;
		this.telephone = telephone;
	}


	
	
	
}
