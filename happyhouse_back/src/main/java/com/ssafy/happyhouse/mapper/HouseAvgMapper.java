package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.DealAvg;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

@Mapper
public interface HouseAvgMapper {
	
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<DealAvg> getAptDealInfo(String gugun) throws SQLException;
}
