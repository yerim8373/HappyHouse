package com.ssafy.happyhouse.dto;

public class ChargingStation {
	String id;
	String address;
	String type;
	String name;
	String lat;
	String lng;
	String sidoCode;

	public ChargingStation(String id, String address, String type, String name, String lat, String lng,
			String sidoCode) {
		super();
		this.id = id;
		this.address = address;
		this.type = type;
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.sidoCode = sidoCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}

	@Override
	public String toString() {
		return "ChargingStation [id=" + id + ", address=" + address + ", type=" + type + ", name=" + name + ", lat="
				+ lat + ", lng=" + lng + ", sidoCode=" + sidoCode + "]";
	}
}
