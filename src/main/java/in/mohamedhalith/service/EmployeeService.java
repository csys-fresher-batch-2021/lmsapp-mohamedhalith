package in.mohamedhalith.service;

import java.util.List;

import in.mohamedhalith.dao.EmployeeDAO;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.validator.EmployeeValidator;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveBalance;

public class EmployeeService {

	private EmployeeService() {
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
			return employeeDAO.findAll();
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
	
	public static Employee getEmployee(int employeeId) throws ServiceException, ValidationException {
		try {
			Employee employee = null;
			EmployeeValidator.isEmployee(employeeId);
			employee = employeeDAO.findByEmployeeId(employeeId);
			if (employee == null) {
				throw new ServiceException("Invalid Employee Id");
			}
			return employee;
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e, "No employee is found for given details");
		}
	}

	/**
	 * This method is used to get id of an employee when username is given as input
	 * 
	 * Returns an integer if valid username is given
	 * 
	 * @param username
	 * @return Integer
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static Integer getEmployeeId(String username) throws ServiceException {
		try {
			Integer employeeId = employeeDAO.findEmployeeId(username);
			if (employeeId == null) {
				throw new ServiceException("Invalid Employee username");
			}
			return employeeId;
		} catch (DBException e) {
			throw new ServiceException(e, "No employee is found for given details");
		}
	}

	/**
	 * This method is used to find the leave balance of an employee. Employee id is
	 * given as input.
	 * 
	 * Returns Employee for a valid employee id
	 * 
	 * @param employeeId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static LeaveBalance findLeaveBalance(int employeeId) throws ServiceException, ValidationException {
		try {
			LeaveBalance employeeLeaveBalance = null;
			EmployeeValidator.isEmployee(employeeId);
			employeeLeaveBalance = employeeDAO.findLeaveBalance(employeeId);
			if (employeeLeaveBalance == null) {
				throw new ServiceException("Invalid employee id");
			}
			return employeeLeaveBalance;
		} catch (DBException e) {
			throw new ServiceException(e, "Unable to get employee leave balance");
		}
	}

	/**
	 * This method is used to verify an employee by username and password
	 * 
	 * Return boolean true for valid username and password and false for invalid
	 * details
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static boolean findByUsernameAndPassword(String username, String password)
			throws ServiceException, ValidationException {
		try {
			boolean isValid = false;
			Employee employee = employeeDAO.findByUsernameAndPassword(username, password);
			if (employee != null) {
				isValid = true;
			}
			return isValid;
		} catch (DBException e) {
			throw new ServiceException(e, "Unable to verify user");
		}
	}

}
