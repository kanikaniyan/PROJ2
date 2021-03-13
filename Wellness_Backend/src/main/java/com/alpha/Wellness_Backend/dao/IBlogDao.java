package com.alpha.Wellness_Backend.dao;

import java.util.List;

import com.alpha.Wellness_Backend.model.Blog;

public interface IBlogDao {
	List<Blog> getAllBlogs();
	List<Blog> getBlogsByStatus(String status);
	List<Blog> getUsersBlogs(int id);
	Blog getBlogById(int userId);
	boolean addBlog(Blog blog);
	boolean updateBlog(Blog blog);
	boolean deleteBlog(int blogId);
	List<Blog> getAllNotApprovedBlogs();
	boolean approveABlog(int blogId);
	List<Blog> getAllApprovedBlogById(int userId);
}