package com.alpha.Wellness_Backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.ILikeDao;
import com.alpha.Wellness_Backend.dao.IUserDao;
import com.alpha.Wellness_Backend.model.LikeStore;
import com.alpha.Wellness_Backend.service.ILikeService;

@Service
@Transactional
public class LikeServiceImpl implements ILikeService{
	
	@Autowired
	ILikeDao likeDao;
	
	@Override
	public boolean addLike(int userId, int blogId, int bUser_id) {
		return likeDao.addLike(userId, blogId, bUser_id);
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
	public List<LikeStore> getAllLikesById(int userId) {
		return likeDao.getAllLikesById(userId);
		
	}

	@Override
	public long getLikesByUserId(int bUserId) {
		return likeDao.getLikesByUserId(bUserId);
	}
}
