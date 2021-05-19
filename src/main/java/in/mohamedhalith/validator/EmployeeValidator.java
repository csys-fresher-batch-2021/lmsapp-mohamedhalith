package in.mohamedhalith.validator;

import in.mohamedhalith.dao.EmployeeDAO;
import in.mohamedhalith.exception.ValidationException;

public class EmployeeValidator {
	private EmployeeValidator() {
		// Default Constructor
	}

	public static void isEmployee(String username) throws ValidationException {
		EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
		if (employeeDAO.getEmployee(username) == null) {
			throw new ValidationException("No employee found");
		}
	}
}
