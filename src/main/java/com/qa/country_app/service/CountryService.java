package com.qa.country_app.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.country_app.data.entity.Country;
import com.qa.country_app.data.repository.CountryRepository;

@Service
public class CountryService {
	
	private CountryRepository countryRepository;
	
	@Autowired //constructor injection
	public CountryService(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	public List<Country> getAll(){
		return countryRepository.findAll();
	}
	
	public Country getById(long id) {
		if (countryRepository.existsById(id)) {
			return countryRepository.findById(id).get();
		}
		throw new EntityNotFoundException("Country with id " + id + " was not found");
	}
	
	public Country create(Country country) {
		Country savedCountry = countryRepository.save(country);
		return savedCountry;
	}
	
	public Country update(long id, Country country) {
		if (countryRepository.existsById(id)) {
			Country countryInDb = countryRepository.getById(id);
			
			countryInDb.setCountry(country.getCountry());
			countryInDb.setCapital(country.getCapital());
			countryInDb.setContinent(country.getContinent());
			countryInDb.setPopulation(country.getPopulation());
			
			return countryRepository.save(countryInDb);
		} else {
			throw new EntityNotFoundException("Country with id " + id + " was not found");
		}
	}
	
	public void delete(Long id) {
		if (countryRepository.existsById(id)) {
			countryRepository.deleteById(id);
		} else {
			throw new EntityNotFoundException("Country with id " + id + " was not found");
		}
	}
}
