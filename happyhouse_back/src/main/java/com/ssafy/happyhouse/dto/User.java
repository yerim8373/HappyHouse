package com.ssafy.happyhouse.dto;

public class User {
	String id;
	String password;
	String email;
	String phone;
	String name;
	String no;
	
	public User() {
		super();
	}

	public User(String id, String password, String email, String phone, String name, String no) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", email=" + email + ", phone=" + phone + ", name=" + name
				+ ", no=" + no + "]";
	}
}
