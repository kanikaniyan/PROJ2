package com.alpha.Wellness_Backend.dao;

import java.util.List;

import com.alpha.Wellness_Backend.model.BlogComments;

public interface IBlogCommentsDao {
	
	List<BlogComments> getAllBlogsComments();
	BlogComments getBlogCommentsById(int blogCommentsId);
	boolean addBlogComments(BlogComments blogComments);
	boolean updateBlogComments(BlogComments blogComments);
	boolean deleteBlogComments(BlogComments blogComments);
	List<BlogComments>getCommentsByBlog(int blogId);
}
