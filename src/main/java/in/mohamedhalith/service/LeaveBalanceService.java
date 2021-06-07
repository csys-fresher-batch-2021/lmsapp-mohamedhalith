package in.mohamedhalith.service;

import in.mohamedhalith.dao.LeaveBalanceDAO;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.LeaveBalance;
import in.mohamedhalith.validator.EmployeeValidator;

public class LeaveBalanceService {

	private LeaveBalanceService() {
		// Default Constructor
	}

	private static LeaveBalanceDAO leaveBalanceDAO = LeaveBalanceDAO.getInstance();

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
			employeeLeaveBalance = leaveBalanceDAO.findLeaveBalance(employeeId);
			// If output is null, no data is found
			if (employeeLeaveBalance == null) {
				throw new ServiceException("Invalid employee id");
			}
			return employeeLeaveBalance;
		} catch (DBException e) {
			throw new ServiceException(e, "Unable to get employee leave balance");
		}
	}

	public static void remove(int employeeId) throws ServiceException, ValidationException {
		try {
			EmployeeValidator.isEmployee(employeeId);
			boolean isRemoved = leaveBalanceDAO.remove(employeeId);
			// If output is false, performed operation is not expected output
			if (!isRemoved) {
				throw new ServiceException("Unable to remove employee leave balance");
			}
		} catch (DBException e) {
			throw new ServiceException(e,"Unable to remove employee leave balance");
		}

	}
}
