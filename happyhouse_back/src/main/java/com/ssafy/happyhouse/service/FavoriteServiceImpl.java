package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.Favorite;
import com.ssafy.happyhouse.dto.Like;
import com.ssafy.happyhouse.mapper.FavoriteMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	private FavoriteMapper favoriteMapper;
	
	@Override
	public List<Favorite> searchFavorite(String userid){
		return favoriteMapper.getFavoriteList(userid);
	}

	@Override
	public boolean deleteLikedItem(Like like) {
		return favoriteMapper.deleteLikedItem(like) == 1;
	}

	@Override
	public boolean addLikedItem(Like like) {
		return favoriteMapper.addLikedItem(like) == 1;
	}

}
