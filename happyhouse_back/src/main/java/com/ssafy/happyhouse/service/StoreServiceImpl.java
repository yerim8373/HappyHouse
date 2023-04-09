package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.StoreDto;
import com.ssafy.happyhouse.mapper.StoreMapper;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreMapper storeMapper;

	@Override
	public List<StoreDto> listNearbyByName(String lat, String lng, String name, double dist) throws SQLException {

		List<StoreDto> sList = storeMapper.getStoreByName(name);

		List<StoreDto> ret = new ArrayList<>();
		for (StoreDto s : sList) {
			double lat1 = Double.parseDouble(s.getLat());
			double lon1 = Double.parseDouble(s.getLng());
			double lat2 = Double.parseDouble(lat);
			double lon2 = Double.parseDouble(lng);
			if (distance(lat1, lon1, lat2, lon2) <= dist)
				ret.add(s);
		}

		return ret;
	}

	@Override
	public List<StoreDto> listNearbyByType(String lat, String lng, String type, double dist) throws SQLException {
		List<StoreDto> sList = storeMapper.getStoreByType(type);

		List<StoreDto> ret = new ArrayList<>();
		for (StoreDto s : sList) {
			double lat1 = Double.parseDouble(s.getLat());
			double lon1 = Double.parseDouble(s.getLng());
			double lat2 = Double.parseDouble(lat);
			double lon2 = Double.parseDouble(lng);
			if (distance(lat1, lon1, lat2, lon2) <= dist)
				ret.add(s);
		}

		return ret;
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
