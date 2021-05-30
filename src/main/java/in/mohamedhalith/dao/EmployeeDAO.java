package in.mohamedhalith.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveBalance;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.util.ConnectionUtil;

public class EmployeeDAO {
	private EmployeeDAO() {
		// Default constructor
	}

	private static final EmployeeDAO instance = new EmployeeDAO();

	private static final String EMPLOYEE_ID = "employee_id";

	public static EmployeeDAO getInstance() {
		return instance;
	}

	/**
	 * This method is used to return the list of employees
	 * 
	 * @return List<Employee>
	 * @throws DBException
	 * @throws ValidationException
	 */
	public List<Employee> findAll() throws DBException, ValidationException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			// Get Database connection
			connection = ConnectionUtil.getConnection();
			// Query to be executed
			String query = "select id,name,employee_id,username from employees where active = true order by name asc";
			// Converting query to statement
			statement = connection.prepareStatement(query);
			// Executing query
			result = statement.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			Employee employee = null;
			while (result.next()) {
				employee = new Employee();
				returnAsEmployee(result, employee);
				employeeList.add(employee);
			}
			return employeeList;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to get employees");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}

	/**
	 * This is a method used to convert result set into an employee object(instance)
	 * 
	 * @param result
	 * @param employee
	 * @return
	 * @throws SQLException
	 * @throws ValidationException
	 */
	private Employee returnAsEmployee(ResultSet result, Employee employee) throws SQLException, ValidationException {
		employee.setId(result.getInt("id"));
		employee.setName(result.getString("name"));
		employee.setEmployeeId(result.getInt(EMPLOYEE_ID));
		employee.setUsername(result.getString("username"));
		return employee;
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
	public boolean updateLeaveBalance(String keyword, int employeeId, LeaveRequest leaveRequest) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		String leaveType = leaveRequest.getType().toLowerCase();
		String query = null;
		String apply = "apply";
		String cancel = "cancel";
		String reject = "reject";
		try {
			connection = ConnectionUtil.getConnection();
			switch (leaveType) {
			case "sickleave":
				if (keyword.equalsIgnoreCase(apply)) {
					query = "update employee_leavebalance set leave_balance = leave_balance - ? where employee_id = ? and type_of_leave = \'sickleave\'";
				} else if (keyword.equalsIgnoreCase(cancel) || keyword.equalsIgnoreCase(reject)) {
					query = "update employee_leavebalance set leave_balance = leave_balance + ? where employee_id = ? and type_of_leave = \'sickleave\'";
				}
				break;
			case "casualleave":
				if (keyword.equalsIgnoreCase(apply)) {
					query = "update employee_leavebalance set leave_balance = leave_balance - ? where employee_id = ? and type_of_leave = \'casualleave\'";
				} else if (keyword.equalsIgnoreCase(cancel) || keyword.equalsIgnoreCase(reject)) {
					query = "update employee_leavebalance set leave_balance = leave_balance + ? where employee_id = ? and type_of_leave = \'casualleave\'";
				}
				break;
			default:
				if (keyword.equalsIgnoreCase(apply)) {
					query = "update employee_leavebalance set leave_balance = leave_balance - ? where employee_id = ? and type_of_leave = \'earnedleave\'";
				} else if (keyword.equalsIgnoreCase(cancel) || keyword.equalsIgnoreCase(reject)) {
					query = "update employee_leavebalance set leave_balance = leave_balance + ? where employee_id = ? and type_of_leave = \'earnedleave\'";
				}
			}
			statement = connection.prepareStatement(query);
			statement.setInt(1, leaveRequest.getDuration());
			statement.setInt(2, employeeId);
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
	 * This method is used to return a specific employee. Employee ID is obtained
	 * and returns employee with that Employee ID.
	 * 
	 * @param username
	 * @return Employee
	 * @throws DBException
	 * @throws ValidationException
	 */
	public Employee findByEmployeeId(int employeeId) throws DBException, ValidationException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();
			String query = "select id,name,username,employee_id from employees where employee_id = ? and active = true";

			statement = connection.prepareStatement(query);
			statement.setInt(1, employeeId);

			result = statement.executeQuery();
			Employee employee = null;
			if (result.next()) {
				employee = new Employee();
				employee = returnAsEmployee(result, employee);
			}
			return employee;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to get employee");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}

	/**
	 * This method is used to find employee by username and password.
	 * 
	 * @param username
	 * @param password
	 * @return Employee
	 * @throws DBException
	 * @throws ValidationException
	 */
	public Employee findByUsernameAndPassword(String username, String password)
			throws DBException, ValidationException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = ConnectionUtil.getConnection();

			String query = "select id,name,employee_id from employees where username = ? and password = ?";

			statement = connection.prepareStatement(query);

			statement.setString(1, username);
			statement.setString(2, password);

			result = statement.executeQuery();
			Employee employee = null;
			if (result.next()) {
				employee = new Employee();
				employee.setId(result.getInt("id"));
				employee.setName(result.getString("name"));
				employee.setEmployeeId(result.getInt(EMPLOYEE_ID));
			}
			return employee;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to get user details");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
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
				}else if (leaveType.equalsIgnoreCase("earnedleave")) {
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
	 * This method is used to find id of an employee by using username as reference.
	 * 
	 * @param username
	 * @return Integer
	 * @throws DBException
	 */
	public Integer findEmployeeId(String username) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();

			String query = "select employee_id from employees where username = ?";

			statement = connection.prepareStatement(query);

			statement.setString(1, username);
			result = statement.executeQuery();
			Integer employeeId = null;
			if (result.next()) {
				employeeId = result.getInt(EMPLOYEE_ID);
			}
			return employeeId;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to get employee id");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}
}
