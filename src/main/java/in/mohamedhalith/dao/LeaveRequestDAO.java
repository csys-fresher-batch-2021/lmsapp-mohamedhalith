package in.mohamedhalith.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	private static final String BASE_QUERY = "select lr.id,e.name,e.employee_id,lr.from_date,lr.to_date,lr.leave_type,lr.status,"
			+ "lr.reason,lr.created_time,lr.duration"
			+ " from leave_requests lr, employees e where e.employee_id = lr.employee_id";
	
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

	public List<LeaveRequest> findAll() throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();

			String query =  BASE_QUERY + "and status != \'cancelled\'";

			statement = connection.prepareStatement(query);

			result = statement.executeQuery();
			List<LeaveRequest> requestList = new ArrayList<>();
			while (result.next()) {
				LeaveRequest leaveRequest = new LeaveRequest();
				leaveRequest =  returnAsLeaveRequest(result,leaveRequest);
				requestList.add(leaveRequest);
			}
			return requestList;
		} catch (ValidationException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException(e, "Failed to fetch leave requests");
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
	private LeaveRequest returnAsLeaveRequest(ResultSet result,LeaveRequest leaveRequest) throws SQLException, ValidationException {
		leaveRequest.setLeaveId(result.getInt("id"));
		leaveRequest.setFromDate(result.getDate("from_date").toLocalDate());
		leaveRequest.setToDate(result.getDate("to_date").toLocalDate());
		leaveRequest.setType(result.getString("leave_type"));
		leaveRequest.setStatus(result.getString("status"));
		leaveRequest.setReason(result.getString("reason"));
		leaveRequest.setDuration(result.getInt("duration"));
		leaveRequest.setAppliedTime(result.getTimestamp("created_time").toLocalDateTime());
		Employee employee = new Employee();
		employee.setName(result.getString("name"));
		employee.setEmployeeId(result.getInt("employee_id"));
		leaveRequest.setEmployee(employee);
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
	public List<LeaveRequest> findRequestsByEmployeeId(int employeeId) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();

			String query = BASE_QUERY + " and e.employee_id = ? and status != \'cancelled\'";

			statement = connection.prepareStatement(query);
			statement.setInt(1, employeeId);
			result = statement.executeQuery();

			List<LeaveRequest> requestList = new ArrayList<>();

			while (result.next()) {
				LeaveRequest leaveRequest = new LeaveRequest();
				leaveRequest =  returnAsLeaveRequest(result,leaveRequest);
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
	public boolean save(LeaveRequest leaveRequest) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		leaveRequest.setAppliedTime(LocalDateTime.now());
		boolean isAdded = false;
		try {
			connection = ConnectionUtil.getConnection();
			String query = "insert into leave_requests (employee_id,from_date,to_date,leave_type,duration,reason,created_time)"
					+ "values(?,?,?,?,?,?,?)";

			statement = connection.prepareStatement(query);
			statement.setInt(1, leaveRequest.getEmployee().getEmployeeId());
			statement.setDate(2, Date.valueOf(leaveRequest.getFromDate()));
			statement.setDate(3, Date.valueOf(leaveRequest.getToDate()));
			statement.setString(4, leaveRequest.getType().toLowerCase());
			statement.setInt(5, leaveRequest.getDuration());
			statement.setString(6, leaveRequest.getReason());
			statement.setTimestamp(7, Timestamp.valueOf(leaveRequest.getAppliedTime()));
			int row = statement.executeUpdate();
			if (row == 1) {
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
	public List<LeaveRequest> findPendingRequests(int employeeId) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();

			String query = BASE_QUERY + " and e.employee_id = ? and status = \'Waiting for approval\'";

			statement = connection.prepareStatement(query);
			statement.setInt(1, employeeId);

			result = statement.executeQuery();
			List<LeaveRequest> requestList = new ArrayList<>();
			while (result.next()) {
				LeaveRequest leaveRequest = new LeaveRequest();
				leaveRequest =  returnAsLeaveRequest(result,leaveRequest);
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

	/**
	 * This method is used to set status of the request to cancelled
	 * 
	 * @param leaveId
	 * @return
	 * @throws DBException
	 */
	public boolean remove(int leaveId) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionUtil.getConnection();
			String query = "update leave_requests set status = \'cancelled\',modified_time = now() where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, leaveId);

			int row = statement.executeUpdate();
			boolean isCancelled = false;
			if (row == 1) {
				isCancelled = true;
			}
			return isCancelled;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to cancel the leave request");
		} finally {
			ConnectionUtil.closeConnection(connection, statement);
		}
	}

	/**
	 * This method is used to find a leave request by its id.
	 * 
	 * @param leaveId (int)
	 * @return LeaveRequest(If valid id) Null, incase invalid
	 * @throws DBException
	 */
	public LeaveRequest findById(int leaveId) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();

			String query = "select employee_id,from_date,to_date,leave_type,duration,status,created_time,reason "
					+ "from leave_requests where id = ?";

			statement = connection.prepareStatement(query);
			statement.setInt(1, leaveId);

			result = statement.executeQuery();
			LeaveRequest leaveRequest = null;
			if (result.next()) {
				leaveRequest = new LeaveRequest();
				leaveRequest.setLeaveId(leaveId);
				leaveRequest.setFromDate(result.getDate("from_date").toLocalDate());
				leaveRequest.setToDate(result.getDate("to_date").toLocalDate());
				leaveRequest.setType(result.getString("leave_type"));
				leaveRequest.setStatus(result.getString("status"));
				leaveRequest.setDuration(result.getInt("duration"));
				leaveRequest.setReason(result.getString("reason"));
				leaveRequest.setAppliedTime(result.getTimestamp("created_time").toLocalDateTime());
				Employee employee = new Employee();
				employee.setEmployeeId(result.getInt("employee_id"));
				leaveRequest.setEmployee(employee);
			}
			return leaveRequest;
		} catch (ClassNotFoundException | SQLException | ValidationException e) {
			throw new DBException("Failed to get leave request");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}
}
