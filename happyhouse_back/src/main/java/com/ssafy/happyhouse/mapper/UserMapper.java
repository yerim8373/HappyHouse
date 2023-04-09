package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.User;

@Mapper
public interface UserMapper {

	User login(User u) throws Exception;
	int modifyInfo(User user) throws SQLException;
	void regist(User user) throws SQLException;
	int deleteUser(String id) throws SQLException;
	User userInfo(String id);
	
}
