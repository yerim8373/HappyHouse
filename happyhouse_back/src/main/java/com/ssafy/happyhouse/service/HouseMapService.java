package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.DongCode;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<DongCode> getDongInGugun(String gugun) throws Exception;
	List<HouseInfo> getAptInDong(Map<String, String> map) throws Exception;
	List<HouseInfo> getAptInAptCode(String aptCode) throws Exception;
	List<HouseInfo> getAptInAptName(String name) throws Exception;
}
