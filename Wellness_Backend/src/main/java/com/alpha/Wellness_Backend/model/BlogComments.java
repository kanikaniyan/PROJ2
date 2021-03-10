package com.alpha.Wellness_Backend.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Component
@Entity
public class BlogComments implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int blogCommentId;
	int userId;
	String username;
	String ProfileId;
	String title;
	int noOfLikes;
	String blogComment;
	Date currentDate;
	@ManyToOne
	@JoinColumn(name="BlogId")
	@JsonBackReference
	Blog blog;
	
	public String getBlogComment() {
		return blogComment;
	}
	public void setBlogComment(String blogComment) {
		this.blogComment = blogComment;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public int getBlogCommentId() {
		return blogCommentId;
	}
	public void setBlogCommentId(int blogCommentId) {
		this.blogCommentId = blogCommentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfileId() {
		return ProfileId;
	}
	public void setProfileId(String profileId) {
		ProfileId = profileId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNoOfLikes() {
		return noOfLikes;
	}
	public void setNoOfLikes(int noOfLikes) {
		this.noOfLikes = noOfLikes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
