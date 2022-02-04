package com.qa.country_app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.country_app.data.entity.Country;
import com.qa.country_app.service.CountryService;

//import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@WebMvcTest(CountryController.class)
public class CountryControllerWebIntegrationTest {
	
	@Autowired
	private CountryController controller;
	
	@MockBean
	private CountryService countryService;
	
	private List<Country> countries;
	
	//create test
	private Country validCountry;
	private Country countryToCreate;
	
	//by id test
	private Country validId;
	
	//update test
	private Country countryInDb;
	private Country countryToUpdateWith;
	
	@BeforeEach
	public void init() {
		//get all test
		countries = new ArrayList<>();
		countries.addAll(List.of(new Country(0, "Germany", "Berlin", "Europe", 83),
					  			 new Country(1, "Brazil", "Brasilia", "South America", 214),
					  			 new Country(2, "China", "Beijing", "Asia", 1411)));  
		
		//create test
		validCountry = new Country ("Brazil", "Brasilia", "South America", 214);
		countryToCreate = new Country (1, "Brazil", "Brasilia", "South America", 214);
		
		//by id test
		validId = new Country (2, "China", "Beijing", "Asia", 1411); 
		
		//update test
		countryInDb = new Country (0, "Germany", "Berlin", "Europe", 83);
		countryToUpdateWith = new Country ("Germany", "Berlin", "Europe", 83);
		
	}
	
	@Test
	public void getAllCountriesTest() {
		ResponseEntity<List<Country>> expected = new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
		
		when(countryService.getAll()).thenReturn(countries);
		ResponseEntity<List<Country>> actual = controller.getCountries();
		assertThat(expected).isEqualTo(actual);
		verify(countryService, times(1)).getAll();
		
	}
	
	@Test
	public void createCountryTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/country/" + String.valueOf(validCountry.getId()));
		ResponseEntity<Country> expected = new ResponseEntity<Country>(validCountry, headers, HttpStatus.CREATED);
		
		when(countryService.create(countryToCreate)).thenReturn(validCountry);
		ResponseEntity<Country> actual = controller.createCountry(countryToCreate);
		assertThat(expected).isEqualTo(actual);
		verify(countryService, times(1)).create(countryToCreate);
	}
	
	@Test
	public void getCountryByIdTest() {
		ResponseEntity<Country> expected = new ResponseEntity<Country>(validId, HttpStatus.OK);
		
		when(countryService.getById(2)).thenReturn(validId);
		ResponseEntity<Country> actual = controller.getCountryById(2);
		assertThat(expected).isEqualTo(actual);
		verify(countryService, times(1)).getById(2);
	}
	
	@Test
	public void updateCountryTest() {
		long userId = countryInDb.getId();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/user/" + String.valueOf(countryInDb.getId()));
		ResponseEntity<Country> expected = new ResponseEntity<Country>(countryInDb, headers, HttpStatus.ACCEPTED);
		
		when(countryService.update(userId, countryToUpdateWith)).thenReturn(countryInDb);
		ResponseEntity<Country> actual = controller.updateCountry(userId, countryToUpdateWith);
		assertThat(expected).isEqualTo(actual);
		verify(countryService, times(1)).update(userId, countryToUpdateWith);
	}
	
	@Test
	public void deleteCountryTest() {
		ResponseEntity<?> expected = ResponseEntity.accepted().build();
		ResponseEntity<?> actual = controller.deleteCountry(1);
		
		assertThat(expected).isEqualTo(actual);
		verify(countryService, times(1)).delete(1L);
	}

}
