package in.mohamedhalith.service;

import java.util.List;

import in.mohamedhalith.dao.EmployeeDAO;
import in.mohamedhalith.dao.LeaveRequestDAO;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.validator.LeaveRequestValidator;

public class LeaveRequestService {

	private LeaveRequestService() {
		// Default Constructor
	}

	private static LeaveRequestDAO leaveRequestDAO = LeaveRequestDAO.getInstance();
	private static EmployeeDAO employeeDAO = EmployeeDAO.getInstance();

	/**
	 * This method is used to get list of leave requests.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public static List<LeaveRequest> getRequestList() throws ServiceException {
		try {
			return leaveRequestDAO.getRequestList();
		} catch (DBException e) {
			throw new ServiceException(e, "Failed to get list of requests");
		}
	}

	/**
	 * This method is used to get leave requests of a particular employee.
	 * 
	 * Returns the list of leave requests applied by an employee
	 * 
	 * @param username
	 * @return List<LeaveRequest>
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static List<LeaveRequest> getEmployeeRequests(String username) throws ServiceException, ValidationException {
		try {
			Employee employee = EmployeeService.getEmployee(username);
			return leaveRequestDAO.getEmployeeRequests(employee);
		} catch (DBException e) {
			throw new ServiceException(e, "Failed to get employee's requests");
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
	 * @throws ValidationException
	 */
	public static boolean applyLeaveRequest(LeaveRequest leaveRequest, String username)
			throws ServiceException, ValidationException {

		try {
			Employee employee = EmployeeService.getEmployee(username);
			List<LeaveRequest> employeeRequests = LeaveRequestService.getEmployeeRequests(username);
			LeaveRequestValidator.isValidRequest(leaveRequest, employee, employeeRequests);
			employeeDAO.updateLeaveBalance(employee, leaveRequest.getType(), leaveRequest.getDuration());
			boolean isApplied = leaveRequestDAO.applyLeaveRequest(leaveRequest);
			return isApplied;
		} catch (DBException e) {
			throw new ServiceException(e, e.getMessage());
		}
	}
}
