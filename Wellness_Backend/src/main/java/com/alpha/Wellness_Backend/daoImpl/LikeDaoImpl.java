package com.alpha.Wellness_Backend.daoImpl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.ILikeDao;
import com.alpha.Wellness_Backend.model.LikeStore;
import com.alpha.Wellness_Backend.model.User;

@Repository("LikeDao")
@Transactional
public class LikeDaoImpl implements ILikeDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addLike(int userId, int blogId, int bUser_id) {
		LikeStore like = null;
		String q="from LikeStore where userId='"+userId+"' and blogId='"+blogId+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		try {
			like = new LikeStore();
			like=(LikeStore) query.getSingleResult();
			like.setLikes(true);
			sessionFactory.getCurrentSession().update(like);
			return true;
		}
		catch(NoResultException nex) {
			System.out.println("No result found! creating new...");
			like = new LikeStore();
			like.setUserId(userId);
			like.setBlogId(blogId);
			like.setbUserId(bUser_id);
			like.setLikes(true);
			try {
				sessionFactory.getCurrentSession().save(like);
				System.out.println(like);
				return true;
			}
			catch(Exception ex) {
				ex.printStackTrace();
				return false;
			}
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
	public List<LikeStore> getAllLikesById(int userId) {
		return (List<LikeStore>) sessionFactory.getCurrentSession().createQuery("from LikeStore where userId='"+userId+"'", LikeStore.class).getResultList();
	}

	@Override
	public long getLikesByUserId(int bUserId) {
//		return sessionFactory.getCurrentSession().createQuery("select count(likes) from likeStore where  likes=true and bUserId='"+bUserId+"'", LikeStore.class).getFirstResult();
		String SQL_QUERY = "select count(l.likes) from LikeStore l where likes=true and bUserId='"+bUserId+"'";
		  Query query = sessionFactory.getCurrentSession().createQuery(SQL_QUERY);
		  long count = (long)query.uniqueResult();
		  System.out.println(count);
		  return count;
	}

}
