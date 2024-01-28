package com.taoge.ecommerce.dao;

import com.taoge.ecommerce.dto.ProductCategoryListResponse;
import com.taoge.ecommerce.entity.Product;
import com.taoge.ecommerce.entity.ProductCategory;
import com.taoge.ecommerce.exceptionHandling.NotFoundException;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductCategoryDaoImpl implements ProductCategoryDao
{
	private EntityManager entityManager;

	@Autowired
	public ProductCategoryDaoImpl(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	@Override
	public ProductCategoryListResponse findAll()
	{
		List<ProductCategory> result = entityManager.createQuery("From ProductCategory", ProductCategory.class).getResultList();

		ProductCategoryListResponse productCategoryListResponse = new ProductCategoryListResponse();
		productCategoryListResponse.setContent(result);

		return productCategoryListResponse;
	}

	@Override
	public ProductCategory findById(int id)
	{
		ProductCategory result = entityManager.find(ProductCategory.class, id);

		// If ID is invalid
		if (result == null)
		{
			throw new NotFoundException(String.format("ProductCategory ID (%d) Not Found", id));
		}

		return result;
	}
}
