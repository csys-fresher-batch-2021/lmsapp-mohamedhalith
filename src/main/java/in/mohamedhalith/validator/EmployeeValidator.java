package in.mohamedhalith.validator;

import in.mohamedhalith.dao.EmployeeDAO;

public class EmployeeValidator {
	private EmployeeValidator() {
		// Default Constructor
	}
	
	public static boolean isEmployee(String username) {
		EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
		boolean isEmployee = false;
		if(employeeDAO.getEmployee(username) != null) {
			isEmployee = true;
		}
		return isEmployee;
	}
}
