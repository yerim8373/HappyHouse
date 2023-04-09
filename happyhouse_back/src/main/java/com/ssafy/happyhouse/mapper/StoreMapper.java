package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.StoreDto;

@Mapper
public interface StoreMapper {

	List<StoreDto> getStoreByName(String name) throws SQLException;
	List<StoreDto> getStoreByType(String type) throws SQLException;
}
