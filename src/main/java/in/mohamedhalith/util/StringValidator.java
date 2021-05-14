package in.mohamedhalith.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {

	private StringValidator() {
		// Default Constructor
	}

	public static boolean isValidString(String string) {
		boolean valid = true;
		if (string == null || string.trim().isEmpty()) {
			valid = false;
		}
		return valid;
	}
	
	/**
	 * This method is used to validate the given password and return true if it satifies the 
	 * regular expression.
	 * 
	 * Return true for a password with 8 or more alphanumeric characters.
	 * @param password
	 * @return boolean
	 */
	public static boolean isValidPassword(String password) {
		boolean valid = false;
		String regex = "[A-Za-z0-9]{8,}";
		if (StringValidator.isValidString(password)) {
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(password);
			valid = match.matches();
		}
		return valid;
	}
	
	/**
	 * This method is used to validate the given username and returns true if it
	 * matches the required pattern.
	 * 
	 * Returns true for a username with 7 or more alphanumeric characters.
	 * 
	 * @param username
	 * @return boolean
	 */
	public static boolean isValidUsername(String username) {
		boolean valid = false;
		String regex = "[A-Za-z0-9]{7,}";
		if (StringValidator.isValidString(username)) {
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(username);
			valid = match.matches();
		}
		return valid;
	}
}
