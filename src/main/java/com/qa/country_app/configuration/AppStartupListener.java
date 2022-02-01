package com.qa.country_app.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.qa.country_app.data.entity.Country;
import com.qa.country_app.data.repository.CountryRepository;

@Profile("dev")
@Configuration
public class AppStartupListener implements ApplicationListener<ApplicationReadyEvent>{
	
	private CountryRepository countryRepository;
	
	@Autowired
	public AppStartupListener(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		countryRepository.saveAll(List.of(new Country("Germany", "Berlin", "Europe", 83),
				 						  new Country("Brazil", "Brasilia", "South America", 214),
										  new Country("France", "Paris", "Europe", 68),
										  new Country("China", "Beijing", "Asia", 1411),
										  new Country("England", "London", "Europe", 56)));
	}

}
