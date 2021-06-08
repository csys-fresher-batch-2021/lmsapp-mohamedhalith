package in.mohamedhalith.validator;

import in.mohamedhalith.constant.Role;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.service.EmployeeService;
import in.mohamedhalith.util.StringValidator;

public class LoginValidator {

	private LoginValidator() {
		// Default Constructor
	}

	/**
	 * This method is used to verify whether the user's credentials are valid and
	 * also user is the admin of the system.
	 * 
	 * Returns True if the user is admin and False otherwise.
	 * 
	 * @param username
	 * @param password
	 * @param role
	 * @return boolean
	 * @throws ValidationException
	 */
	public static boolean adminVerification(String username, String password, String role) {
		boolean valid = false;
		try {
			// Checks for valid user-name,password and role
			StringValidator.isValidUsername(username);
			StringValidator.isValidPassword(password);
			StringValidator.isValidString(role);
			if (role.equalsIgnoreCase("admin")) {
				valid = EmployeeService.findByUsernameAndPassword(username, password,Role.MANAGER.toString());
			}
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		}
		return valid;
	}

	/**
	 * This method is used to verify whether the user's access code is valid and
	 * user is one of the employee of the company.
	 * 
	 * Returns True if the user is employee and False otherwise.
	 * 
	 * @param username
	 * @param password
	 * @param role
	 * @return boolean
	 * @throws ValidationException
	 */
	public static boolean employeeVerification(String username, String password, String role) {
		boolean valid = false;
		try {
			// Checks for valid user-name, password and role
			StringValidator.isValidUsername(username);
			StringValidator.isValidPassword(password);
			StringValidator.isValidString(role);
			if (role.equalsIgnoreCase("employee")) {
				// Verifies user name and password with employee list
				valid = EmployeeService.findByUsernameAndPassword(username, password,Role.EMPLOYEE.toString());
			}
		} catch (ServiceException | ValidationException e) {
			e.printStackTrace();
		}
		return valid;
	}
}
