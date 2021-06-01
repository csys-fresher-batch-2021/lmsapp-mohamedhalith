package in.mohamedhalith.service;

import java.util.List;

import in.mohamedhalith.constant.UpdateAction;
import in.mohamedhalith.dao.LeaveBalanceDAO;
import in.mohamedhalith.dao.LeaveRequestDAO;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.util.StringValidator;
import in.mohamedhalith.validator.EmployeeValidator;
import in.mohamedhalith.validator.LeaveRequestValidator;

public class LeaveRequestService {

	private LeaveRequestService() {
		// Default Constructor
	}

	private static LeaveRequestDAO leaveRequestDAO = LeaveRequestDAO.getInstance();
	private static LeaveBalanceDAO leaveBalanceDAO = LeaveBalanceDAO.getInstance();

	/**
	 * This method is used to get list of leave requests.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public static List<LeaveRequest> getRequestList() throws ServiceException {
		try {
			return leaveRequestDAO.findAll();
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
			EmployeeValidator.isEmployee(employeeId);
			return leaveRequestDAO.findRequestsByEmployeeId(employeeId);
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
			LeaveRequestValidator.isValidRequest(leaveRequest, employeeId);
			leaveBalanceDAO.updateLeaveBalance(UpdateAction.APPLY,employeeId, leaveRequest);
			return leaveRequestDAO.save(leaveRequest);
		} catch (DBException e) {
			throw new ServiceException(e,"Unable to apply for leave");
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
	public static List<LeaveRequest> getUnapprovedRequest(int employeeId)
			throws ServiceException, ValidationException {
		try {
			EmployeeValidator.isEmployee(employeeId);
			return leaveRequestDAO.findPendingRequests(employeeId);
		} catch (DBException e) {
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
			leaveBalanceDAO.updateLeaveBalance(UpdateAction.CANCEL,employeeId, leaveRequest);
			return leaveRequestDAO.update("cancel",leaveId);
		} catch (DBException e) {
			throw new ServiceException(e, "Unable to cancel the selected request");
		}
	}

	public static boolean updateLeaveRequest(String action, int leaveId, int employeeId) throws ValidationException, ServiceException {
		try {
			LeaveRequestValidator.isValidId(leaveId);
			StringValidator.isValidAction(action);
			EmployeeValidator.isEmployee(employeeId);
			LeaveRequest leaveRequest = leaveRequestDAO.findById(leaveId);
			if(action.equalsIgnoreCase("reject")) {
				leaveBalanceDAO.updateLeaveBalance(UpdateAction.REJECT,employeeId, leaveRequest);
			}
			return leaveRequestDAO.update(action,leaveId);
		} catch (DBException e) {
			throw new ServiceException(e, "Unable to cancel the selected request");
		}
	}
	
	public static LeaveRequest getLeaveRequest(int leaveId) throws ServiceException {
		LeaveRequest leaveRequest = null;
		try {
			leaveRequest = leaveRequestDAO.findById(leaveId);
			if(leaveRequest == null) {
				throw new ServiceException("Invalid Leave request Id");
			}
			return leaveRequest;
		} catch (DBException e) {
			throw new ServiceException("Failed to get leave requests");
		}
	}
}
