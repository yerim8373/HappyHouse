package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.Favorite;
import com.ssafy.happyhouse.dto.Like;
import com.ssafy.happyhouse.mapper.FavoriteMapper;


public interface FavoriteService {
	
	public List<Favorite> searchFavorite(String userid);
	public boolean deleteLikedItem(Like like);
	public boolean addLikedItem(Like like);
}
