package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.DongCode;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;


@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<DongCode> getDongInGugun(String gugun) throws SQLException;
	//List<HouseInfo> getAptInDong(String dong) throws SQLException;
	List<HouseInfo> getAptInAptcode(String aptcode) throws SQLException;
	List<HouseInfo> getAptInAptName(String name) throws SQLException;
	List<HouseInfo> getAptInDong(Map<String, String> map) throws SQLException;
}
