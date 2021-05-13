package in.mohamedhalith.util;

public class StringValidator {
	
	private StringValidator() {
		//Default Constructor
	}
	
	public static boolean isValidString(String string) {
		boolean valid = true;
		if(string==null || string.trim().isEmpty()) {
			valid = false;
		}
		return valid;
	}
}
