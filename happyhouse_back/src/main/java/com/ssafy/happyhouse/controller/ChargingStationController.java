package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.ChargingStation;
import com.ssafy.happyhouse.service.ChargingStationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/chargingStation")
public class ChargingStationController {

	@Autowired
	ChargingStationService service;
	
	@GetMapping
	public ResponseEntity<List<ChargingStation>> getNearByChargingStationBySidoCode(@RequestParam("lat") String lat,
			@RequestParam("lng") String lng,
			@RequestParam(value = "dist", required = false, defaultValue = "0.5") String dist,
			@RequestParam("sidoCode") String sidoCode) throws Exception, SQLException {
		return new ResponseEntity<List<ChargingStation>>(service.getChargingStationBySidoCode(lat, lng, sidoCode, Double.parseDouble(dist)), HttpStatus.OK);
	}
}
