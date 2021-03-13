package com.alpha.Wellness_Backend.ImageRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.Wellness_Backend.model.Image;

public interface ImageRepository {
	Image inserting(Image img);
	Image findById(int userId);
	boolean removeImage(int userId);
	List<Image> getAllImages();
}
