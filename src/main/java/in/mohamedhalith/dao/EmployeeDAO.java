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
import in.mohamedhalith.util.ConnectionUtil;

public class EmployeeDAO {
	private EmployeeDAO() {
		// Default constructor
	}

	private static final EmployeeDAO instance = new EmployeeDAO();

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
	public List<Employee> getEmployeeList() throws DBException, ValidationException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			// Get Database connection
			connection = ConnectionUtil.getConnection();
			// Query to be executed
			String query = "select * from employees ";
			// Converting query to statement
			statement = connection.prepareStatement(query);
			// Executing query
			result = statement.executeQuery();
			List<Employee> employeeList = new ArrayList<>();

			while (result.next()) {
				Employee employee = returnAsEmployee(result);
				employeeList.add(employee);
			}
			return employeeList;

		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to get Employee list");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}

	private Employee returnAsEmployee(ResultSet result) throws ValidationException, SQLException {
		Employee employee = new Employee();
		employee.setName(result.getString("name"));
		employee.setId(result.getInt("id"));
		employee.setEmployeeId(result.getInt("employeeid"));
		employee.setUsername(result.getString("username"));
		employee.setPassword(result.getString("password"));
		employee.setEmail(result.getString("email"));
		String mobile = String.valueOf(result.getLong("mobilenumber"));
		employee.setMobileNumber(mobile);
		employee.setSickLeave(result.getInt("sickleave"));
		employee.setCasualLeave(result.getInt("casualleave"));
		employee.setEarnedLeave(result.getInt("earnedleave"));
		return employee;
	}

	/**
	 * This method is used to return a specific employee. Employee username is
	 * obtained and returns employee with that username.
	 * 
	 * @param username
	 * @return
	 * @throws DBException
	 * @throws ValidationException
	 */
	public Employee getEmployee(String username) throws DBException, ValidationException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();
			String query = "select * from employees where username = ?";

			statement = connection.prepareStatement(query);
			statement.setString(1, username);

			result = statement.executeQuery();
			if (result.next()) {
				Employee employee = returnAsEmployee(result);
				return employee;
			} else {
				throw new SQLException("Employee not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to get employee");
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}

	/**
	 * This method is used to update the leave balance of employee after applying
	 * for a leave request
	 * 
	 * @param employee
	 * @param type
	 * @param duration
	 * @throws DBException
	 */
	public void updateLeaveBalance(Employee employee, String type, int duration) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;
		String username = employee.getUsername();
		type = type.toLowerCase();
		try {
			connection = ConnectionUtil.getConnection();
			String query = "update employees set " + type + " = " + type + "- ? where username = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, duration);
			statement.setString(2, username);
			statement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Cannot apply the leave request");
		} finally {
			ConnectionUtil.closeConnection(connection, statement);
		}
	}
}
