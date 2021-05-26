package in.mohamedhalith.validator;

import java.util.List;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.service.EmployeeService;
import in.mohamedhalith.util.StringValidator;

public class EmployeeValidator {
	private EmployeeValidator() {
		// Default Constructor
	}

	/**
	 * This method is used to verify whether the user is an employee or not
	 * 
	 * @param username
	 * @throws ValidationException
	 * @throws ServiceException 
	 */
	public static void isEmployee(String username) throws ValidationException, ServiceException {
		boolean valid = false;
		StringValidator.isValidUsername(username);
		List<Employee> employeeList = EmployeeService.getEmployeeList();
		for (Employee employee : employeeList) {
			if (employee.getUsername().equals(username)) {
				valid = true;
				break;
			}
		}
		if(!valid) {
			throw new ValidationException("Invalid employee details");
		}
	}
	
	
	public static void isEmployee(int employeeId) throws ValidationException, ServiceException {
		boolean valid = false;
		List<Employee> employeeList = EmployeeService.getEmployeeList();
		for (Employee employee : employeeList) {
			if (employee.getEmployeeId() == employeeId) {
				valid = true;
				break;
			}
		}
		if(!valid) {
			throw new ValidationException("Invalid employee details");
		}
	}
}
