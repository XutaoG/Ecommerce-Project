package com.taoge.ecommerce.service;

import com.taoge.ecommerce.dao.CountryDao;
import com.taoge.ecommerce.dao.StateDao;
import com.taoge.ecommerce.dto.CountryListResponse;
import com.taoge.ecommerce.dto.StateListResponse;
import com.taoge.ecommerce.entity.Country;
import com.taoge.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService
{
	private CountryDao countryDao;
	private StateDao stateDao;

	@Autowired
	public CountryServiceImpl(CountryDao countryDao, StateDao stateDao)
	{
		this.countryDao = countryDao;
		this.stateDao = stateDao;
	}

	@Override
	public CountryListResponse findAllCountries()
	{
		return countryDao.findAll();
	}

	@Override
	public Country findCountryById(int id)
	{
		return countryDao.findById(id);
	}

	@Override
	public StateListResponse findAllStates()
	{
		return stateDao.findAll();
	}

	@Override
	public State findStateById(int id)
	{
		return stateDao.findById(id);
	}

	@Override
	public StateListResponse findAllStateByCountryCode(String code)
	{
		return stateDao.findByCountryCode(code);
	}
}
