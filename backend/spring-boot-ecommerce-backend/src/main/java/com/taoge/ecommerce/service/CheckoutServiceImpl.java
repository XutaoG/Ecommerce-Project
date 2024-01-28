package com.taoge.ecommerce.service;

import com.taoge.ecommerce.dao.CustomerDao;
import com.taoge.ecommerce.dto.Purchase;
import com.taoge.ecommerce.dto.PurchaseResponse;
import com.taoge.ecommerce.entity.Customer;
import com.taoge.ecommerce.entity.Order;
import com.taoge.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService
{
	private CustomerDao customerDao;

	@Autowired
	public CheckoutServiceImpl(CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase)
	{
		// Retrieve order info from dto
		Order order = purchase.getOrder();

		// Generate tracking number
		String trackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(trackingNumber);

		// Populate order with order items
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));

		// Populate order with addresses
		order.setShippingAddress(purchase.getShippingAddress());
		order.setBillingAddress(purchase.getBillingAddress());

		// Populate customer with order
		Customer customer = purchase.getCustomer();
		customer.add(order);

		// Save to database
		customerDao.save(customer);

		// Return a response
		return new PurchaseResponse(trackingNumber);
	}

	private String generateOrderTrackingNumber()
	{
		// Generate UUID (Universally-unique-identifier)
		return UUID.randomUUID().toString();
	}
}
