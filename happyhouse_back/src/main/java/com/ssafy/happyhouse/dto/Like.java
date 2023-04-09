package com.ssafy.happyhouse.dto;

public class Like {
	String userid;
	String aptCode;
	
	public Like() {
		super();
	}

	@Override
	public String toString() {
		return "Like [userid=" + userid + ", aptCode=" + aptCode + "]";
	}

	public Like(String userid, String aptCode) {
		super();
		this.userid = userid;
		this.aptCode = aptCode;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
}
