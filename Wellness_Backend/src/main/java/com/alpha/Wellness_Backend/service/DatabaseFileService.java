package com.alpha.Wellness_Backend.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.Wellness_Backend.ImageRepo.ImageRepository;
import com.alpha.Wellness_Backend.exception.FileNotFoundException;
import com.alpha.Wellness_Backend.exception.FileStorageException;
import com.alpha.Wellness_Backend.model.Image;

@Service
@Component
public class DatabaseFileService {

    @Autowired
    private ImageRepository dbFileRepository;

    public Image storeFile(MultipartFile file, int userId) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Image img=new Image();
            
            img.setFileName(fileName);
            img.setFileType(file.getContentType());
            img.setData(file.getBytes());
            img.setUserId(userId);
            
            if(dbFileRepository.inserting(img)!=null) {
            	System.out.println("ok");
            	return dbFileRepository.inserting(img);
            }else {
            	return null;
            }
            
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    public Image getFile(int userId) {
        return dbFileRepository.findById(userId);
    }

	public boolean removeImg(int userId) {
		return dbFileRepository.removeImage(userId);
	}
}