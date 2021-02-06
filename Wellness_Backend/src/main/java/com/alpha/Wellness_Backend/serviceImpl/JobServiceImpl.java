package com.alpha.Wellness_Backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.IJobDao;
import com.alpha.Wellness_Backend.model.Job;
import com.alpha.Wellness_Backend.service.IJobService;

@Service
@Transactional
public class JobServiceImpl implements IJobService{

	@Autowired
	IJobDao jobDao;
	
	@Override
	public List<Job> getAllJobs() {
		return jobDao.getAllJobs();
	}

	@Override
	public Job getJobById(int jobId) {
		return jobDao.getJobById(jobId);
	}

	@Override
	public Job getJobByName(String jobName) {
		return jobDao.getJobByName(jobName);
	}

	@Override
	public boolean addJob(Job job) {
		return jobDao.addJob(job);
	}

	@Override
	public boolean updateJob(Job job) {
		return jobDao.updateJob(job);
	}

	@Override
	public boolean deleteJob(Job job) {
		return jobDao.deleteJob(job);
	}

	@Override
	public boolean deactivateJob(int jobId) {
		return jobDao.deactivateJob(jobId);
	}

}
