package com.taoge.ecommerce.dao;

import com.taoge.ecommerce.dto.ProductListResponse;
import com.taoge.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDao
{
	ProductListResponse findAll(Pageable pageable);

	Product findById(long id);

	ProductListResponse findByCategoryId(int categoryId, Pageable pageable);

	ProductListResponse findByNameConntaining(String keyword, Pageable pageable);
}
