import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Product } from '../common/product';
import { ProductCategory } from '../common/product-category';

@Injectable({
	providedIn: 'root'
})
export class ProductService 
{
	private baseUrl = "http://localhost:8080/api/products";
	private categoryUrl = "http://localhost:8080/api/product-category";

	constructor(private httpClient: HttpClient) // Angular automatically injects HttpClient
	{

	}

	getProduct(productId: number): Observable<Product>
	{
		const productUrl: string = `${this.baseUrl}/${productId}`;

		return this.httpClient.get<Product>(productUrl);
	}
	
	// ***** OLD *****
	
	searchProductListPaginate(page: number, pageSize: number, keyword: string): Observable<GetResponseProduct> 
	{
		const searchUrl: string = `${this.baseUrl}/search/findByNameContaining?name=${keyword}&`
								+ `page=${page}&size=${pageSize}`;
		
		return this.httpClient.get<GetResponseProduct>(searchUrl);
	}

	getProductList(categoryId: number): Observable<Product[]> 
	{
		const searchUrl: string = `${this.baseUrl}/search/findByCategoryId?id=${categoryId}`;
		
		return this.getProducts(searchUrl);
	}

	getProductCategories(): Observable<ProductCategory[]>
	{
		return this.httpClient.get<GetResponseProductCategory>(this.categoryUrl).pipe(
			map(response => response._embedded.productCategory)
		);
	}

	private getProducts(searchUrl: string): Observable<Product[]>
	{
		return this.httpClient.get<GetResponseProduct>(searchUrl).pipe(
			map(response => response._embedded.products)
		);
	}

	getProductListPaginate(page: number, pageSize: number, categoryId: number): Observable<GetResponseProduct> 
	{
		const searchUrl: string = `${this.baseUrl}/search/findByCategoryId?id=${categoryId}&`
								+ `page=${page}&size=${pageSize}`;
		
		return this.httpClient.get<GetResponseProduct>(searchUrl);
	}

	// ***** NEW *****

	// searchProduct(keyword: string): Observable<Product[]>
	// {
	// 	const searchUrl: string = `${this.baseUrl}/search/findByNameContaining?name=${keyword}`;

	// 	return this.httpClient.get<ProductListResponse>(searchUrl).pipe(map(
	// 		response => response.content
	// 		));
	// }

	// getProductCategories(): Observable<ProductCategory[]>
	// {
	// 	return this.httpClient.get<ProductCategoryListResponse>(this.categoryUrl).pipe(map(
	// 		response => response.content
	// 		));
	// }

	// // Categorize product by ID
	// getProductList(categoryId: number): Observable<Product[]> 
	// {
	// 	const searchUrl: string = `${this.baseUrl}/search/findByCategoryId?id=${categoryId}`;
		
	// 	return this.httpClient.get<ProductListResponse>(searchUrl).pipe(map(
	// 		response => response.content
	// 		));
	// }

	// // Search product by keyword, paginated
	// searchProductListPaginate(page: number, pageSize: number, keyword: string): Observable<ProductListResponse> 
	// {
	// 	const searchUrl: string = `${this.baseUrl}/search/findByNameContaining?name=${keyword}&`
	// 							+ `page=${page}&size=${pageSize}`;
		
	// 	return this.httpClient.get<ProductListResponse>(searchUrl);
	// }

	// // Categorize product by ID, paginated
	// getProductListPaginate(page: number, pageSize: number, categoryId: number): Observable<ProductListResponse> 
	// {
	// 	const searchUrl: string = `${this.baseUrl}/search/findByCategoryId?id=${categoryId}&`
	// 							+ `page=${page}&size=${pageSize}`;
		
	// 	return this.httpClient.get<ProductListResponse>(searchUrl);
	// }

}

// ***** OLD *****

interface GetResponseProduct
{
	_embedded:
	{
		products: Product[];
	}
	page:
	{
		size: number,
		totalElements: number,
		totalPages: number,
		number: number
	}
}

interface GetResponseProductCategory
{
	_embedded:
	{
		productCategory: ProductCategory[];
	}
}

// ***** NEW *****

// interface ProductListResponse
// {
// 	content: Product[];
// 	pageNumber: number;
// 	pageSize: number;
// 	totalElements: number;
// 	totalPages: number;
// }

// interface ProductCategoryListResponse
// {
// 	content: ProductCategory[];
// }