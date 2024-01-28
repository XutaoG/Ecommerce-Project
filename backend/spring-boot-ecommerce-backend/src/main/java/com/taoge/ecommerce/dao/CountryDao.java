package com.taoge.ecommerce.dao;

import com.taoge.ecommerce.dto.CountryListResponse;
import com.taoge.ecommerce.entity.Country;

public interface CountryDao
{
	CountryListResponse findAll();

	Country findById(int id);
}
