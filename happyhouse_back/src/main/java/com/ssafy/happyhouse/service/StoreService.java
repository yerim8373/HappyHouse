package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.StoreDto;

public interface StoreService {
	List<StoreDto> listNearbyByName(String lat, String lng, String name, double dist) throws SQLException;
	List<StoreDto> listNearbyByType(String lat, String lng, String type, double dist) throws SQLException;
}
