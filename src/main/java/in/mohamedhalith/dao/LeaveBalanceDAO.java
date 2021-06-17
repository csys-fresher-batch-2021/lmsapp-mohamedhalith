package in.mohamedhalith.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.mohamedhalith.constant.UpdateAction;
import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveBalance;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.util.ConnectionUtil;

public class LeaveBalanceDAO {

	private LeaveBalanceDAO() {
		// Default Constructor
	}

	private static final LeaveBalanceDAO instance = new LeaveBalanceDAO();
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet result = null;

	public static LeaveBalanceDAO getInstance() {
		return instance;
	}

	/**
	 * This method is used to update the leave balance of employee after applying
	 * for a leave request
	 * 
	 * @param employee
	 * @param type
	 * @param duration
	 * @return boolean
	 * @throws DBException
	 * @throws ValidationException
	 */
	public boolean updateLeaveBalance(UpdateAction action, int employeeId, LeaveRequest leaveRequest)
			throws DBException {
		String leaveType = leaveRequest.getType().toLowerCase();
		String query = null;

		try {
			connection = ConnectionUtil.getConnection();
			if (action == (UpdateAction.APPLY)) {
				query = "update employee_leavebalance set leave_balance = leave_balance - ? where employee_id = ? and type_of_leave = ?";
			} else if (action == UpdateAction.CANCEL || action == UpdateAction.REJECT) {
				query = "update employee_leavebalance set leave_balance = leave_balance + ? where employee_id = ? and type_of_leave = ?";
			}
			statement = connection.prepareStatement(query);
			statement.setInt(1, leaveRequest.getDuration());
			statement.setInt(2, employeeId);
			statement.setString(3, leaveType);
			int row = statement.executeUpdate();
			boolean isUpdated = false;
			if (row == 1) {
				isUpdated = true;
			}
			return isUpdated;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Cannot apply the leave request");
		} finally {
			ConnectionUtil.closeConnection(connection, statement);
		}
	}

	/**
	 * This method is used to find leavebalance of an employee
	 * 
	 * @param employeeId
	 * @return Employee
	 * @throws DBException
	 * @throws ValidationException
	 */
	public LeaveBalance findLeaveBalance(int employeeId) throws DBException {
		String leaveBalance = "leave_balance";
		try {
			connection = ConnectionUtil.getConnection();

			String query = "select e.id,e.name,e.employee_id,lb.type_of_leave,lb.leave_balance from employees as e, employee_leavebalance as lb"
					+ " where e.employee_id = lb.employee_id and e.employee_id = ?";

			statement = connection.prepareStatement(query);

			statement.setInt(1, employeeId);

			result = statement.executeQuery();
			LeaveBalance employeeLeaveBalance = new LeaveBalance();
			while (result.next()) {
				Employee employee = new Employee();
				employee.setId(result.getInt("id"));
				employee.setName(result.getString("name"));
				employee.setEmployeeId(employeeId);
				employeeLeaveBalance.setEmployee(employee);
				String leaveType = result.getString("type_of_leave");
				if (leaveType.equalsIgnoreCase("sickleave")) {
					employeeLeaveBalance.setSickLeave(result.getInt(leaveBalance));
				} else if (leaveType.equalsIgnoreCase("casualleave")) {
					employeeLeaveBalance.setCasualLeave(result.getInt(leaveBalance));
				} else if (leaveType.equalsIgnoreCase("earnedleave")) {
					employeeLeaveBalance.setEarnedLeave(result.getInt(leaveBalance));
				}
			}
			return employeeLeaveBalance;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to get employee leave balance");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}

	/**
	 * This method is used to insert the leave types allowed and leave balance for a
	 * new employee
	 * 
	 * Returns true if successfully added,false otherwise
	 * 
	 * @param employeeId
	 * @return boolean
	 * @throws DBException
	 */
	public boolean save(int employeeId) throws DBException {
		try {
			connection = ConnectionUtil.getConnection();

			String query = "INSERT INTO employee_leavebalance(employee_id,type_of_leave,leave_balance,modified_time)"
					+ "values(?,\'sickleave\',0,now())," + "(?,\'casualleave\',0,now()),"
					+ "(?,\'earnedleave\',0,now())";
			statement = connection.prepareStatement(query);
			statement.setInt(1, employeeId);
			statement.setInt(2, employeeId);
			statement.setInt(3, employeeId);

			int row = statement.executeUpdate();
			boolean isAdded = false;
			if (row == 3) {
				isAdded = true;
			}
			return isAdded;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to add employee's leavebalance");
		} finally {
			ConnectionUtil.closeConnection(connection, statement);
		}
	}

	/**
	 * This method is used to make employee leavebalance inactive
	 * @param employeeId
	 * @return boolean
	 * @throws DBException
	 */
	public boolean remove(int employeeId) throws DBException {
		try {
			connection = ConnectionUtil.getConnection();

			String query = "UPDATE employee_leavebalance set active = false where employee_id = ? ";

			statement = connection.prepareStatement(query);
			statement.setInt(1, employeeId);
			int row = statement.executeUpdate();
			boolean isRemoved = false;
			if (row == 3) {
				isRemoved = true;
			}
			return isRemoved;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to remove employee leave balance");
		}
	}
}
