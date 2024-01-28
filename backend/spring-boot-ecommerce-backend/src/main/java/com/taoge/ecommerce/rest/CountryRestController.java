package com.taoge.ecommerce.rest;

import com.taoge.ecommerce.dto.CountryListResponse;
import com.taoge.ecommerce.entity.Country;
import com.taoge.ecommerce.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class CountryRestController
{
	private CountryService countryService;

	@Autowired
	public CountryRestController(CountryService countryService)
	{
		this.countryService = countryService;
	}

	// GET all countries
	@GetMapping("/countries")
	public CountryListResponse getAllCountries()
	{
		return countryService.findAllCountries();
	}

	// GET country by ID
	@GetMapping("/countries/{id}")
	public Country getCountryById(@PathVariable(name = "id") int id)
	{
		return countryService.findCountryById(id);
	}
}
