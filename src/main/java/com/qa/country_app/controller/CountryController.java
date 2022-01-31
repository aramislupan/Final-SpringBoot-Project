package com.qa.country_app.controller;

import java.net.http.HttpHeaders;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.country_app.data.entity.Country;
import com.qa.country_app.service.CountryService;

@RestController
@RequestMapping (path = "/country")
public class CountryController {
	
	private CountryService countryService;
	
	@Autowired
	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}
	
	//Read all
	@GetMapping
	public ResponseEntity<List<Country>> getCountries() {
		ResponseEntity<List<Country>> countries = ResponseEntity.ok(countryService.getAll());
		return countries;
	}
	
	//Read by Id
	@RequestMapping (path = "/{id}", method = {RequestMethod.GET})
	public Country getUserById(@PathVariable("id") long id) {
		return null;
	}
	
	//Create
//	@PostMapping
//	public Country createCountry(@Valid @RequestBody Country country) {
//		Country savedCountry = countryService.create(country);
//		
//		HttpHeaders headers = new HttpHeaders(null);
//		headers.add("Location", "/country/" + String.valueOf(savedCountry.getId()));
//		
//		ResponseEntity<Country> response = new ResponseEntity(savedCountry, headers, HttpStatus.CREATED);
//		return response;
//	}
	
	//Update
	@PutMapping("/{id}")
	public Country updateCountry(@PathVariable("id") long id, @Valid @RequestBody Country country) {
		return null;
	}
	
	//Delete
	@DeleteMapping("/{id}") 
	public void deleteCountry (@PathVariable("id") long id){
		
	}
}
