package com.taoge.ecommerce.service;

import com.taoge.ecommerce.dto.ProductCategoryListResponse;
import com.taoge.ecommerce.dto.ProductListResponse;
import com.taoge.ecommerce.entity.Product;
import com.taoge.ecommerce.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService
{
	// ***** Product DAO Methods *****

	ProductListResponse findAllProducts(Pageable pageable);

	Product findProductById(long id);

	ProductListResponse findProductsByCategoryId(int categoryId, Pageable pageable);

	ProductListResponse findProductsByNameContaining(String keyword, Pageable pageable);

	// ***** ProductCategory DAO Methods *****

	ProductCategoryListResponse findAllProductCategories();

	ProductCategory findProductCategoryById(int id);
}
