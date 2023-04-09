package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.ChargingStation;

@Mapper
public interface ChargingStationMapper {
	List<ChargingStation> getChargingStationBySidoCode(String sidoCode) throws SQLException;
}
