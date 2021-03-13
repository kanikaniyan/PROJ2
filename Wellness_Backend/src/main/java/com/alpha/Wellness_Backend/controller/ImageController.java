package com.alpha.Wellness_Backend.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alpha.Wellness_Backend.model.Image;
import com.alpha.Wellness_Backend.model.Response;
import com.alpha.Wellness_Backend.service.DatabaseFileService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/image")
public class ImageController {

    @Autowired
    private DatabaseFileService fileStorageService;

    @PostMapping("/upload-image/{userId}")
    public Response uploadFile(@PathVariable("userId") int user_id, @RequestParam("imageFile") MultipartFile file) {
    	System.out.println("yeah");
    	Image fileName = fileStorageService.storeFile(file, user_id);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("image/downloadFile/")
                .path(String.valueOf(user_id))
                .toUriString();
        return new Response(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    @GetMapping("/downloadFile/{userId}")
    public Image downloadFile(@PathVariable int userId) {
        Image img = fileStorageService.getFile(userId);
        return img;
    }
    
    @GetMapping("/all-images")
    public List<Image> getAllImages() {
    	return fileStorageService.getAllImages();
    }
    
    @DeleteMapping("/remove-image/{userId}")
    public boolean removeImage(@PathVariable int userId) {
    	return fileStorageService.removeImg(userId);
    }
}
















//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.Optional;
//import java.util.zip.DataFormatException;
//import java.util.zip.Deflater;
//import java.util.zip.Inflater;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity.BodyBuilder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.alpha.Wellness_Backend.ImageRepo.ImageRepository;
//import com.alpha.Wellness_Backend.model.Image;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping(path = "image")
//public class ImageController {
//	
//	@Autowired
//	ImageRepository imageRepository;
//	
//	@PostMapping("/upload-image")
//	public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
//		System.out.println("image byte size : "+ file.getBytes().length);
//		Image img=new Image(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
//		imageRepository.save(img);
//		return ResponseEntity.status(HttpStatus.OK);
//	}
//	
//	public static byte[] compressBytes(byte[] data) {
//		Deflater deflater=new Deflater();
//		deflater.setInput(data);
//		deflater.finish();
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//		byte[] buffer=new byte[1024];
//		while (!deflater.finished()) {
//			int count = deflater.deflate(buffer);
//			outputStream.write(buffer, 0, count);
//		}
//		try {
//			outputStream.close();
//		}
//		catch (IOException e) {
//		}
//		System.out.println(outputStream.toByteArray().length);
//		return outputStream.toByteArray();
//	}
//	
//	@GetMapping(path = { "/get/{name}" })
//	public Image getImage(@PathVariable("name") String name) throws IOException {
//		final Optional<Image> retrievedImage = imageRepository.findByName(name);
//		Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
//		decompressBytes(retrievedImage.get().getPicByte()));
//		return img;
//	}
//	
//	public static byte[] decompressBytes(byte[] data) {
//		Inflater inflater = new Inflater();
//		inflater.setInput(data);
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//		byte[] buffer = new byte[1024];
//		try {
//			while (!inflater.finished()) {
//				int count = inflater.inflate(buffer);
//				outputStream.write(buffer, 0, count);
//			}
//			outputStream.close();
//		} catch (IOException ioe) {
//		} catch (DataFormatException e) {
//		}
//		return outputStream.toByteArray();
//	}
//}
