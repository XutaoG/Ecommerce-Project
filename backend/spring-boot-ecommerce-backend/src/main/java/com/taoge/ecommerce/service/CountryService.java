package com.taoge.ecommerce.service;

import com.taoge.ecommerce.dto.CountryListResponse;
import com.taoge.ecommerce.dto.StateListResponse;
import com.taoge.ecommerce.entity.Country;
import com.taoge.ecommerce.entity.State;

public interface CountryService
{
	// ***** Country DAO Methods *****

	CountryListResponse findAllCountries();

	Country findCountryById(int id);

	// ***** State DAO Methods *****

	StateListResponse findAllStates();

	State findStateById(int id);

	StateListResponse findAllStateByCountryCode(String code);
}
