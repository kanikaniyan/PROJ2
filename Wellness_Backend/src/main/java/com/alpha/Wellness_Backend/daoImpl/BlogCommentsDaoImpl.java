package com.alpha.Wellness_Backend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.IBlogCommentsDao;
import com.alpha.Wellness_Backend.model.BlogComments;

@Repository("blogCommentsDao")
@Transactional
public class BlogCommentsDaoImpl implements IBlogCommentsDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<BlogComments> getAllBlogsComments() {
		return sessionFactory.getCurrentSession().createQuery("from blogcomments", BlogComments.class).getResultList();
	}

	@Override
	public BlogComments getBlogCommentsById(int blogCommentsId) {
		return sessionFactory.getCurrentSession().get(BlogComments.class, Integer.valueOf(blogCommentsId));
	}

	@Override
	public boolean addBlogComments(BlogComments blogComments) {
		try {
			sessionFactory.getCurrentSession().save(blogComments);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBlogComments(BlogComments blogComments) {
		try {
			sessionFactory.getCurrentSession().update(blogComments);
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBlogComments(BlogComments blogComments) {
		try {
			sessionFactory.getCurrentSession().delete(blogComments);
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<BlogComments> getCommentsByBlog(int blogId) {
		// TODO Auto-generated method stub
		return null;
	}

}
