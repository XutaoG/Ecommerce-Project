package com.taoge.ecommerce.dao;

import com.taoge.ecommerce.dto.CountryListResponse;
import com.taoge.ecommerce.entity.Country;
import com.taoge.ecommerce.exceptionHandling.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoImpl implements CountryDao
{
	private EntityManager entityManager;

	@Autowired
	public CountryDaoImpl(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	@Override
	public CountryListResponse findAll()
	{
		TypedQuery<Country> query = entityManager.createQuery("FROM Country", Country.class);

		CountryListResponse response = new CountryListResponse(query.getResultList());

		return response;
	}

	@Override
	public Country findById(int id)
	{
		Country result = entityManager.find(Country.class, id);

		// If ID is invalid
		if (result == null)
		{
			throw new NotFoundException(String.format("Country ID (%d) Not Found", id));
		}

		return result;
	}
}
