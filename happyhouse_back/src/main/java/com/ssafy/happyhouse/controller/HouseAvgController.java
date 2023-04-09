package com.ssafy.happyhouse.controller;

import java.util.Collections;
import java.util.List;

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

import com.ssafy.happyhouse.dto.DealAvg;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.HouseAvgService;

@RestController
@RequestMapping("/avg")
@CrossOrigin("*")
public class HouseAvgController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	
	@Autowired
	private HouseAvgService houseAvgService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		System.out.println("?????????????????");
		logger.debug("sido : {}", houseAvgService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseAvgService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseAvgService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<DealAvg>> apt(@RequestParam("gugun") String gugun) throws Exception {
		List<DealAvg> list = houseAvgService.getAptDealInfo(gugun);
		Collections.sort(list);
		return new ResponseEntity<List<DealAvg>>(list, HttpStatus.OK);
	}
}
