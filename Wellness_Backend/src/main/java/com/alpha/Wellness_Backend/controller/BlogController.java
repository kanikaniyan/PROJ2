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
import com.alpha.Wellness_Backend.model.User;
import com.alpha.Wellness_Backend.service.IBlogService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class BlogController {
	
	@Autowired
	IBlogService blogService;
	
	@PostMapping("save-blog")
	public boolean saveBlog(@RequestBody Blog blog ) {
		return blogService.addBlog(blog);
	}

	@PostMapping("blog-list")
	public List<Blog> allBlogs() {
		return blogService.getAllBlogs();
	}
	
	@DeleteMapping("delete-blog/{blog}")
	public boolean deleteUser(@PathVariable("blog") Blog blog) {
		return blogService.deleteBlog(blog);
	}
	
	@GetMapping("blog/{blogId}")
	public Blog blogById(@PathVariable("blogId") int blogId) {
		return blogService.getBlogById(blogId);
	}
	
	@PostMapping("update-blog/{blogId}")
	public boolean updateBlog(@RequestBody Blog blog, @PathVariable("blogId") int blogId) {
		blog.setBlogId(blogId);
		return blogService.updateBlog(blog);
	}
	
}
