package com.alpha.Wellness_Backend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.dao.IUserDao;
import com.alpha.Wellness_Backend.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements IUserDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<User> userListbyStatus(String status) {
		String q="from User where status='"+status+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		return query.getResultList();
	}

	@Override
	public List<User> getAllUsers() {
		
		return sessionFactory.getCurrentSession().createQuery("from user", User.class).getResultList();
	}

	@Override
	public User getUserById(int userId) {
		return sessionFactory.getCurrentSession().get(User.class,  Integer.valueOf(userId));
	}

	@Override
	public User getUserByUsername(String userName) {
		String query="from User where username=:username";
		return sessionFactory.getCurrentSession().createQuery(query, User.class).setParameter("username", userName).getSingleResult();
	}

	@Override
	public User validateUser(User user) {
		String username=user.getUsername();
		String password=user.getPassword();
		String q="from User where username='"+username+"' and password='"+password+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		try {
			user= (User)query.getSingleResult();
			return user;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(int userId) {
		try {
			sessionFactory.getCurrentSession().delete(getUserById(userId));
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deactivateUser(int userId) {
		try {
			User user=getUserById(userId);
			user.setEnabled(false);
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUserProfile(String file, Integer userId) {
		String q="update User set profile=:filename where userId=:id";
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		query.setParameter("id", (Integer)userId);
		query.setParameter("fileName", file);
		try {
			query.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
