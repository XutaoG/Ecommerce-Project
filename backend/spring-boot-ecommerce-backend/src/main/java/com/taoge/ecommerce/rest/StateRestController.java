package com.taoge.ecommerce.rest;

import com.taoge.ecommerce.dto.StateListResponse;
import com.taoge.ecommerce.entity.State;
import com.taoge.ecommerce.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class StateRestController
{
	private CountryService countryService;

	@Autowired
	public StateRestController(CountryService countryService)
	{
		this.countryService = countryService;
	}

	// GET all states
	@GetMapping("/states")
	public StateListResponse getAllStates()
	{
		return countryService.findAllStates();
	}

	// GET state by ID
	@GetMapping("/states/{id}")
	public State getStateById(@PathVariable(name = "id") int id)
	{
		return countryService.findStateById(id);
	}

	// GET all states by country code
	@GetMapping("/states/search/findByCountryCode")
	public StateListResponse getAllStatesByCountryCode(@RequestParam(name = "code") String code)
	{
		return countryService.findAllStateByCountryCode(code);
	}
}
