package in.mohamedhalith.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import in.mohamedhalith.util.StringValidator;

public class UsernameValidator {

	private UsernameValidator() {
		// Default Constructor
	}

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
