package com.qa.country_app.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

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

@RestController
@RequestMapping (path = "/country")
public class CountryController {
	
	private static long counter = 0;
	
	private List<Country> countries = new ArrayList<>(List.of(new Country(counter++, "England", "London", "Europe", 56),
															  new Country(counter++, "Brazil", "Brasilia", "South America", 210),
															  new Country(counter++, "China", "Beijing", "Asia", 1412)));
	
	@GetMapping
	public List<Country> getCountries() {
		return countries;
	}
	
	@RequestMapping (path = "/{id}", method = {RequestMethod.GET})
	public Country getUserById(@PathVariable("id") int id) { //changed from Integer(error) to int - to review
		for (Country country : countries) {
			if(country.getId() == id) {
				return country;
			}
		}
		throw new EntityNotFoundException("Entity with id " + id + " was not found");
	}
	
	@PostMapping
	public Country createCountry(@Valid @RequestBody Country country) {
		
		country.setId(counter++);
		countries.add(country);
		return country;
	}
	
	@PutMapping("/{id}")
	public Country updateCountry(@PathVariable("id") long id, @Valid @RequestBody Country country) {
		if (countryExists(id)) {
			for (Country countryInDb : countries) {
				if (countryInDb.getId() == id) {
					countryInDb.setCountry(country.getCountry());
					countryInDb.setCapital(country.getCapital());
					countryInDb.setContinent(country.getContinent());
					countryInDb.setPopulation(country.getPopulation());
					return countryInDb;
				}
			}
		}
		throw new EntityNotFoundException("Entity with id " + id + " was not found");
	}
	
	private boolean countryExists(long id) {
		for (Country country : countries) {
			if (country.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	@DeleteMapping("/{id}") 
	public void deleteCountry (@PathVariable("id") long id){
		if (countryExists(id)) {
			Iterator<Country> iterator = countries.iterator(); // check option for iterator other than Java.util
			while (iterator.hasNext()) {
				Country country = iterator.next();
				if (country.getId() == id) {
					iterator.remove();
					return;
				}
			}
		} else {
			throw new EntityNotFoundException("Entity with id " + id + " was not found");
		}
	}
}
