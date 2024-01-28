package com.taoge.ecommerce.dao;

import com.taoge.ecommerce.dto.Purchase;
import com.taoge.ecommerce.dto.PurchaseResponse;
import com.taoge.ecommerce.entity.Customer;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerDaoImpl implements CustomerDao
{
	private EntityManager entityManager;

	@Autowired
	public CustomerDaoImpl(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Customer customer)
	{
		entityManager.merge(customer);
	}
}
