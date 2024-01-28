import { Injectable } from '@angular/core';
import { CartItem } from '../common/cart-item';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class CartService 
{
	cartItems: CartItem[] = [];

	// Subject, subclass of Observable, used to publish events
	totalPrice: Subject<number> = new BehaviorSubject<number>(0);
	totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

	constructor() 
	{ 

	}

	addToCart(cartItem: CartItem): void
	{
		// Check if item is already in the cart
		let doesExist: boolean = false;
		let existingCartItem: CartItem = undefined!;

		if (this.cartItems.length > 0)
		{
			// Find the item in cart based on ID
			existingCartItem = this.cartItems.find(tempCartItem => tempCartItem.id == cartItem.id)!;

			// Check if item is found
			doesExist = (existingCartItem != undefined);
		}

		if (doesExist)
		{
			existingCartItem.quantity++;
		}
		else
		{
			this.cartItems.push(cartItem);
		}

		// Compute cart total price and quantity
		this.computeTotals();
	}

	computeTotals(): void
	{
		let newTotalPrice: number = 0;
		let newTotalQuantity: number = 0;

		for (let cartItem of this.cartItems)
		{
			newTotalPrice += cartItem.quantity * cartItem.unitPrice;
			newTotalQuantity += cartItem.quantity;
		}

		// Publish the new price and quantity
		// next() publish event
		this.totalPrice.next(newTotalPrice);
		this.totalQuantity.next(newTotalQuantity);
	}

	decrementQuantity(cartItem: CartItem): void
	{
		cartItem.quantity--;

		// Remove cart item if quantity is 0
		if (cartItem.quantity == 0)
		{
			this.remove(cartItem);
		}
		// Computer new total after decrementing quantity
		else
		{
			this.computeTotals();
		}
	}

	remove(cartItem: CartItem): void
	{
		// Get the index of the cart item in the array
		const itemIndex: number = this.cartItems.findIndex(tempCartItem => cartItem.id == tempCartItem.id);

		// If found, remove the item from the array
		if (itemIndex >= 0)
		{
			this.cartItems.splice(itemIndex, 1);

			this.computeTotals();
		}
	}
}
