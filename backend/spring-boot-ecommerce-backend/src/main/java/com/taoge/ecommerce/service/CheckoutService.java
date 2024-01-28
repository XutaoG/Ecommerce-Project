package com.taoge.ecommerce.service;

import com.taoge.ecommerce.dto.Purchase;
import com.taoge.ecommerce.dto.PurchaseResponse;

public interface CheckoutService
{
	PurchaseResponse placeOrder(Purchase purchase);
}