package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.ChargingStation;
import com.ssafy.happyhouse.mapper.ChargingStationMapper;

@Service
public class ChargingStationServiceImpl implements ChargingStationService {

	@Autowired
	private ChargingStationMapper chargingStationMapper;

	@Override
	public List<ChargingStation> getChargingStationBySidoCode(String lat, String lng, String sidoCode, double dist)
			throws SQLException {
		List<ChargingStation> cList = chargingStationMapper.getChargingStationBySidoCode(sidoCode);

		List<ChargingStation> result = new ArrayList<>();
		double lat1 = Double.parseDouble(lat);
		double lng1 = Double.parseDouble(lng);
		for (ChargingStation c : cList) {
			double lat2 = Double.parseDouble(c.getLat());
			double lng2 = Double.parseDouble(c.getLng());
			if (distance(lat1, lng1, lat2, lng2) <= dist)
				result.add(c);
		}
		return result;
	}

	private double distance(double lat1, double lon1, double lat2, double lon2) {

		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		dist = dist * 1.609344;

		return (dist);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
