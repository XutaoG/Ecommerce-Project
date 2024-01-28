package com.taoge.ecommerce.service;

import com.taoge.ecommerce.dao.ProductCategoryDao;
import com.taoge.ecommerce.dao.ProductDao;
import com.taoge.ecommerce.dto.ProductCategoryListResponse;
import com.taoge.ecommerce.dto.ProductListResponse;
import com.taoge.ecommerce.entity.Product;
import com.taoge.ecommerce.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService
{
	private ProductDao productDao;
	private ProductCategoryDao productCategoryDao;

	@Autowired
	public ProductServiceImpl(ProductDao productDao, ProductCategoryDao productCategoryDao)
	{
		this.productDao = productDao;
		this.productCategoryDao = productCategoryDao;
	}

	// ***** Product DAO Methods *****

	@Override
	public ProductListResponse findAllProducts(Pageable pageable)
	{
		return productDao.findAll(pageable);
	}

	@Override
	public Product findProductById(long id)
	{
		return productDao.findById(id);
	}

	@Override
	public ProductListResponse findProductsByCategoryId(int categoryId, Pageable pageable)
	{
		return productDao.findByCategoryId(categoryId, pageable);
	}

	@Override
	public ProductListResponse findProductsByNameContaining(String keyword, Pageable pageable)
	{
		return productDao.findByNameConntaining(keyword, pageable);
	}

	// ***** ProductCategory DAO Methods *****

	@Override
	public ProductCategoryListResponse findAllProductCategories()
	{
		return productCategoryDao.findAll();
	}

	@Override
	public ProductCategory findProductCategoryById(int id)
	{
		return productCategoryDao.findById(id);
	}
}
