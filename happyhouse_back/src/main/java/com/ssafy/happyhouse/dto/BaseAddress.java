package com.ssafy.happyhouse.dto;

public class BaseAddress {
	String no;
	String sidoName;
	String gugunName;
	String dongName;
	String lat;
	String lng;
	
	public BaseAddress(String no, String sidoName, String gugunName, String dongName, String lat, String lng) {
		super();
		this.no = no;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.lat = lat;
		this.lng = lng;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "BasedAddress [no=" + no + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName="
				+ dongName + ", lat=" + lat + ", lng=" + lng + "]";
	}
}
