package in.mohamedhalith.validator;

import in.mohamedhalith.dao.EmployeeDAO;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.util.StringValidator;

public class EmployeeValidator {
	private EmployeeValidator() {
		// Default Constructor
	}

	public static void isEmployee(String username) throws ValidationException {
		EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
		try {
			StringValidator.isValidUsername(username);
			employeeDAO.getEmployee(username);
		} catch (DBException | ValidationException e) {
			throw new ValidationException(e, e.getMessage());
		}
		
	}
}
