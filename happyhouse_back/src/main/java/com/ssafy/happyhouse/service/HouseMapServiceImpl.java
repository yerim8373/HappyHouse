package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.DongCode;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.mapper.HouseMapMapper;


@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<DongCode> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfo> getAptInDong(Map<String, String> map) throws Exception {
		return houseMapMapper.getAptInDong(map);
	}

	@Override
	public List<HouseInfo> getAptInAptCode(String aptCode) throws Exception {
		return houseMapMapper.getAptInAptcode(aptCode);
	}

	@Override
	public List<HouseInfo> getAptInAptName(String name) throws Exception {
		return houseMapMapper.getAptInAptName(name);
	}

}
