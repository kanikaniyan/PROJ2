package com.alpha.Wellness_Backend.service;

import java.util.List;

import com.alpha.Wellness_Backend.model.BlogComments;

public interface IBlogCommentsService {
	
	List<BlogComments> getAllBlogsComments();
	BlogComments getBlogCommentsById(int blogCommentsId);
	boolean addBlogComments(BlogComments blogComments);
	boolean updateBlogComments(BlogComments blogComments);
	boolean deleteBlogComments(BlogComments blogComments);
	List<BlogComments>getCommentsByBlog(int blogId);
}
