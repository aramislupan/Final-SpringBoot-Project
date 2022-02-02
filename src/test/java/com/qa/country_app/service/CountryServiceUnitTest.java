package com.qa.country_app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.country_app.data.entity.Country;
import com.qa.country_app.data.repository.CountryRepository;

@ExtendWith(MockitoExtension.class)
public class CountryServiceUnitTest {
	
	@Mock
	private CountryRepository countryRepository;
	
	@InjectMocks
	private CountryService countryService;
	
	private List<Country> countries;
	private Country expectedCountryWithId;
	private Country expectedCountryWithoutId;
	
	@BeforeEach
	public void init() {
		//get all test
		countries = new ArrayList<>();
		countries.addAll(List.of(new Country(0, "Germany", "Berlin", "Europe", 83),
					  			 new Country(1, "Brazil", "Brasilia", "South America", 214),
					  			 new Country(2, "China", "Beijing", "Asia", 1411)));
		//create test
		expectedCountryWithId = new Country(0, "Germany", "Berlin", "Europe", 83);	
		expectedCountryWithoutId = new Country("Germany", "Berlin", "Europe", 83);
		
	}
	
	@Test
	public void getAllCountriesTest() {
		when(countryRepository.findAll()).thenReturn(countries);
		assertThat(countryService.getAll()).isEqualTo(countries);
		verify(countryRepository).findAll();
	}
	
	@Test
	public void createCountryTest() {
		when(countryRepository.save(expectedCountryWithoutId)).thenReturn(expectedCountryWithId);
		assertThat(countryService.create(expectedCountryWithoutId)).isEqualTo(expectedCountryWithId);
		verify(countryRepository).save(expectedCountryWithoutId);
	}

}
