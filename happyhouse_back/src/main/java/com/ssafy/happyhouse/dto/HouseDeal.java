package com.ssafy.happyhouse.dto;

public class HouseDeal {
	String no;
	String aptCode;
	String dealAmount;
	String dealYear;
	String dealDay;
	String area;
	String floor;
	String type;
	String rentMoney;
	
	public HouseDeal(String no, String aptCode, String dealAmount, String dealYear, String dealDay, String area,
			String floor, String type, String rentMoney) {
		super();
		this.no = no;
		this.aptCode = aptCode;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.type = type;
		this.rentMoney = rentMoney;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRentMoney() {
		return rentMoney;
	}

	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}

	@Override
	public String toString() {
		return "HouseDeal [no=" + no + ", aptCode=" + aptCode + ", dealAmount=" + dealAmount + ", dealYear=" + dealYear
				+ ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + ", type=" + type + ", rentMoney="
				+ rentMoney + "]";
	}
}
