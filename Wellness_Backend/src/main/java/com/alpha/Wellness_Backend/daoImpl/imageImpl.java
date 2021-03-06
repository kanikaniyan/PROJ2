package com.alpha.Wellness_Backend.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.ImageRepo.ImageRepository;
import com.alpha.Wellness_Backend.model.Image;

@Repository("imageRepo")
@Transactional
public class imageImpl implements ImageRepository{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Image inserting(Image img) {
		sessionFactory.getCurrentSession().save(img);
		System.out.println("img");
		return img;
	}

	@Override
	public Image findById(int userId) {
		String query="from Image where userId=:userId";
		try {
		return sessionFactory.getCurrentSession().createQuery(query, Image.class).setParameter("userId", userId).getSingleResult();
		}
		catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public boolean removeImage(int userId) {
		try {
			sessionFactory.getCurrentSession().delete(findById(userId));
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Image> getAllImages() {
		try {
			return sessionFactory.getCurrentSession().createQuery("from Image", Image.class).getResultList();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
