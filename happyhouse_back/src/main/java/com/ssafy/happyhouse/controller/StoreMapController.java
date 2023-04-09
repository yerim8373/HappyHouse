package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.StoreDto;
import com.ssafy.happyhouse.service.StoreService;

@RestController
@RequestMapping("/store")
@CrossOrigin("*")
public class StoreMapController {

	@Autowired
	StoreService storeService;

	@GetMapping("/name")
	@ResponseBody
	public ResponseEntity<List<StoreDto>> nearByStoreName(@RequestParam("lat") String lat,
			@RequestParam("lng") String lng,
			@RequestParam(value = "dist", required = false, defaultValue = "1.0") String dist,
			@RequestParam("name") String name) throws NumberFormatException, SQLException {
		
		return new ResponseEntity<List<StoreDto>>(storeService.listNearbyByName(lat, lng, name, Double.parseDouble(dist)), HttpStatus.OK);
	}
	
	@GetMapping("/type")
	@ResponseBody
	public ResponseEntity<List<StoreDto>> nearByStoreType(@RequestParam("lat") String lat,
			@RequestParam("lng") String lng,
			@RequestParam(value = "dist", required = false, defaultValue = "1") String dist,
			@RequestParam("type") String type) throws NumberFormatException, SQLException {
		
		return new ResponseEntity<List<StoreDto>>(storeService.listNearbyByType(lat, lng, type, Double.parseDouble(dist)), HttpStatus.OK);
	}
}
