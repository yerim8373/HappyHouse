package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.ChargingStation;

public interface ChargingStationService {
	List<ChargingStation> getChargingStationBySidoCode(String lat, String lng, String sidoCode, double dist) throws SQLException;
}
