import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ShopFormService } from '../../services/shop-form.service';
import { Country } from '../../common/country';
import { State } from '../../common/state';
import { ShopFormValidators } from '../../validators/shop-form-validators';
import { CartService } from '../../services/cart.service';
import { CheckoutService } from '../../services/checkout.service';
import { Order } from '../../common/order';
import { CartItem } from '../../common/cart-item';
import { OrderItem } from '../../common/order-item';
import { Purchase } from '../../common/purchase';
import { Router } from '@angular/router';

@Component({
	selector: 'app-checkout',
	templateUrl: './checkout.component.html',
	styleUrl: './checkout.component.css'
})
export class CheckoutComponent implements OnInit
{
	checkoutFormGroup!: FormGroup;

	totalPrice: number = 0;
	totalQuantity: number = 0;

	creditCardYears: number[] = [];
	creditCardMonths: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

	countries: Country[] = [];

	copyShippingToBillingFlag: boolean = false;

	shippingAddressStates: State[] = [];
	billingAddressStates: State[] = [];

	constructor(private formBuilder: FormBuilder, 
				private shopFormService: ShopFormService, 
				private cartService: CartService,
				private checkoutService: CheckoutService,
				private router: Router)
	{
	}

	ngOnInit(): void
	{
		this.reviewCartDetail();

		this.checkoutFormGroup = this.formBuilder.group(
			{
				customer: this.formBuilder.group(
					{
						firstName: new FormControl("", 
							[Validators.required, Validators.minLength(2), ShopFormValidators.notOnlyWhiteSpace]),
						lastName: new FormControl("", 
							[Validators.required, Validators.minLength(2), ShopFormValidators.notOnlyWhiteSpace]),
						email: new FormControl("", 
							[Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")])
					}
				),
				shippingAddress: this.formBuilder.group(
					{
						street: new FormControl("",
							[Validators.required, ShopFormValidators.notOnlyWhiteSpace]),
						city: new FormControl("",
							[Validators.required, ShopFormValidators.notOnlyWhiteSpace]),
						state: new FormControl("", [Validators.required]),
						country: new FormControl("", [Validators.required]),
						zipCode: new FormControl("",
							[Validators.required, Validators.minLength(2), ShopFormValidators.notOnlyWhiteSpace])
					}
				),
				billingAddress: this.formBuilder.group(
					{
						street: new FormControl("",
							[Validators.required, ShopFormValidators.notOnlyWhiteSpace]),
						city: new FormControl("",
							[Validators.required, ShopFormValidators.notOnlyWhiteSpace]),
						state: new FormControl("", [Validators.required]),
						country: new FormControl("", [Validators.required]),
						zipCode: new FormControl("",
							[Validators.required, Validators.minLength(2), ShopFormValidators.notOnlyWhiteSpace])
					}
				),
				creditCard: this.formBuilder.group(
					{
						cardType: new FormControl("", [Validators.required]),
						nameOnCard: new FormControl("", 
							[Validators.required, Validators.minLength(2), ShopFormValidators.notOnlyWhiteSpace]),
						cardNumber: new FormControl("", 
							[Validators.required, Validators.pattern("[0-9]{16}")]),
						securityCode: new FormControl("", 
							[Validators.required, Validators.pattern("[0-9]{3}")]),
						expirationMonth: new FormControl("", [Validators.required]),
						expirationYear: new FormControl("", [Validators.required])
					}
				)
			}
		);

		// Populate credit card years
		this.shopFormService.getCreditCardYears().subscribe(data =>
			{
				this.creditCardYears = data;
			});

		// Populate countries
		this.shopFormService.getCountries().subscribe(data =>
			{
				this.countries = data;
			});
	}

	reviewCartDetail()
	{
		this.cartService.totalQuantity.subscribe(data => this.totalQuantity = data);
		this.cartService.totalPrice.subscribe(data => this.totalPrice = data);	
	}

	onSubmit(): void
	{
		if (this.checkoutFormGroup.invalid)
		{
			this.checkoutFormGroup.markAllAsTouched();
			return;
		}
		
		// Set up order
		let order: Order = new Order();
		order.totalPrice = this.totalPrice;
		order.totalQuantity = this.totalQuantity;

		// Get cart item
		let cartItems: CartItem[] = this.cartService.cartItems;

		// Create orderItems from cart
		let orderItems: OrderItem[] = [];
		for (let i = 0; i < cartItems.length; i++)
		{
			orderItems[i] = new OrderItem(cartItems[i]);
		}

		// Set up purchase
		let purchase: Purchase = new Purchase();

		purchase.customer = this.checkoutFormGroup.controls["customer"].value;
		
		purchase.shippingAddress = this.checkoutFormGroup.controls["shippingAddress"].value;
		let shippingAddressState: State = JSON.parse(JSON.stringify(purchase.shippingAddress.state));
		let shippingAddressCountry: Country = JSON.parse(JSON.stringify(purchase.shippingAddress.country));
		purchase.shippingAddress.state = shippingAddressState.name;
		purchase.shippingAddress.country = shippingAddressCountry.name;

		purchase.billingAddress = this.checkoutFormGroup.controls["billingAddress"].value;
		let billingAddressState: State = JSON.parse(JSON.stringify(purchase.billingAddress.state));
		let billingAddressCountry: Country = JSON.parse(JSON.stringify(purchase.billingAddress.country));
		purchase.billingAddress.state = billingAddressState.name;
		purchase.billingAddress.country = billingAddressCountry.name;

		purchase.order = order;
		purchase.orderItems = orderItems;

		// Call REST API via CheckoutService
		this.checkoutService.placeOrder(purchase).subscribe(
			{
				// next: success path
				next: response =>
				{
					alert(`Your order has been received.\nOrder tracking number ${response.orderTrackingNumber}`);

					// Reset the cart
					this.resetCart();
				},
				// error: error path
				error: err =>
				{
					alert(`There was an error: ${err.message}`);
				}
			}
		);

	}

	resetCart()
	{
		// Reset cart data
		this.cartService.cartItems = [];
		this.cartService.totalPrice.next(0);
		this.cartService.totalQuantity.next(0);

		// Reset form
		this.checkoutFormGroup.reset();

		// Navigate back to product page
		this.router.navigateByUrl("/products");
	}

	get firstName() { return this.checkoutFormGroup.get("customer.firstName"); }
	get lastName() { return this.checkoutFormGroup.get("customer.lastName"); }
	get email() { return this.checkoutFormGroup.get("customer.email"); }

	get shippingAddressStreet() { return this.checkoutFormGroup.get("shippingAddress.street"); }
	get shippingAddressCity() { return this.checkoutFormGroup.get("shippingAddress.city"); }
	get shippingAddressState() { return this.checkoutFormGroup.get("shippingAddress.state"); }
	get shippingAddressCountry() { return this.checkoutFormGroup.get("shippingAddress.country"); }
	get shippingAddressZipCode() { return this.checkoutFormGroup.get("shippingAddress.zipCode"); }

	get billingAddressStreet() { return this.checkoutFormGroup.get("billingAddress.street"); }
	get billingAddressCity() { return this.checkoutFormGroup.get("billingAddress.city"); }
	get billingAddressState() { return this.checkoutFormGroup.get("billingAddress.state"); }
	get billingAddressCountry() { return this.checkoutFormGroup.get("billingAddress.country"); }
	get billingAddressZipCode() { return this.checkoutFormGroup.get("billingAddress.zipCode"); }

	get creditCardType() { return this.checkoutFormGroup.get("creditCard.cardType"); }
	get creditCardName() { return this.checkoutFormGroup.get("creditCard.nameOnCard"); }
	get creditCardNumber() { return this.checkoutFormGroup.get("creditCard.cardNumber"); }
	get creditCardSecurityCode() { return this.checkoutFormGroup.get("creditCard.securityCode"); }
	get creditCardExpirationMonth() { return this.checkoutFormGroup.get("creditCard.expirationMonth"); }
	get creditCardExpirationYear() { return this.checkoutFormGroup.get("creditCard.expirationYear"); }

	copyShippingAddressToBillingAddress(): void
	{
		this.copyShippingToBillingFlag = !this.copyShippingToBillingFlag;

		if (this.copyShippingToBillingFlag)
		// if ((<HTMLInputElement>event.target).checked)
		{
			this.checkoutFormGroup.controls.billingAddress.setValue(this.checkoutFormGroup.controls.shippingAddress.value);

			// Copy over shipping address state
			this.billingAddressStates = this.shippingAddressStates;
		}
		else
		{
			this.checkoutFormGroup.controls.billingAddress.reset();

			// Reset billing address states
			this.billingAddressStates = [];
		}
	}

	getState(formGroupName: string): void
	{
		let formGroup = this.checkoutFormGroup.get(formGroupName);

		let countryCode = formGroup.value.country.code;
		
		this.shopFormService.getStates(countryCode).subscribe(data =>
			{
				if (formGroupName == "shippingAddress")
				{
					this.shippingAddressStates = data;
				}
				else if (formGroupName == "billingAddress")
				{
					this.billingAddressStates = data;
				}

				// Select first state by default
				formGroup.get("state").setValue(data[0]);
			}
		);
	}
}


