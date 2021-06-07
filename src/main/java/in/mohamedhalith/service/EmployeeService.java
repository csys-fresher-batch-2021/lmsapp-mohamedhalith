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
	public static List<Employee> getEmployeeList() throws ServiceException {
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
			// If employee is null, no data is found for given id
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
			// If employee is null, no data is found for given username
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
	public static boolean findByUsernameAndPassword(String username, String password) throws ServiceException {
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

	/**
	 * This method is used to add any new recruit or employee to the organisation
	 * 
	 * @param employee
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static boolean addEmployee(Employee employee) throws ServiceException, ValidationException {
		EmployeeValidator.isValidEmployee(employee);
		String errorMessage = "Unable to add employee";
		try {
			boolean isAdded = employeeDAO.save(employee);
			// If isAdded is false, performed operation is not expected operation
			if (!isAdded) {
				throw new ServiceException(errorMessage);
			}
			isAdded = leaveBalanceDAO.save(employee.getEmployeeId());
			// If isAdded is false, performed operation is not expected operation
			if (!isAdded) {
				throw new ServiceException(errorMessage);
			}
			return isAdded;
		} catch (DBException e) {
			throw new ServiceException(e, errorMessage);
		}
	}

	/**
	 * This method is used to remove employee from the organization
	 * 
	 * @param employeeId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public static boolean removeEmployee(int employeeId) throws ValidationException, ServiceException {
		try {
			EmployeeValidator.isEmployee(employeeId);
			boolean isRemoved = employeeDAO.remove(employeeId);
			// If isAdded is false, performed operation is not expected operation
			if (!isRemoved) {
				throw new ServiceException("Unable to remove employee");
			}
			isRemoved = leaveBalanceDAO.remove(employeeId);
			return isRemoved;
		} catch (DBException e) {
			throw new ServiceException("Unable to remove employee");
		}
	}
}
