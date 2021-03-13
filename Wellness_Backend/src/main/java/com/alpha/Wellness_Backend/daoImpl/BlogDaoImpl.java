package com.alpha.Wellness_Backend.daoImpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.IBlogDao;
import com.alpha.Wellness_Backend.model.Blog;

@Repository("BlogDao")
@Transactional
public class BlogDaoImpl implements IBlogDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Blog> getAllBlogs() {
		return sessionFactory.getCurrentSession().createQuery("from Blog where status='Approved'", Blog.class).getResultList();
	}

	@Override
	public List<Blog> getBlogsByStatus(String status) {
		String q="from Blog where status='"+status+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		return query.getResultList();
	}

	@Override
	public List<Blog> getUsersBlogs(int userId) {
		String q="select blogTitle, blogContent from Blog where blogId='"+userId+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		return query.getResultList();
	}

	@Override
	public Blog getBlogById(int blogId) {
		return sessionFactory.getCurrentSession().get(Blog.class, Integer.valueOf(blogId));
	}

	@Override
	public boolean addBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBlog(int blogId) {
		try {
			sessionFactory.getCurrentSession().delete(getBlogById(blogId));
			try {
				sessionFactory.getCurrentSession().createQuery("delete from LikeStore where blogId='"+blogId+"'");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Blog> getAllNotApprovedBlogs() {
		return sessionFactory.getCurrentSession().createQuery("from Blog where status='Approval Pending'",Blog.class).getResultList();
	}

	@Override
	public boolean approveABlog(int blogId) {
		try {
			Blog blog=getBlogById(blogId);
			blog.setStatus("Approved");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Blog> getAllApprovedBlogById(int userId) {
		String q="from Blog where userId='"+userId+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		return query.getResultList();
	}
}
