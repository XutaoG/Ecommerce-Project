package com.taoge.ecommerce.rest;

import com.taoge.ecommerce.dto.ProductListResponse;
import com.taoge.ecommerce.entity.Product;
import com.taoge.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class ProductRestController
{
	private ProductService productService;

	@Autowired
	public ProductRestController(ProductService productService)
	{
		this.productService = productService;
	}

	// GET all products
	@GetMapping("/products")
	public ProductListResponse getAllProducts(
			@RequestParam(name = "page", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(name = "size", defaultValue = "20", required = false) int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		return productService.findAllProducts(pageable);
	}

	// GET product by id
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable(name = "id") long id)
	{
		return productService.findProductById(id);
	}

	// GET all products in a category
	@GetMapping("/products/search/findByCategoryId")
	public ProductListResponse getAllProductsByCategoryId(
			@RequestParam(name = "id", defaultValue = "1", required = false) int categoryId,
			@RequestParam(name = "page", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(name = "size", defaultValue = "20", required = false) int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		return productService.findProductsByCategoryId(categoryId, pageable);
	}

	// GET all products with name containing keyword
	@GetMapping("/products/search/findByNameContaining")
	public ProductListResponse getAllProductsByNameContaining(
			@RequestParam(name = "name") String keyword,
			@RequestParam(name = "page", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(name = "size", defaultValue = "20", required = false) int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		return productService.findProductsByNameContaining(keyword, pageable);
	}
}
