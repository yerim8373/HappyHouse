package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.happyhouse.dto.User;

public interface UserService {

	User login(User u) throws SQLException, Exception;
	
	public User userInfo(String id) throws Exception;

	boolean modifyInfo(User user) throws SQLException;

	void regist(User user) throws SQLException;

	boolean deleteUser(String id) throws SQLException;
}
