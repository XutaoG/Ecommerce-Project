package com.taoge.ecommerce.dto;

import com.taoge.ecommerce.entity.State;

import java.util.List;

public class StateListResponse
{
	private List<State> content;

	// ***** Constructors *****

	public StateListResponse()
	{
	}

	public StateListResponse(List<State> content)
	{
		this.content = content;
	}

	// ***** Getters/Setters *****


	public List<State> getContent()
	{
		return content;
	}

	public void setContent(List<State> content)
	{
		this.content = content;
	}
}
