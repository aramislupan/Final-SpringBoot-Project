package com.qa.country_app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	//create & delete test
	private Country expectedCountryWithId;
	private Country expectedCountryWithoutId; 
	
	@BeforeEach
	public void init() {
		//get all test
		countries = new ArrayList<>();
		countries.addAll(List.of(new Country(0, "Germany", "Berlin", "Europe", 83),
					  			 new Country(1, "Brazil", "Brasilia", "South America", 214),
					  			 new Country(2, "China", "Beijing", "Asia", 1411)));
		//create & delete test
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
	
	@Test
	public void deleteCountryTest() {
		when(countryRepository.existsById(expectedCountryWithId.getId())).thenReturn(true);
		countryService.delete(expectedCountryWithId.getId());
		verify(countryRepository).existsById(expectedCountryWithId.getId());
		verify(countryRepository).deleteById(expectedCountryWithId.getId());
	}
	
	@Test
	public void getCountryByIdTest() {
		long id = expectedCountryWithId.getId();
		when(countryRepository.findById(id)).thenReturn(Optional.of(expectedCountryWithId));
		assertThat(countryService.getById(id)).isEqualTo(expectedCountryWithId);
		verify(countryRepository).findById(id);
	}
	
	@Test
	public void updateUserTest() {
		long id = expectedCountryWithId.getId();
		
		Country countryToUpdate = new Country(expectedCountryWithId.getId(),
											  expectedCountryWithId.getCountry(),
											  expectedCountryWithId.getCapital(),
											  expectedCountryWithId.getContinent(),
											  expectedCountryWithId.getPopulation());
		
		when(countryRepository.existsById(id)).thenReturn(true);
		when(countryRepository.getById(id)).thenReturn(expectedCountryWithId);
		when(countryRepository.save(expectedCountryWithId)).thenReturn(countryToUpdate);
		
		assertThat(countryService.update(id, countryToUpdate)).isEqualTo(countryToUpdate);
		
		verify(countryRepository).existsById(id);
		verify(countryRepository).getById(id);
		verify(countryRepository).save(expectedCountryWithId);
		
	}

}
