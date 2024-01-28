package com.taoge.ecommerce.rest;

import com.taoge.ecommerce.dto.Purchase;
import com.taoge.ecommerce.dto.PurchaseResponse;
import com.taoge.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/checkout")
public class CheckoutRestController
{
	private CheckoutService checkoutService;

	@Autowired
	public CheckoutRestController(CheckoutService checkoutService)
	{
		this.checkoutService = checkoutService;
	}

	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase)
	{
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

		return purchaseResponse;
	}
}