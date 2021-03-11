package com.alpha.Wellness_Backend.service;

public interface ILikeService {
	boolean addLike(int user_id, int blog_id);
	boolean removeLike(int user_id, int blog_id);
	void getLikeById();
	void getAllLikesById();
}
