package com.qa.country_app.data.entity;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Country {
	
	private Long id;
	
	@NotNull
	private String country;
	@NotNull
	private String capital;
	@NotNull
	private String continent;
	
	@Max(1415) // Not added - No constraints
	@Min(1) // Not added - No constraints
	private int population;
	
	public Country() {
		super();
	}
	
	public Country(String country, String capital, String continent, int population) {
		super();
		
		this.country = country;
		this.capital = capital;
		this.continent = continent;
		this.population = population;
	}
	
	public Country(long id, String country, String capital, String continent, int population) {
		super();
		this.id = id;
		this.country = country;
		this.capital = capital;
		this.continent = continent;
		this.population = population;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCapital() {
		return capital;
	}
	
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	public String getContinent() {
		return continent;
	}
	
	public void setContinent(String continent) {
		this.continent = continent;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return String.format("Country [id=%s, country=%s, capital=%s, continent=%s, population=%s]", id, country,
				capital, continent, population);
	}

	@Override
	public int hashCode() {
		return Objects.hash(capital, continent, country, id, population);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(capital, other.capital) && Objects.equals(continent, other.continent)
				&& Objects.equals(country, other.country) && Objects.equals(id, other.id)
				&& population == other.population;
	} 

}
