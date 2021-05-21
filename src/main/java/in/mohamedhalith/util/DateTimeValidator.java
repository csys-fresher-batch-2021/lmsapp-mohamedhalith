package in.mohamedhalith.util;

import java.time.LocalDate;

import in.mohamedhalith.exception.ValidationException;

public class DateTimeValidator {
	
	private DateTimeValidator() {
		//Default Constructor
	}
	
	/**
	 * This method is used to check the given date is not a past date.
	 * 
	 * @param date
	 * @throws ValidationException 
	 */
	public static void isValidDate(LocalDate date) throws ValidationException {
		LocalDate today = LocalDate.now();
		if (date.isBefore(today)) {
			throw new ValidationException("Date cannot be past date");
		}
	}
}
	
