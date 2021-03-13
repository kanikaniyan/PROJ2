package com.alpha.Wellness_Backend.service;

import java.util.List;

import com.alpha.Wellness_Backend.model.LikeStore;

public interface ILikeService {
	boolean addLike(int user_id, int blog_id, int bUser_id);
	boolean removeLike(int user_id, int blog_id);
	void getLikeById();
	List<LikeStore> getAllLikesById(int user_id);
	long getLikesByUserId(int bUserId);
}
