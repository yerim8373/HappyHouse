package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.DongCode;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.HouseMapService;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		//logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<DongCode>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<DongCode>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfo>> apt(@RequestParam Map<String, String> map) throws Exception {
		// logger.debug("input {}", map);
		// logger.debug("apt : {}", haHouseMapService.getAptInDong(map));
		return new ResponseEntity<List<HouseInfo>>(haHouseMapService.getAptInDong(map), HttpStatus.OK);
	}
	
	@GetMapping("/aptCode")
	public ResponseEntity<List<HouseInfo>> aptCode(@RequestParam("aptCode") String aptCode) throws Exception {
		return new ResponseEntity<List<HouseInfo>>(haHouseMapService.getAptInAptCode(aptCode), HttpStatus.OK);
	}
	
	@GetMapping("/aptSearch")
	public ResponseEntity<List<HouseInfo>> aptName(@RequestParam("aptName") String name) throws Exception {
		return new ResponseEntity<List<HouseInfo>>(haHouseMapService.getAptInAptName(name), HttpStatus.OK);
	}
}
