package in.mohamedhalith.validator;

import in.mohamedhalith.dao.EmployeeDAO;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.service.EmployeeService;
import in.mohamedhalith.util.DateValidator;
import in.mohamedhalith.util.NumberValidator;
import in.mohamedhalith.util.StringValidator;

public class EmployeeValidator {
	private EmployeeValidator() {
		// Default Constructor
	}

	private static final String INVALIDEMPLOYEE = "Invalid Employee details";
	private static final EmployeeDAO employeeDAO = EmployeeDAO.getInstance();

	/**
	 * This method is used to verify whether the user is an employee or not
	 * 
	 * @param username
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public static void isEmployee(String username) throws ValidationException, ServiceException {
		try {
			EmployeeService.getEmployeeId(username);
		} catch (ServiceException e) {
			throw new ValidationException(INVALIDEMPLOYEE);
		}
	}

	/**
	 * This method is used to validate or verify given employee id
	 * 
	 * @param employeeId
	 * @return boolean
	 * @throws ValidationException
	 */
	public static boolean isEmployee(int employeeId) throws ValidationException {
		Employee employee = null;
		try {
			employee = EmployeeService.getEmployee(employeeId);
			boolean isValid = false;
			if (employee != null) {
				isValid = true;
			}
			return isValid;
		} catch (ServiceException e) {
			throw new ValidationException(INVALIDEMPLOYEE);
		}
	}

	/**
	 * This method is used to validates the fields of the employee and verifies
	 * whether each of them is valid
	 * 
	 * @param employeeId
	 * @throws ValidationException
	 */
	public static void isValidEmployee(Employee employee) throws ValidationException {
		StringValidator.isValidName(employee.getName());
		StringValidator.isValidEmail(employee.getEmail());
		StringValidator.isValidPassword(employee.getPassword());
		StringValidator.isValidUsername(employee.getUsername());
		NumberValidator.isValidMobileNumber(employee.getMobileNumber());
		try {
			boolean isValid = employeeDAO.exists(employee.getEmployeeId());
			if (isValid) {
				throw new ValidationException("Employee Id already exists");
			}
			isValid = employeeDAO.exists(employee.getMobileNumber());
			if (isValid) {
				throw new ValidationException("Mobile Number already exists");
			}
			isValid = employeeDAO.exists(employee.getEmail());
			if (isValid) {
				throw new ValidationException("Email Id already exists");
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
		DateValidator.isValidJoinedDate(employee.getJoinedDate());
	}
}
