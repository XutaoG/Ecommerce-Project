package com.taoge.ecommerce.dao;

import com.taoge.ecommerce.dto.ProductListResponse;
import com.taoge.ecommerce.entity.Product;
import com.taoge.ecommerce.entity.ProductCategory;
import com.taoge.ecommerce.exceptionHandling.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao
{
	private EntityManager entityManager;

	@Autowired
	public ProductDaoImpl(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	public ProductListResponse findAll(Pageable pageable)
	{
		List<Product> products = entityManager.createQuery("FROM Product", Product.class).getResultList();

		return paginateList(products, pageable.getPageNumber(), pageable.getPageSize());
	}

	@Override
	public Product findById(long id)
	{
		Product result = entityManager.find(Product.class, id);

		// If ID is invalid
		if (result == null)
		{
			throw new NotFoundException(String.format("Product ID (%d) Not Found", id));
		}

		return result;
	}

	// Find products with category ID
	@Override
	public ProductListResponse findByCategoryId(int categoryId, Pageable pageable)
	{
		ProductCategory category = entityManager.find(ProductCategory.class, categoryId);

		// If category ID is invalid
		if (category == null)
		{
			throw new NotFoundException(String.format("Category ID (%d) Not Found", categoryId));
		}

		TypedQuery<Product> typedQuery = entityManager.createQuery("FROM Product WHERE category = :categoryId", Product.class);
		typedQuery.setParameter("categoryId", category);
		List<Product> products = typedQuery.getResultList();

		return paginateList(products, pageable.getPageNumber(), pageable.getPageSize());
	}

	// Find products with name containing keyword
	@Override
	public ProductListResponse findByNameConntaining(String keyword, Pageable pageable)
	{
		TypedQuery<Product> typedQuery = entityManager.createQuery("FROM Product WHERE name LIKE CONCAT('%', :keyword, '%')", Product.class);
		typedQuery.setParameter("keyword", keyword);
		List<Product> products = typedQuery.getResultList();

		return paginateList(products, pageable.getPageNumber(), pageable.getPageSize());
	}

	// Paginate list given page number and size
	private ProductListResponse paginateList(List<Product> products, int pageNumber, int pageSize)
	{
		int totalPages = (int) Math.ceil(products.size() * 1.0 / pageSize);

		int max = pageNumber + 1 >= totalPages ? products.size() : pageSize * (pageNumber + 1);
		int min = pageNumber + 1 > totalPages ? max : pageSize * pageNumber;

		return new ProductListResponse(
				products.subList(min, max),
				pageNumber,
				pageSize,
				products.size());
	}
}
