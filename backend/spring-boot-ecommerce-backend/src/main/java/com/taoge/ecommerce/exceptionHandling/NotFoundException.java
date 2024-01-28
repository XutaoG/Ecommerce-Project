package com.taoge.ecommerce.exceptionHandling;

public class NotFoundException extends RuntimeException
{
	public NotFoundException(String message)
	{
		super(message);
	}
}
