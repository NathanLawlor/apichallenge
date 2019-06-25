package com.challenge.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.api.model.Picture;
import com.challenge.api.repository.PictureRepository;
import com.challenge.api.service.GeocodeService;

@RestController	
@RequestMapping("/api")
public class PictureController {
	
    private PictureRepository picRepo;
    
    private GeocodeService geoService;
    
	@Autowired
    public PictureController(PictureRepository picRepo, GeocodeService geoService) {
    	this.picRepo = picRepo;
    	this.geoService = geoService;
    }
	
	@GetMapping("/pictures")
	public List<Picture> getAllPictures() {
		return picRepo.findAll();
	}
	
	@GetMapping("pictures/{pictureId}")
	public Optional<Picture> getPictureById(@PathVariable(value="pictureId") Long pictureId) {
		return picRepo.findById(pictureId);
	}
	
	@PostMapping("/uploadPicture")
	public Picture uploadPicture(@Valid @RequestBody Picture picture) throws Exception {
		Date date = new Date();
		picture.setUploadDate(date);
		
		String coords = picture.getCoordinates();
		String[] address = geoService.getLocation(coords);
		if(null != address) {
			picture.setCountry(address[0]);
			picture.setCity(address[1]);
		}
		return picRepo.save(picture);
	}
	
	@DeleteMapping("pictures/{pictureId}")
	public void updatePicture(@PathVariable(value="pictureId") Long pictureId) {
		picRepo.deleteById(pictureId);
	}
	
}
	