package com.taoge.ecommerce.dto;

import com.taoge.ecommerce.entity.ProductCategory;

import java.util.List;

public class ProductCategoryListResponse
{
	private List<ProductCategory> content;

	// ***** Constructors *****

	public ProductCategoryListResponse()
	{
	}

	public ProductCategoryListResponse(List<ProductCategory> content)
	{
		this.content = content;
	}

	// ***** Getters/Setters *****

	public List<ProductCategory> getContent()
	{
		return content;
	}

	public void setContent(List<ProductCategory> content)
	{
		this.content = content;
	}
}
