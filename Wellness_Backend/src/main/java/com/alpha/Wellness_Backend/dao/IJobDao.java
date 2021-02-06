package com.alpha.Wellness_Backend.dao;

import java.util.List;

import com.alpha.Wellness_Backend.model.Job;

public interface IJobDao {
	
	List<Job> getAllJobs(); 
	Job getJobById(int jobId);
	Job getJobByName(String jobName);
	boolean addJob(Job job);
	boolean updateJob(Job job);
	boolean deleteJob(Job job);
	boolean deactivateJob(int jobId);
}
