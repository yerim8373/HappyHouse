package com.ssafy.happyhouse.dto;

public class StoreDto {
	private String id;
	private String name;
	private String dongcode;
	private String type;
	private String lat;
	private String lng;
	
	public StoreDto() {
		super();
	}

	public StoreDto(String id, String name, String dongcode, String type, String lat, String lng) {
		super();
		this.id = id;
		this.name = name;
		this.dongcode = dongcode;
		this.type = type;
		this.lat = lat;
		this.lng = lng;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		return "StoreInfo [id=" + id + ", name=" + name + ", dongcode=" + dongcode + ", type=" + type + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}
}
