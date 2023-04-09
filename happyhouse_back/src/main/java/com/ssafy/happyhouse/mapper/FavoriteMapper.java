package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Favorite;
import com.ssafy.happyhouse.dto.Like;

@Mapper
public interface FavoriteMapper {
	public List<Favorite> getFavoriteList(String userid);
	public int deleteLikedItem(Like like);
	public int addLikedItem(Like like);
}
