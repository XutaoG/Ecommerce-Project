package com.taoge.ecommerce.dao;

import com.taoge.ecommerce.dto.StateListResponse;
import com.taoge.ecommerce.entity.State;

public interface StateDao
{
	StateListResponse findAll();

	State findById(int id);

	StateListResponse findByCountryCode(String code);
}
