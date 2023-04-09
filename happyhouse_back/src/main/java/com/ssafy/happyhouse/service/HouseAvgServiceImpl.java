package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.DealAvg;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.mapper.HouseAvgMapper;

@Service
public class HouseAvgServiceImpl implements HouseAvgService {

	@Autowired
	private HouseAvgMapper houseAvgMapper;
	
	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseAvgMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseAvgMapper.getGugunInSido(sido);
	}

	@Override
	public List<DealAvg> getAptDealInfo(String gugun) throws Exception {
		return houseAvgMapper.getAptDealInfo(gugun);
	}

}
