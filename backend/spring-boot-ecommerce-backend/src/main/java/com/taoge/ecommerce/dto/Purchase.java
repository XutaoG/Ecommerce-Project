package com.taoge.ecommerce.dto;

import com.taoge.ecommerce.entity.Address;
import com.taoge.ecommerce.entity.Customer;
import com.taoge.ecommerce.entity.Order;
import com.taoge.ecommerce.entity.OrderItem;

import java.util.Set;

public class Purchase
{
	private Customer customer;

	private Address shippingAddress;

	private Address billingAddress;

	private Order order;

	private Set<OrderItem> orderItems;

	// ***** Getters/Setters *****

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public Address getShippingAddress()
	{
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress()
	{
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress)
	{
		this.billingAddress = billingAddress;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public Set<OrderItem> getOrderItems()
	{
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}
}
