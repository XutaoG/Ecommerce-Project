package com.taoge.ecommerce.dao;

import com.taoge.ecommerce.dto.StateListResponse;
import com.taoge.ecommerce.entity.Country;
import com.taoge.ecommerce.entity.State;
import com.taoge.ecommerce.exceptionHandling.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StateDaoImpl implements StateDao
{
	private EntityManager entityManager;

	@Autowired
	public StateDaoImpl(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	@Override
	public StateListResponse findAll()
	{
		TypedQuery<State> query = entityManager.createQuery("FROM State", State.class);

		StateListResponse response = new StateListResponse(query.getResultList());

		return response;
	}

	@Override
	public State findById(int id)
	{
		State result = entityManager.find(State.class, id);

		if (result == null)
		{
			throw new NotFoundException(String.format("State ID (%d) Not Found", id));
		}

		return result;
	}

	@Override
	public StateListResponse findByCountryCode(String code)
	{
		TypedQuery<Country> countryQuery = entityManager.createQuery("FROM Country where code = :code", Country.class);
		countryQuery.setParameter("code", code);
		Country country = countryQuery.getSingleResult();

		if (country == null)
		{
			throw new NotFoundException(String.format("Country Code (%s) Not Found", code));
		}

		TypedQuery<State> query = entityManager.createQuery("FROM State where country = :country", State.class);
		query.setParameter("country", country);

		return new StateListResponse(query.getResultList());
	}
}
