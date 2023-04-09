package com.ssafy.happyhouse.dto;

public class DongCode {
	String dongCode;
	String dong;
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public DongCode(String dongCode, String dong) {
		super();
		this.dongCode = dongCode;
		this.dong = dong;
	}
	@Override
	public String toString() {
		return "DongCode [dongCode=" + dongCode + ", dong=" + dong + "]";
	}

}
