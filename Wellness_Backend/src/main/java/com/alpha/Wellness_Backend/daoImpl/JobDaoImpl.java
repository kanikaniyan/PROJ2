package com.alpha.Wellness_Backend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.IJobDao;
import com.alpha.Wellness_Backend.model.Job;

@Repository("jobDao")
@Transactional
public class JobDaoImpl implements IJobDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Job> getAllJobs() {
		return sessionFactory.getCurrentSession().createQuery("from job", Job.class).getResultList();
	}

	@Override
	public Job getJobById(int jobId) {
		return sessionFactory.getCurrentSession().get(Job.class, Integer.valueOf(jobId));
	}

	@Override
	public Job getJobByName(String jobName) {
		String query="from job where jobname=:jobname";
		return sessionFactory.getCurrentSession().createQuery(query, Job.class).setParameter("jobname", jobName).getSingleResult();
	}

	@Override
	public boolean addJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deactivateJob(int jobId) {
		try {
			Job job=getJobById(jobId);
			job.setActive(false);
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
