package com.alpha.Wellness_Backend.daoImpl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.ILikeDao;
import com.alpha.Wellness_Backend.model.LikeStore;

@Repository("LikeDao")
@Transactional
public class LikeDaoImpl implements ILikeDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addLike(int userId, int blogId) {
		try {
			LikeStore like=new LikeStore();
			like.setUserId(userId);
			like.setBlogId(blogId);
			like.setLikes(true);
			sessionFactory.getCurrentSession().save(like);
			System.out.println(like);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeLike(int userId, int blogId) {
		LikeStore like=new LikeStore();
		String q="from LikeStore where userId='"+userId+"' and blogId='"+blogId+"'";
		Query query= sessionFactory.getCurrentSession().createQuery(q);
		like=(LikeStore) query.getSingleResult();
		like.setLikes(false);
		try {	
			sessionFactory.getCurrentSession().update(like);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
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
