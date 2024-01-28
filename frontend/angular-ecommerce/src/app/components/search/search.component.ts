import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
	selector: 'app-search',
	templateUrl: './search.component.html',
	styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit
{
	constructor(private router: Router)
	{

	}

	ngOnInit(): void
	{
		
	}

	search(userInput: String): void
	{
		console.log(String);
		this.router.navigateByUrl(`/search/${userInput}`);
	}
}
