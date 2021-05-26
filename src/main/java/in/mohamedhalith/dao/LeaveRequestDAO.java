package in.mohamedhalith.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.util.ConnectionUtil;

public class LeaveRequestDAO {

	private LeaveRequestDAO() {
		// Default Constructor
	}

	private static LeaveRequestDAO instance = new LeaveRequestDAO();

	/**
	 * This method is used to obtain the instance of the class LeaveDAO.
	 * 
	 * @return LeaveRequestDAO
	 */
	public static LeaveRequestDAO getInstance() {
		return instance;
	}

	/**
	 * This method is used to obtain the list of leave request from the static
	 * memory.
	 * 
	 * Returns list of leave requests.
	 * 
	 * @return List<LeaveRequest>
	 * @throws DBException
	 */

	public List<LeaveRequest> getRequestList() throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();

			String query = "select * from leaverequests where status != \'cancelled\'";

			statement = connection.prepareStatement(query);

			result = statement.executeQuery();
			List<LeaveRequest> requestList = new ArrayList<>();
			while (result.next()) {
				LeaveRequest leaveRequest = returnAsLeaveRequest(result);
				requestList.add(leaveRequest);
			}
			return requestList;
		} catch (ValidationException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException(e,"Failed to fetch leave requests");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}

	}

	/**
	 * This method is used to convert the result set obtained from the database into
	 * an instance of LeaveRequest class.
	 * 
	 * @param result
	 * @return LeaveRequest
	 * @throws SQLException
	 * @throws ValidationException
	 */
	public LeaveRequest returnAsLeaveRequest(ResultSet result) throws SQLException, ValidationException {
		LeaveRequest leaveRequest = new LeaveRequest();
		leaveRequest.setLeaveId(result.getInt("id"));
		leaveRequest.setEmployeeName(result.getString("employeename"));
		leaveRequest.setEmployeeId(result.getInt("employeeid"));
		// Converting from date to LocalDate
		LocalDate date = result.getDate("fromdate").toLocalDate();
		leaveRequest.setFromDate(date);
		
		date = result.getDate("todate").toLocalDate();
		leaveRequest.setToDate(date);
		
		leaveRequest.setDuration(result.getInt("duration"));
		leaveRequest.setReason(result.getString("reason"));
		leaveRequest.setType(result.getString("type"));
		leaveRequest.setStatus(result.getString("status"));
		// Converting from Timestamp to LocalDateTime
		LocalDateTime timestamp = result.getTimestamp("appliedtime").toLocalDateTime();
		leaveRequest.setAppliedTime(timestamp);
		if (result.getTimestamp("modifiedtime") != null && leaveRequest.getStatus().equalsIgnoreCase("cancelled")) {
			timestamp = result.getTimestamp("cancelledtime").toLocalDateTime();
			leaveRequest.setCancelledTime(timestamp);
		}
		if (result.getTimestamp("modifiedtime") != null && leaveRequest.getStatus().equalsIgnoreCase("cancelled")) {
			timestamp = result.getTimestamp("reviewedtime").toLocalDateTime();
			leaveRequest.setReviewedTime(timestamp);
		}
		return leaveRequest;
	}

	/**
	 * This method is used to obtain the leave requests applied by a particular
	 * employee.
	 * 
	 * Return a list of leave request of an employee
	 * 
	 * @param employee
	 * @return
	 * @throws DBException
	 */
	public List<LeaveRequest> getEmployeeRequests(Employee employee) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();

			String query = "select * from leaverequests where employeeid = ? and status != \'cancelled\'";

			statement = connection.prepareStatement(query);
			statement.setInt(1, employee.getEmployeeId());

			result = statement.executeQuery();
			List<LeaveRequest> requestList = new ArrayList<>();
			while (result.next()) {
				LeaveRequest leaveRequest = returnAsLeaveRequest(result);
				requestList.add(leaveRequest);
			}
			return requestList;
		} catch (ValidationException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException(e,"Failed to fetch leave requests of employee");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}

	/**
	 * This method is used to add the leave request applied by the employee to the
	 * list.
	 * 
	 * Returns a string with appropriate message.
	 * 
	 * @param leaveRequest
	 * @param employee
	 * @return String
	 * @throws DBException
	 */
	public boolean applyLeaveRequest(LeaveRequest leaveRequest) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		leaveRequest.setAppliedTime(LocalDateTime.now());
		boolean isAdded = false;
		try {
			connection = ConnectionUtil.getConnection();
			String query = "insert into leaverequests (employeename,employeeid,fromdate,todate,duration,type,reason,appliedtime)"
					+ "values(?,?,?,?,?,?,?,?)";

			statement = connection.prepareStatement(query);
			statement.setString(1, leaveRequest.getEmployeeName());
			statement.setInt(2, leaveRequest.getEmployeeId());
			Date date = Date.valueOf(leaveRequest.getFromDate());
			statement.setDate(3, date);
			date = Date.valueOf(leaveRequest.getToDate());
			statement.setDate(4, date);
			statement.setInt(5, leaveRequest.getDuration());
			String type = leaveRequest.getType().toLowerCase();
			statement.setString(6, type);
			statement.setString(7, leaveRequest.getReason());
			Timestamp timestamp = Timestamp.valueOf(leaveRequest.getAppliedTime());
			statement.setTimestamp(8, timestamp);
			int row = statement.executeUpdate();
			if(row == 1) {
				isAdded = true;
			}
			return isAdded;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to apply leave");
		} finally {
			ConnectionUtil.closeConnection(connection, statement);
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
	public List<LeaveRequest> getUnApprovedRequests(Employee employee) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();

			String query = "select * from leaverequests where employeeid = ? and status = \'waiting for approval\'";

			statement = connection.prepareStatement(query);
			statement.setInt(1, employee.getEmployeeId());

			result = statement.executeQuery();
			List<LeaveRequest> requestList = new ArrayList<>();
			while (result.next()) {
				LeaveRequest leaveRequest = returnAsLeaveRequest(result);
				requestList.add(leaveRequest);
			}
			return requestList;
		} catch (ValidationException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException(e, "Failed to fetch leave requests of employee");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}
	
	public boolean cancelLeaveRequest(int leaveId) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionUtil.getConnection();
			String query = "update leaverequests set status = \'cancelled\',modifiedtime = now() where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, leaveId);
			
			int row = statement.executeUpdate();
			boolean isCancelled = false;
			if(row == 1) {
				isCancelled = true;
			}
			return isCancelled;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e,"Failed to cancel the leave request");
		}finally {
			ConnectionUtil.closeConnection(connection, statement);
		}
	}

	public LeaveRequest findById(int leaveId) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionUtil.getConnection();
			
			String query = "select * from leaverequests where id = ?";
			
			statement = connection.prepareStatement(query);
			statement.setInt(1, leaveId);
			
			result = statement.executeQuery();
			LeaveRequest leaveRequest = null;
			if(result.next()) {
				leaveRequest = returnAsLeaveRequest(result);
			}
			return leaveRequest;
		} catch (ClassNotFoundException | SQLException | ValidationException e) {
			throw new DBException("Failed to get leave request");
		}finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}
}
