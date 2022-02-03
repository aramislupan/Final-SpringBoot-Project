package com.qa.country_app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.country_app.data.entity.Country;
import com.qa.country_app.data.repository.CountryRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class CountryControllerSystemIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private CountryRepository countryRepository;

	private List<Country> countriesInDb;
	
	
	@BeforeEach
	public void init() {
		List<Country> countries = List.of(new Country(1, "Germany", "Berlin", "Europe", 83),
				  						  new Country(2, "Brazil", "Brasilia", "South America", 214),
				  						  new Country(3, "France", "Paris", "Europe", 68),
										  new Country(4, "China", "Beijing", "Asia", 1411),
										  new Country(5, "England", "London", "Europe", 56));
		countriesInDb = new ArrayList<>();
		countriesInDb.addAll(countryRepository.saveAll(countries));
		
	}
	
	@Test
	public void getAllCountriesTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/country");
		mockRequest.accept(MediaType.APPLICATION_JSON); 
		
		String countries = objectMapper.writeValueAsString(countriesInDb);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(countries);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
		
	}
	
	@Test
	public void updateCountryTest() throws Exception {
		Long id = 2L;
		Country countryToUpdate = new Country("China", "Beijing", "Asia", 1411);
		Country expectedCountry = new Country(id,"China", "Beijing", "Asia", 1411);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/country/" + id);
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		
		mockRequest.content(objectMapper.writeValueAsString(countryToUpdate));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		String countryJson = objectMapper.writeValueAsString(expectedCountry);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(countryJson); 
																						
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void getCountryByIdTest() throws Exception {
		Long id = 3L;
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/country/" + id);
		mockRequest.accept(MediaType.APPLICATION_JSON); 
		
		String country = objectMapper.writeValueAsString(countriesInDb.get(2));
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(country);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void deleteCountryTest() throws Exception {
		Long id = 1L;
		Country countryToDelete = new Country("China", "Beijing", "Asia", 1411);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/country/" + id);
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		
		mockRequest.content(objectMapper.writeValueAsString(countryToDelete));
		mockRequest.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
																				
		mockMvc.perform(mockRequest).andExpect(statusMatcher);
	}
	
}
