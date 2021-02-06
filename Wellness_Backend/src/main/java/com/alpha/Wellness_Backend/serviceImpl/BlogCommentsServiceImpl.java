package com.alpha.Wellness_Backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.IBlogCommentsDao;
import com.alpha.Wellness_Backend.model.BlogComments;
import com.alpha.Wellness_Backend.service.IBlogCommentsService;

@Service
@Transactional
public class BlogCommentsServiceImpl implements IBlogCommentsService{

	@Autowired
	IBlogCommentsDao blogCommentsDao;
	
	@Override
	public List<BlogComments> getAllBlogsComments() {
		return blogCommentsDao.getAllBlogsComments();
	}

	@Override
	public BlogComments getBlogCommentsById(int blogCommentsId) {
		return blogCommentsDao.getBlogCommentsById(blogCommentsId);
	}

	@Override
	public boolean addBlogComments(BlogComments blogComments) {
		return blogCommentsDao.addBlogComments(blogComments);
	}

	@Override
	public boolean updateBlogComments(BlogComments blogComments) {
		return blogCommentsDao.updateBlogComments(blogComments);
	}

	@Override
	public boolean deleteBlogComments(BlogComments blogComments) {
		return blogCommentsDao.deleteBlogComments(blogComments);
	}

	@Override
	public List<BlogComments> getCommentsByBlog(int blogId) {
		return blogCommentsDao.getCommentsByBlog(blogId);
	}
	
}
