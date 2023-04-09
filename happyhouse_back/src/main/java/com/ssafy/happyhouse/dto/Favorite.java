package com.ssafy.happyhouse.dto;

import java.util.Random;

public class Favorite {
	String aptCode;
	String aptname;
	int buildyear;
	String dealAmount;
	String img;
	
	@Override
	public String toString() {
		return "Favorite [aptcode=" + aptCode + ", aptname=" + aptname + ", buildyear=" + buildyear + ", dealAmount="
				+ dealAmount + ", img=" + img + "]";
	}

	public Favorite(String aptCode, String aptname, int buildyear, String dealAmount) {
		super();
		this.aptCode = aptCode;
		this.aptname = aptname;
		this.buildyear = buildyear;
		this.dealAmount = dealAmount;
		this.img = getImgPath(); 
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

	
	public String getAptcode() {
		return aptCode;
	}

	public void setAptcode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptname() {
		return aptname;
	}

	public void setAptname(String aptname) {
		this.aptname = aptname;
	}

	public int getBuildyear() {
		return buildyear;
	}

	public void setBuildyear(int buildyear) {
		this.buildyear = buildyear;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
}