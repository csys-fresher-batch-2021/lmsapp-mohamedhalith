package in.mohamedhalith.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.mohamedhalith.exception.ValidationException;

public class StringValidator {

	private StringValidator() {
		// Default Constructor
	}

	public static void isValidString(String string) throws ValidationException {
		if (string == null || string.trim().isEmpty()) {
			throw new ValidationException("String should not be null or empty");
		}
	}

	/**
	 * This method is used to validate the given password and return true if it satifies the 
	 * regular expression.
	 * 
	 * Return true for a password with 8 or more alphanumeric characters.
	 * @param password
	 * @return boolean
	 * @throws ValidationException 
	 */
	public static void isValidPassword(String password) throws ValidationException {
		String regex = "[A-Za-z0-9]{8,}";
		StringValidator.isValidString(password);
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(password);
		boolean valid = match.matches();
		if(!valid) {
			throw new ValidationException("Given password is invalid");
		}
		
	}

	/**
	 * This method is used to validate the given username and returns true if it
	 * matches the required pattern.
	 * 
	 * Returns true for a username with 7 or more alphanumeric characters.
	 * 
	 * @param username
	 * @return boolean
	 * @throws ValidationException 
	 */
	public static void isValidUsername(String username) throws ValidationException {
		String regex = "[A-Za-z0-9]{7,}";
		StringValidator.isValidString(username);
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(username);
		boolean valid = match.matches();
		if(!valid) {
			throw new ValidationException("Invalid username");
		}
		
	}
}