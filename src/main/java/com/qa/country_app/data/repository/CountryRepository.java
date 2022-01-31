package com.qa.country_app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.country_app.data.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository <Country, Long> {
	
	

}
