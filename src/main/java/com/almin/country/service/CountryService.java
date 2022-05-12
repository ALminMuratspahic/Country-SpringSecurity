package com.almin.country.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.almin.country.entity.Country;
import com.almin.country.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository repo;

	// get All Country
	public List<Country> getAllCountry() {
		return repo.findAll();
	}

	// get one Country by ID
	public Country getOneCountry(Integer id) {
		Optional<Country> opt = repo.findById(id);
		Country country;
		if (opt.isPresent()) {
			country = opt.get();
		} else {
			country = null;
		}
		return country;
	}

	// Save
	public Country saveCountry(Country country) {

		return repo.save(country);
	}

	// Delete
	public void deleteCountry(Integer id) {
		repo.deleteById(id);
	}

	// Filter
	public List<Country> filterCountry(String keyword) {

		if (keyword == null || keyword.isEmpty()) {
			return repo.findAll();
		} else {
			return repo.filter(keyword);
		}
	}

	// SORT
	public List<Country> sortDecending() {
		return repo.sortCountryDESC();
	}

	public List<Country> sortAscending() {
		return repo.sortCountryASC();
	}

	public void saveImage(MultipartFile imageFile) throws Exception {

		String folder = "D:\\photos\\";
		byte[] bytes = imageFile.getBytes();// byte array represent image

		Path path = Paths.get(folder + imageFile.getOriginalFilename());
		Files.write(path, bytes);

	}

}
