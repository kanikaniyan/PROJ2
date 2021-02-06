package com.alpha.Wellness_Backend.service;

import java.util.List;

import com.alpha.Wellness_Backend.model.Blog;

public interface IBlogService {
	List<Blog> getAllBlogs();
	List<Blog> getBlogsByStatus(String status);
	List<Blog> getUsersBlogs(int id);
	Blog getBlogById(int blogId);
	boolean addBlog(Blog blog);
	boolean updateBlog(Blog blog);
	boolean deleteBlog(Blog blog);
}
