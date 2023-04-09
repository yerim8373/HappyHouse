package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.DealAvg;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

public interface HouseAvgService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<DealAvg> getAptDealInfo(String gugun) throws Exception;
}
