package com.alpha.Wellness_Backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.IBlogDao;
import com.alpha.Wellness_Backend.model.Blog;
import com.alpha.Wellness_Backend.service.IBlogService;

@Service
@Transactional
public class BlogServiceImpl implements IBlogService{

	@Autowired
	IBlogDao blogDao;
	
	@Override
	public List<Blog> getAllBlogs() {
		return blogDao.getAllBlogs();
	}

	@Override
	public List<Blog> getBlogsByStatus(String status) {
		return blogDao.getBlogsByStatus(status);
	}

	@Override
	public List<Blog> getUsersBlogs(int id) {
		return blogDao.getUsersBlogs(id);
	}

	@Override
	public Blog getBlogById(int blogId) {
		return blogDao.getBlogById(blogId);
	}

	@Override
	public boolean addBlog(Blog blog) {
		return blogDao.addBlog(blog);
	}

	@Override
	public boolean updateBlog(Blog blog) {
		return blogDao.updateBlog(blog);
	}

	@Override
	public boolean deleteBlog(Blog blog) {
		return blogDao.deleteBlog(blog);
	}
	
}
