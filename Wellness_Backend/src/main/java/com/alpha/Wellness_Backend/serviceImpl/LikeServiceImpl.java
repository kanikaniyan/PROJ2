package com.alpha.Wellness_Backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.ILikeDao;
import com.alpha.Wellness_Backend.dao.IUserDao;
import com.alpha.Wellness_Backend.service.ILikeService;

@Service
@Transactional
public class LikeServiceImpl implements ILikeService{
	
	@Autowired
	ILikeDao likeDao;
	
	@Override
	public boolean addLike(int userId, int blogId) {
		return likeDao.addLike(userId, blogId);
	}

	@Override
	public boolean removeLike(int userId, int blogId) {
		return likeDao.removeLike(userId, blogId);
		
	}

	@Override
	public void getLikeById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAllLikesById() {
		// TODO Auto-generated method stub
		
	}
}
