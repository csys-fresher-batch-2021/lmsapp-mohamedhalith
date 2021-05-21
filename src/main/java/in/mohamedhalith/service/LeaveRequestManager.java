package in.mohamedhalith.service;

import java.util.List;
import in.mohamedhalith.dao.LeaveRequestDAO;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.validator.LeaveRequestValidator;

public class LeaveRequestManager {

	private LeaveRequestManager() {
		// Default Constructor
	}

	private static LeaveRequestDAO leaveRequestDAO = LeaveRequestDAO.getInstance();

	/**
	 * This method is used to get list of leave requests.
	 * 
	 * @return
	 */
	public static List<LeaveRequest> getRequestList() {
		return leaveRequestDAO.getRequestList();
	}

	/**
	 * This method is used to get leave requests of a particular employee.
	 * 
	 * Returns the list of leave requests applied by an employee
	 * 
	 * @param username
	 * @return List<LeaveRequest>
	 * @throws ServiceException
	 */
	public static List<LeaveRequest> getEmployeeRequests(String username) throws ServiceException {
		try {
			Employee employee = EmployeeManager.getEmployee(username);
			return leaveRequestDAO.getEmployeeRequests(employee);
		} catch (ServiceException e) {
			throw new ServiceException(e, e.getMessage());
		}

	}

	/**
	 * This method is used to apply for leave.
	 * 
	 * Returns a message if applied successfully, else exception was thrown
	 * 
	 * @param leaveRequest
	 * @param username
	 * @return
	 * @throws ServiceException
	 */
	public static String applyLeaveRequest(LeaveRequest leaveRequest, String username) throws ServiceException {

		try {
			String message;
			Employee employee = EmployeeManager.getEmployee(username);
			List<LeaveRequest> employeeRequests = LeaveRequestManager.getEmployeeRequests(username);
			LeaveRequestValidator.isValidRequest(leaveRequest, employee, employeeRequests);
			message = leaveRequestDAO.applyLeaveRequest(leaveRequest, employee);
			return message;
		} catch (ServiceException | ValidationException | DBException e) {
			throw new ServiceException(e, e.getMessage());
		}
	}
}
