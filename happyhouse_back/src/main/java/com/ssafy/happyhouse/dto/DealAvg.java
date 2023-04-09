package com.ssafy.happyhouse.dto;

public class DealAvg implements Comparable<DealAvg> {
	double dealAvg;
	String dongName;
	
	public DealAvg(double dealAvg, String dongName) {
		super();
		this.dealAvg = dealAvg;
		this.dongName = dongName;
	}
	
	public double getDealAvg() {
		return dealAvg;
	}

	public void setDealAvg(double dealAvg) {
		this.dealAvg = dealAvg;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	@Override
	public int compareTo(DealAvg o) {
		return Double.compare(o.dealAvg, this.dealAvg);
	}
}
