package aryan.SupportRequest.validation;
import org.springframework.stereotype.Component;

import aryan.SupportRequest.exceptions.exception.*;

@Component
public class validation {
	public void validateFirstName(String FirstName) {
		if(FirstName.length()> 25) {
			throw new FirstNameTooLong("First Name cannot exceed 25 characters");
		}
	}
	
	public void validateLastName(String LastName) {
		if (LastName.length()>25) {
			throw new LastNameTooLong("Last Name cannot exceet 25 characters");
		}
	}
	
	public void validateEmail(String Email) {
		if (Email.length()>50) {
			throw new EmailTooLong("Email cannot exceed 50 characters");
		}
	}
	
	public void validateTitle(String Title) {
		if (Title.length()>50) {
			throw new TitleTooLong("Title cannot exceed 50 characters");
		}
	}
	
	public void validateQuery(String Query) {
		if (Query.length()>4000) {
			throw new QueryTooLong("Query cannot exceed 4,000 characters");
		}
	}
}
