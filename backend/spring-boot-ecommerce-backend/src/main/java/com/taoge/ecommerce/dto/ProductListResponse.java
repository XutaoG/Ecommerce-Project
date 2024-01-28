package com.taoge.ecommerce.dto;

import com.taoge.ecommerce.entity.Product;

import java.util.List;

public class ProductListResponse
{
	private List<Product> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;

	// ***** Constructors *****

	public ProductListResponse()
	{

	}

	public ProductListResponse(List<Product> content, int pageNumber, int pageSize, long totalElements)
	{
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
	}

	// ***** Getters/Setters

	public List<Product> getContent()
	{
		return content;
	}

	public void setContent(List<Product> content)
	{
		this.content = content;
	}

	public int getPageNumber()
	{
		return pageNumber;
	}

	public void setPageNumber(int pageNumber)
	{
		this.pageNumber = pageNumber;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public long getTotalElements()
	{
		return totalElements;
	}

	public void setTotalElements(long totalElements)
	{
		this.totalElements = totalElements;
	}
}
