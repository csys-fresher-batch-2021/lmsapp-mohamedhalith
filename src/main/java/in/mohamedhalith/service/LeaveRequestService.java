package in.mohamedhalith.service;

import java.util.List;

import in.mohamedhalith.dao.EmployeeDAO;
import in.mohamedhalith.dao.LeaveRequestDAO;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.validator.EmployeeValidator;
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
	public static List<LeaveRequest> getEmployeeRequests(int employeeId) throws ServiceException, ValidationException {
		try {
			Employee employee = EmployeeService.getEmployee(employeeId);
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
	public static boolean applyLeaveRequest(LeaveRequest leaveRequest, int employeeId)
			throws ServiceException, ValidationException {

		try {
			EmployeeValidator.isEmployee(employeeId);
			List<LeaveRequest> employeeRequests = LeaveRequestService.getEmployeeRequests(employeeId);
			LeaveRequestValidator.isValidRequest(leaveRequest, employeeId, employeeRequests);
			employeeDAO.updateLeaveBalance("apply",employeeId, leaveRequest);
			return leaveRequestDAO.applyLeaveRequest(leaveRequest);
		} catch (DBException e) {
			throw new ServiceException(e, e.getMessage());
		}
	}

	/**
	 * This method is used to get the unapproved (request in waiting for approval)
	 * of an employee
	 * 
	 * Returns a list of LeaveRequests
	 * 
	 * @param username
	 * @return List<LeaveRequest>
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static List<LeaveRequest> getUnapprovedRequest(String username)
			throws ServiceException, ValidationException {
		try {
			Employee employee = EmployeeService.getEmployee(username);
			return leaveRequestDAO.getUnApprovedRequests(employee);
		} catch (ServiceException | DBException e) {
			throw new ServiceException(e, "Unable to get unapproved requests");
		}
	}

	/**
	 * This method is used to cancel leave request applied by an employee
	 * 
	 * Returns a boolean value, true if successfully cancelled.
	 * 
	 * @param leaveId
	 * @param username
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static boolean cancelLeaveRequest(int leaveId, int employeeId)
			throws ServiceException, ValidationException {
		try {
			LeaveRequestValidator.isValidId(leaveId);
			EmployeeValidator.isEmployee(employeeId);
			LeaveRequest leaveRequest = leaveRequestDAO.findById(leaveId);
			employeeDAO.updateLeaveBalance("cancel",employeeId, leaveRequest);
			return leaveRequestDAO.cancelLeaveRequest(leaveId);
		} catch (DBException e) {
			throw new ServiceException(e, "Unable to cancel the selected request");
		}
	}
}
