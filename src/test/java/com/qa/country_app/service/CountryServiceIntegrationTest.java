package com.qa.country_app.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.country_app.data.entity.Country;
import com.qa.country_app.data.repository.CountryRepository;

@SpringBootTest
@Transactional
public class CountryServiceIntegrationTest {
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CountryRepository countryRepository;
	
	private List<Country> countriesInDb;
	private long nextNewElementId;
	
	@BeforeEach
	public void init() {
		List<Country> countries = List.of(new Country(1, "Germany", "Berlin", "Europe", 83),
				  						  new Country(2, "Brazil", "Brasilia", "South America", 214),
				  						  new Country(3, "France", "Paris", "Europe", 68),
										  new Country(4, "China", "Beijing", "Asia", 1411),
										  new Country(5, "England", "London", "Europe", 56));
		countriesInDb = new ArrayList<>();
		countriesInDb.addAll(countryRepository.saveAll(countries));
		int size = countriesInDb.size();
		nextNewElementId = countriesInDb.get(size - 1).getId() + 1;
		
	}
	
	@Test
	public void getAllCountriesTest() {
		assertThat(countriesInDb).isEqualTo(countryService.getAll());
	}
	
	@Test
	public void createUserTest() {
		Country countryToSave = new Country("France", "Paris", "Europe", 68);
		Country expectedCountry = new Country(nextNewElementId, countryToSave.getCountry(), countryToSave.getCapital(), countryToSave.getContinent(), countryToSave.getPopulation());
		
		assertThat(expectedCountry).isEqualTo(countryService.create(countryToSave));
	}	
	
	@Test
	public void updateCountryTest() {
		Country countryInDb = countriesInDb.get(0);
		long id = countryInDb.getId();
		
		Country countryWithUdatesToAction = new Country(countryInDb.getId(), countryInDb.getCountry(), countryInDb.getCapital(), countryInDb.getContinent(), countryInDb.getPopulation() +1);
		
		assertThat(countryService.update(id, countryWithUdatesToAction)).isEqualTo(countryWithUdatesToAction);		
	}
	
	@Test
	public void deleteCountryTest() {
		Country country = countriesInDb.get(1);
		countryService.delete(country.getId());
		
		assertThat(countryRepository.findById(country.getId()).equals(Optional.empty()));		
	}
	
	@Test
	public void getCountryByIdTest() {
		Country countryInDb = countriesInDb.get(0);
		
		assertThat(countryService.getById(countryInDb.getId()).equals(countryInDb));
	}
	
}
