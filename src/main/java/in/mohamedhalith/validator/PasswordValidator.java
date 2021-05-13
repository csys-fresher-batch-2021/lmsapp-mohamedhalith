package in.mohamedhalith.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.mohamedhalith.util.StringValidator;

public class PasswordValidator {
	private PasswordValidator() {
		// Default Constructor
	}

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
}
