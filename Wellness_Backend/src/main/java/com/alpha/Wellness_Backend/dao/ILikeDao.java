package com.alpha.Wellness_Backend.dao;

public interface ILikeDao {
	boolean addLike(int userId, int blogId);
	boolean removeLike(int userId, int blogId);
	void getLikeById();
	void getAllLikesById();
}
