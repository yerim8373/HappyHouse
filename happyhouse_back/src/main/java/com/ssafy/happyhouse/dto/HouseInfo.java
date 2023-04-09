package com.ssafy.happyhouse.dto;

import java.util.Random;

public class HouseInfo {
	String aptName;
	String dongCode;
	String lat;
	String lng;
	String no;
	String dealAmount;
	String dealYear;
	String dealMonth;
	String dealDay;
	String area;
	String floor;
	String aptCode;
	String img;
	String buildYear;
	
	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public HouseInfo(String aptName, String dongCode, String lat, String lng, String buildYear, String no, String dealAmount,
			String dealYear, String dealMonth, String dealDay, String area, String floor, String aptCode) {
		super();
		this.aptName = aptName;
		this.dongCode = dongCode;
		this.lat = lat;
		this.lng = lng;
		this.buildYear = buildYear;
		this.no = no;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.aptCode = aptCode;
		this.img = getImgPath(); 
	}
	
	@Override
	public String toString() {
		return "HouseInfo [aptName=" + aptName + ", dongCode=" + dongCode + ", lat=" + lat + ", lng=" + lng + ", no="
				+ no + ", dealAmount=" + dealAmount + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth
				+ ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + ", aptCode=" + aptCode + ", img="
				+ img + ", buildYear=" + buildYear + "]";
	}

	private String getImgPath() {
		Random random = new Random();
		int value = random.nextInt(30) + 1;
		
		return "http://localhost:8081/static/images/apt/"+ value + ".jpg";
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getDealDay() {
		return dealDay;
	}

	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}
	
	
}
