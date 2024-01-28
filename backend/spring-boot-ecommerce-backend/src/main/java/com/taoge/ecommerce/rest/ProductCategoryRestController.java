package com.taoge.ecommerce.rest;

import com.taoge.ecommerce.dto.ProductCategoryListResponse;
import com.taoge.ecommerce.entity.ProductCategory;
import com.taoge.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class ProductCategoryRestController
{
	private ProductService productService;

	@Autowired
	public ProductCategoryRestController(ProductService productService)
	{
		this.productService = productService;
	}

	// GET all products
	@GetMapping("/product-category")
	public ProductCategoryListResponse getAllProductCategories()
	{
		return productService.findAllProductCategories();
	}

	// GET product by id
	@GetMapping("/product-category/{id}")
	public ProductCategory getProductCategoryById(@PathVariable(name = "id") int id)
	{
		return productService.findProductCategoryById(id);
	}
}