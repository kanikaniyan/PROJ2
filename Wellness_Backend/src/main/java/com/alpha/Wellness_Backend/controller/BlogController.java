package com.alpha.Wellness_Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.Wellness_Backend.model.Blog;
import com.alpha.Wellness_Backend.service.IBlogService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class BlogController {
	
	@Autowired
	IBlogService blogService;
	
	@PostMapping("add-blog")
	public boolean saveBlog(@RequestBody Blog blog ) {
		System.out.println(blog.getBlogPosted());
		return blogService.addBlog(blog);
	}

	@GetMapping("blog-list")
	public List<Blog> allApprovedBlogs() {
		return blogService.getAllBlogs();
	}
	
	@DeleteMapping("delete-blog/{blogId}")
	public boolean deleteUser(@PathVariable("blogId") int blogId) {
		return blogService.deleteBlog(blogId);
	}
	
	@GetMapping("oneBlog/{blogId}")
	public Blog blogById(@PathVariable("blogId") int blogId) {
		return blogService.getBlogById(blogId);
	}
	
	@PostMapping("update-blog/{blogId}")
	public boolean updateBlog(@RequestBody Blog blog, @PathVariable("blogId") int blogId) {
		blog.setBlogId(blogId);
		return blogService.updateBlog(blog);
	}
	
	@GetMapping("notApprovedBlogs")
	public List<Blog> getAllNotApprovedBlog() {
		return blogService.getAllNotApprovedBlog();
	}
	
	@PostMapping("approveABlog/{blogId}")
	public boolean approveABlog(@RequestBody Blog blog, @PathVariable("blogId") int blogId) {
		return blogService.approveABlog(blogId);
	}
	
	@GetMapping("allApprovedBlogs/{userId}")
	public List<Blog> getAllApprovedBlogById(@PathVariable("userId") int userId) {
		return blogService.getAllApprovedBlogById(userId);
	}
	
}
