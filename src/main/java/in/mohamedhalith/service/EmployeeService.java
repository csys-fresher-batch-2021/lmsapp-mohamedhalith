package in.mohamedhalith.service;

import java.util.List;

import in.mohamedhalith.dao.EmployeeDAO;
import in.mohamedhalith.dao.LeaveBalanceDAO;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.util.NumberValidator;
import in.mohamedhalith.util.StringValidator;
import in.mohamedhalith.validator.EmployeeValidator;

public class EmployeeService {

	private EmployeeService() {
		// Default Constructor
	}

	private static EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
	private static LeaveBalanceDAO leaveBalanceDAO = LeaveBalanceDAO.getInstance();

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
			NumberValidator.isValidEmployeeId(employeeId);
			employee = employeeDAO.findByEmployeeId(employeeId);
			if (employee == null) {
				throw new ServiceException("Invalid Employee Id");
			}
			return employee;
		} catch (DBException e) {
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
	public static Integer getEmployeeId(String username) throws ServiceException, ValidationException {
		try {
			StringValidator.isValidUsername(username);
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
			throws ServiceException {
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
	
	
	public static boolean addEmployee(Employee employee) throws ValidationException, ServiceException {
		EmployeeValidator.isValidEmployee(employee);
		try {
			boolean isAdded = employeeDAO.save(employee);
			if(!isAdded) {
				throw new ServiceException("Failed at employeeDAO");
			}
			isAdded = false;
			isAdded = leaveBalanceDAO.save(employee.getEmployeeId());
			if(!isAdded) {
				throw new ServiceException("Failed at leavebalandao");
			}
			return isAdded;
		} catch (DBException e) {
			throw new ServiceException(e,"Failed at service");
		}
	}
}
