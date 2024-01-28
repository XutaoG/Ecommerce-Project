import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../common/product';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { CartItem } from '../../common/cart-item';

@Component({
	selector: 'app-product-list',
	templateUrl: './product-list-grid.component.html',
	styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit
{
	products: Product[] = [];
	currentCategoryId: number = 1;
	searchMode: boolean = false;

	previousCategoryId: number = 0;
	previousKeyword: string = "";

	// Pagination fields
	pageNumber: number = 1;
	pageSize: number = 5;
	totalElements: number = 0;

	constructor(private productService: ProductService, private cartService: CartService, private route: ActivatedRoute) // Dependency Injection
	{

	}

	ngOnInit(): void
	{
		this.route.paramMap.subscribe(() => 
		{
			this.listProducts();
		});
	}

	listProducts()
	{
		this.searchMode = this.route.snapshot.paramMap.has("keyword");

		if (this.searchMode) // Search for product
		{
			this.handleSearchProducts()
		}
		else // List product by categories
		{
			this.handleListProducts();
		}
	}

	handleListProducts(): void
	{
		// Check if id parameter is available
		// this.route = Current activated route
		// snapshot = State of the current route
		// paraMap = map of all route parameters
		const hasCategoryId: boolean = this.route.snapshot.paramMap.has("id");

		if (hasCategoryId)
		{
			// Get the id
			// "!" tells that compiler that the object is not null
			this.currentCategoryId = +this.route.snapshot.paramMap.get("id")!;
		}
		else // No category id available
		{
			// Default to category 1
			this.currentCategoryId = 1;
		}

		// If we have different category ID than the previous
		// Set the page number back to 1
		if (this.previousCategoryId != this.currentCategoryId)
		{
			this.pageNumber = 1;
		}

		this.previousCategoryId = this.currentCategoryId;

		// Get the products for the category id
		this.productService.getProductListPaginate(this.pageNumber - 1, this.pageSize, this.currentCategoryId)
							.subscribe(this.processResult());
	}

	handleSearchProducts(): void
	{
		const keyword: string = this.route.snapshot.paramMap.get("keyword")!;

		// If we have different search keyword than the previous
		// Set the page number back to 1
		if (this.previousKeyword != keyword)
		{
			this.pageNumber = 1;
		}

		this.previousKeyword = keyword;

		this.productService.searchProductListPaginate(this.pageNumber - 1, this.pageSize, keyword)
							.subscribe(this.processResult());
	}

	// ***** OLD *****

	// processResult() 
	// {
	// 	return (data: any) => 
	// 	{
	// 		this.products = data._embedded.products;
	// 		this.pageNumber = data.page.number + 1;
	// 		this.pageSize = data.page.size;
	// 		this.totalElements = data.page.totalElements;
	// 	};
	// }
		
	// ***** NEW *****
	
	processResult(): any
	{
		return (data: any) => 
		{
			this.products = data.content;
			this.pageNumber = data.pageNumber + 1;
			this.pageSize = data.pageSize;
			this.totalElements = data.totalElements;
		};
	}
	
	updatePageSize(pageSize: string): void
	{
		this.pageSize = +pageSize;
		this.pageNumber = 1;
		this.listProducts();
	}

	addToCart(product: Product): void
	{
		const cartItem: CartItem = new CartItem(product);

		this.cartService.addToCart(cartItem);
	}
}
