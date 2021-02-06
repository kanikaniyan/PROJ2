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

import com.alpha.Wellness_Backend.model.Job;
import com.alpha.Wellness_Backend.service.IJobService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api")
public class JobController {

	@Autowired
	IJobService jobService;
	
	@PostMapping("save-job") 
	public boolean saveJob (@RequestBody Job job) {
		return jobService.addJob(job);
	}
	
	@GetMapping("job-list")
	public List<Job> allJobs() {
		return jobService.getAllJobs();
	}
	
	@DeleteMapping("delete-job/{job_id}")
	public boolean deleteJob(@PathVariable("job_id") int job_id) {
		Job job = jobService.getJobById(job_id);
		return jobService.deleteJob(job);
	}
	
	@GetMapping("job/{job_id}")
	public Job jobById(@PathVariable("job_id") int job_id) {
		return jobService.getJobById(job_id);
	}
	
	@PostMapping ("update-job/{job_id}")
	public boolean updateJob(@RequestBody Job job, @PathVariable("job_id") int job_id) {
		job.setJobId(job_id);
		return jobService.updateJob(job);
	}
	
}
