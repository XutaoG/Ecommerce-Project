package com.taoge.ecommerce.dto;

public class PurchaseResponse
{
	private String orderTrackingNumber;

	// ***** Constructors *****

	public PurchaseResponse(String orderTrackingNumber)
	{
		this.orderTrackingNumber = orderTrackingNumber;
	}

	// ***** Getters/Setters *****

	public String getOrderTrackingNumber()
	{
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber)
	{
		this.orderTrackingNumber = orderTrackingNumber;
	}
}
