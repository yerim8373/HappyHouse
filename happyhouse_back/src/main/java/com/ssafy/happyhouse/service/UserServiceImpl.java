package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.User;
import com.ssafy.happyhouse.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(User u) throws Exception {
		return userMapper.login(u);
	}

	@Override
	public boolean modifyInfo(User user) throws SQLException {
		return userMapper.modifyInfo(user) == 1;
	}

	@Override
	public void regist(User user) throws SQLException {
		userMapper.regist(user);
	}

	@Override
	public boolean deleteUser(String id) throws SQLException {
		return userMapper.deleteUser(id) == 1;
	}

	@Override
	public User userInfo(String id) throws Exception {
		return userMapper.userInfo(id);
	}

}
