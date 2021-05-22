package in.mohamedhalith.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import in.mohamedhalith.exception.DBException;
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

			String query = "select * from leaverequests";

			statement = connection.prepareStatement(query);

			result = statement.executeQuery();
			List<LeaveRequest> requestList = new ArrayList<>();
			while (result.next()) {
				LeaveRequest leaveRequest = new LeaveRequest();
				leaveRequest.setLeaveId(result.getInt("id"));
				leaveRequest.setEmployeeName(result.getString("employeename"));
				leaveRequest.setEmployeeId(result.getInt("employeeid"));
				leaveRequest.setFromDate(result.getObject("fromdate", LocalDate.class));
				leaveRequest.setToDate(result.getObject("todate", LocalDate.class));
				leaveRequest.setDuration(result.getInt("duration"));
				leaveRequest.setType(result.getString("type"));
				leaveRequest.setAppliedTime(result.getObject("appliedtime", LocalDateTime.class));
				leaveRequest.setCancelledTime(result.getObject("cancelledtime", LocalDateTime.class));
				leaveRequest.setReviewedTime(result.getObject("reviewedtime", LocalDateTime.class));
				requestList.add(leaveRequest);
			}
			return requestList;
		} catch (ValidationException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Failed to fetch leave requests");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}

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

			String query = "select * from leaverequests where employeeid = ?";

			statement = connection.prepareStatement(query);
			statement.setInt(1, employee.getEmployeeId());

			result = statement.executeQuery();
			List<LeaveRequest> requestList = new ArrayList<>();
			while (result.next()) {
				LeaveRequest leaveRequest = new LeaveRequest();
				leaveRequest.setLeaveId(result.getInt("id"));
				leaveRequest.setEmployeeName(result.getString("employeename"));
				leaveRequest.setEmployeeId(result.getInt("employeeid"));
				leaveRequest.setFromDate(result.getObject("fromdate", LocalDate.class));
				leaveRequest.setToDate(result.getObject("todate", LocalDate.class));
				leaveRequest.setDuration(result.getInt("duration"));
				leaveRequest.setType(result.getString("type"));
				leaveRequest.setAppliedTime(result.getObject("appliedtime", LocalDateTime.class));
				leaveRequest.setCancelledTime(result.getObject("cancelledtime", LocalDateTime.class));
				leaveRequest.setReviewedTime(result.getObject("reviewedtime", LocalDateTime.class));
				requestList.add(leaveRequest);
			}
			return requestList;
		} catch (ValidationException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Failed to fetch leave requests of employee");
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
	public String applyLeaveRequest(LeaveRequest leaveRequest) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		leaveRequest.setAppliedTime(LocalDateTime.now());
		try {
			connection = ConnectionUtil.getConnection();
			String query = "insert into leaverequests (employeename,employeeid,fromdate,todate,duration,type,reason,appliedtime)"
						+ "values(?,?,?,?,?,?,?,?)";

			statement = connection.prepareStatement(query);
			statement.setString(1, leaveRequest.getEmployeeName());
			statement.setInt(2, leaveRequest.getEmployeeId());
			statement.setObject(3, leaveRequest.getFromDate());
			statement.setObject(4, leaveRequest.getToDate());
			statement.setInt(5, leaveRequest.getDuration());
			statement.setString(6, leaveRequest.getType());
			statement.setString(7, leaveRequest.getReason());
			statement.setObject(8, leaveRequest.getAppliedTime());
			statement.executeUpdate();
			ConnectionUtil.closeConnection(connection, statement);
			return "Leave Applied Successfully";
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e,e.getMessage());
		}finally {
			ConnectionUtil.closeConnection(connection, statement);
		}
	}
}
