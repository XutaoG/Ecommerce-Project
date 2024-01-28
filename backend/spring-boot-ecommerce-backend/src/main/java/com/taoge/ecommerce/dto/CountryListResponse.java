package com.taoge.ecommerce.dto;

import com.taoge.ecommerce.entity.Country;

import java.util.List;

public class CountryListResponse
{
	private List<Country> content;

	// ***** Constructors *****

	public CountryListResponse()
	{

	}

	public CountryListResponse(List<Country> content)
	{
		this.content = content;
	}

	// ***** Getters/Setters *****


	public List<Country> getContent()
	{
		return content;
	}

	public void setContent(List<Country> content)
	{
		this.content = content;
	}
}
