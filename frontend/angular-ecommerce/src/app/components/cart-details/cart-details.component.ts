import { Component, OnInit } from '@angular/core';
import { CartItem } from '../../common/cart-item';
import { CartService } from '../../services/cart.service';

@Component({
	selector: 'app-cart-details',
	templateUrl: './cart-details.component.html',
	styleUrl: './cart-details.component.css'
})
export class CartDetailsComponent implements OnInit
{

	cartItems: CartItem[] = [];
	totalPrice: number = 0;
	totalQuantity: number = 0;

	constructor(private cartService: CartService)
	{

	}

	ngOnInit(): void
	{
		this.listCartDetail();
	}

	listCartDetail(): void
	{
		// Get a handle to cart items
		this.cartItems = this.cartService.cartItems;

		// subscribe to cart total price
		this.cartService.totalPrice.subscribe(data => this.totalPrice = data);

		// subscribe to cart total quantity
		this.cartService.totalQuantity.subscribe(data => this.totalQuantity = data);

		// compute total price and quantity
		this.cartService.computeTotals();
	}

	incrementQuantity(cartItem: CartItem): void
	{
		this.cartService.addToCart(cartItem);
	}

	decrementQuantity(cartItem: CartItem): void
	{
		this.cartService.decrementQuantity(cartItem);
	}

	remove(cartItem: CartItem): void
	{
		this.cartService.remove(cartItem);
	}
}
