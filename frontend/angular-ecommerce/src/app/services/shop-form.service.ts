import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, of } from 'rxjs';
import { Country } from '../common/country';
import { State } from '../common/state';

@Injectable({
	providedIn: 'root'
})
export class ShopFormService 
{
	private countryUrl = "http://localhost:8080/api/countries";
	private stateUrl = "http://localhost:8080/api/states"

	constructor(private httpClient: HttpClient) 
	{ 

	}

	getCreditCardYears(): Observable<number[]>
	{
		let data: number[] = [];

		let startYear: number = new Date().getFullYear();

		for (let i: number = startYear; i <= startYear + 10; i++)
		{
			data.push(i);
		}

		return of(data);
	}

	// ***** OLD *****

	getCountries(): Observable<Country[]>
	{
		return this.httpClient.get<GetResponseCountries>(this.countryUrl).pipe(map(
			response => response._embedded.countries)
		);
	}

	getStates(countryCode: string): Observable<State[]>
	{
		let searchUrl: string = `${this.stateUrl}/search/findByCountryCode?code=${countryCode}`;

		return this.httpClient.get<GetResponseState>(searchUrl).pipe(map(
			response => response._embedded.states)
		);
	}

	// ***** NEW *****

	// getCountries(): Observable<Country[]>
	// {
	// 	return this.httpClient.get<CountryListResponse>(this.countryUrl).pipe(map(
	// 		response => response.content)
	// 	);
	// }

	// getStates(countryCode: string): Observable<State[]>
	// {
	// 	let searchUrl: string = `${this.stateUrl}/search/findByCountryCode?code=${countryCode}`;

	// 	return this.httpClient.get<StateListResponse>(searchUrl).pipe(map(
	// 		response => response.content)
	// 	);
	// }
}

// ***** OLD *****

interface GetResponseCountries
{
	_embedded:
	{
		countries: Country[];
	};
}

interface GetResponseState
{
	_embedded:
	{
		states: State[];
	};
}

// ***** NEW *****

// interface CountryListResponse
// {
// 	content: Country[];
// }

// interface StateListResponse
// {
// 	content: State[];
// }