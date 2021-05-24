package in.mohamedhalith.service;

import java.util.List;

import in.mohamedhalith.dao.EmployeeDAO;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
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
	 * @throws ServiceException
	 * @throws ValidationException 
	 */
	public static List<Employee> getEmployeeList() throws ServiceException, ValidationException {
		try {
			return employeeDAO.getEmployeeList();
		} catch (DBException e) {
			throw new ServiceException(e, e.getMessage());
		}
	}

	/**
	 * This method checks is used to get employee from the DAO.
	 * 
	 * Returns null if employee not found
	 * 
	 * @param username
	 * @return Employee
	 * @throws ServiceException
	 * @throws ValidationException 
	 */
	public static Employee getEmployee(String username) throws ServiceException, ValidationException {
		try {
			Employee employee = null;
			EmployeeValidator.isEmployee(username);
			employee = employeeDAO.getEmployee(username);
			return employee;
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e, "No employee is found for given details");
		}

	}

}
