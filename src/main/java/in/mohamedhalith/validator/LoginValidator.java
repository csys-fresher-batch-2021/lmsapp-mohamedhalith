package in.mohamedhalith.validator;

import java.util.List;

import in.mohamedhalith.model.Employee;
import in.mohamedhalith.service.EmployeeManager;
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
	 */
	public static boolean adminVerification(String username, String password, String role) {
		boolean valid = false;
		// Checks for valid user-name,password and role
		if (UsernameValidator.isValidUsername(username) && PasswordValidator.isValidPassword(password)
				&& StringValidator.isValidString(role)) {
			if (role.equalsIgnoreCase("admin")) {
				// Verifies user details with the admin credentials
				if (username.equals("hradmin") && password.equals("realadmin")) {
					valid = true;
				}
			}
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
	 */
	public static boolean employeeVerification(String username, String password, String role) {
		boolean valid = false;
		// Checks for valid user-name, password and role
		if (UsernameValidator.isValidUsername(username) && PasswordValidator.isValidPassword(password)
				&& StringValidator.isValidString(role)) {
			if (role.equalsIgnoreCase("employee")) {
				// Verifies user name and password with employee list
				List<Employee> employeeList = EmployeeManager.getEmployeeList();
				for (Employee employee : employeeList) {
					if (username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {
						valid = true;
					}
				}

			}
		}
		return valid;
	}
}
