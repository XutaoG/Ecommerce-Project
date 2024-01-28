package com.taoge.ecommerce.dao;

import com.taoge.ecommerce.dto.ProductCategoryListResponse;
import com.taoge.ecommerce.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao
{
	ProductCategoryListResponse findAll();

	ProductCategory findById(int id);
}
