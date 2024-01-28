import { FormControl, ValidationErrors } from "@angular/forms";

export class ShopFormValidators 
{
	// White space validation
	static notOnlyWhiteSpace(control: FormControl): ValidationErrors
	{
		// Check if string only has white space
		if ((control.value != null) && (control.value.trim().length == 0))
		{
			// Invalid
			return {"notOnlyWhiteSpace": true}
		}

		// Valid
		return null;
	}
}
