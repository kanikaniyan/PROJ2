package com.alpha.Wellness_Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.Wellness_Backend.model.LikeStore;
import com.alpha.Wellness_Backend.model.User;
import com.alpha.Wellness_Backend.service.ILikeService;
import com.alpha.Wellness_Backend.service.IUserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class LikeController {
	
	@Autowired
	ILikeService likeService;
	
	@PostMapping("add-like/{userId}/{blogId}/{bUserId}")
	public boolean addLike (@PathVariable("userId") int user_id, @PathVariable("blogId") int blog_id, @PathVariable("bUserId") int bUser_id) {
		return likeService.addLike(user_id, blog_id, bUser_id);
	}
	
	@PostMapping("remove-like/{userId}/{blogId}")
	public boolean removeLike (@PathVariable("userId") int user_id, @PathVariable("blogId") int blog_id) {
		return likeService.removeLike(user_id, blog_id);
	}
	
	@GetMapping("like-list-by-id/{userId}")
	public List<LikeStore> getAllLikesById (@PathVariable("userId") int user_id) {
		return likeService.getAllLikesById(user_id);
	}
	
	@GetMapping("likes-for-user/{userId}")
	public long getLikesByUserId(@PathVariable("userId") int bUserId) {
		return likeService.getLikesByUserId(bUserId);
	}
}
