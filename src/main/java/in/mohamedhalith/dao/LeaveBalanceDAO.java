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

	private static final LeaveBalanceDAO instance = new LeaveBalanceDAO();

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
	public boolean updateLeaveBalance(UpdateAction action, int employeeId, LeaveRequest leaveRequest) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		String leaveType = leaveRequest.getType().toLowerCase();
		String query = null;
		String keyword = action.toString();
//		String apply = "apply";
//		String cancel = "cancel";
//		String reject = "reject";
		
		try {
			connection = ConnectionUtil.getConnection();
			if (keyword.equalsIgnoreCase("apply")) {
				query = "update employee_leavebalance set leave_balance = leave_balance - ? where employee_id = ? and type_of_leave = ?";
			} else if (keyword.equalsIgnoreCase("cancel") || keyword.equalsIgnoreCase("reject")) {
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
	public LeaveBalance findLeaveBalance(int employeeId) throws DBException, ValidationException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
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

}
