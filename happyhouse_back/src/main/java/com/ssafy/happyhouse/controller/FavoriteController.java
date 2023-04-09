package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.Favorite;
import com.ssafy.happyhouse.dto.Like;
import com.ssafy.happyhouse.service.FavoriteService;

@RestController
@RequestMapping("/favorite")
@CrossOrigin("*")
public class FavoriteController {

	private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private FavoriteService favoriteService;
	
	@GetMapping("{userid}")
	public ResponseEntity<List<Favorite>> searchFavorite(@PathVariable String userid) {
		logger.debug("userid: {}", userid);
		
		return new ResponseEntity<List<Favorite>>(favoriteService.searchFavorite(userid), HttpStatus.OK);
	}
	
	@DeleteMapping("{userid}/{aptCode}")
	public ResponseEntity<String> searchFavorite(@PathVariable String userid, @PathVariable String aptCode) {
		Like like = new Like(userid, aptCode);
		
		logger.debug("favorite info: {}", like);
		
		if (favoriteService.deleteLikedItem(like)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<String> addFavorite(@RequestBody Like like) {
		
		logger.debug("favorite info: {}", like);
		
		if (favoriteService.addLikedItem(like)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
