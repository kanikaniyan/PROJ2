package com.alpha.Wellness_Backend.dao;

import java.util.List;

import com.alpha.Wellness_Backend.model.LikeStore;

public interface ILikeDao {
	boolean addLike(int userId, int blogId, int bUser_id);
	boolean removeLike(int userId, int blogId);
	void getLikeById();
	List<LikeStore> getAllLikesById(int userId);
	long getLikesByUserId(int bUserId);
}
