package com.qa.country_app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
	public List<Country> getCountries() {
		return null;
	}
	
	//Read by Id
	@RequestMapping (path = "/{id}", method = {RequestMethod.GET})
	public Country getUserById(@PathVariable("id") long id) {
		return null;
	}
	
	//Create
	@PostMapping
	public Country createCountry(@Valid @RequestBody Country country) {
		return null;
	}
	
	//Update
	@PutMapping("/{id}")
	public Country updateCountry(@PathVariable("id") long id, @Valid @RequestBody Country country) {
		return null;
	}
	
	@DeleteMapping("/{id}") 
	public void deleteCountry (@PathVariable("id") long id){
		
	}
}
