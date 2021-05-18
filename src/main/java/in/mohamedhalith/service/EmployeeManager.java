package in.mohamedhalith.service;

import java.util.List;

import in.mohamedhalith.dao.EmployeeDAO;
import in.mohamedhalith.validator.EmployeeValidator;
import in.mohamedhalith.model.Employee;

public class EmployeeManager {

	private EmployeeManager() {
		// Default Constructor
	}

	private static EmployeeDAO employeeDAO = EmployeeDAO.getInstance();

	/**
	 * This method is used to return the list of employees
	 * 
	 * @return List<Employee>
	 */
	public static List<Employee> getEmployeeList() {
		return employeeDAO.getEmployeeList();
	}

	/**
	 * This method checks is used to get employee from the DAO.
	 * 
	 * Returns null if employee not found
	 * 
	 * @param username
	 * @return
	 */
	public static Employee getEmployee(String username) {
		Employee employee = null;
		if (EmployeeValidator.isEmployee(username)) {
			employee = employeeDAO.getEmployee(username);
		}
		return employee;
	}

}
