package in.mohamedhalith.dao;

import java.sql.Connection;
import java.sql.Date;
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

	private static final String EMPLOYEE_ID = "employee_id";
	private static final String EMPLOYEE_ERROR_MESSAGE ="Failed to get employee";
	
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
	public List<Employee> findAll() throws DBException {
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
	private Employee returnAsEmployee(ResultSet result, Employee employee) throws SQLException {
		employee.setId(result.getInt("id"));
		employee.setName(result.getString("name"));
		employee.setEmployeeId(result.getInt(EMPLOYEE_ID));
		employee.setUsername(result.getString("username"));
		return employee;
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
	public Employee findByEmployeeId(int employeeId) throws DBException {

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
			throw new DBException(e, EMPLOYEE_ERROR_MESSAGE);
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
	public Employee findByUsernameAndPassword(String username, String password) throws DBException {

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

	public boolean save(Employee employee) throws DBException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionUtil.getConnection();

			String query = "INSERT INTO employees (name,employee_id,mobile_number,email,username,password,joined_date)"
					+ "values(?,?,?,?,?,?,?)";

			statement = connection.prepareStatement(query);
			statement.setString(1, employee.getName());
			statement.setInt(2, employee.getEmployeeId());
			statement.setLong(3, employee.getMobileNumber());
			statement.setString(4, employee.getEmail());
			statement.setString(5, employee.getUsername());
			statement.setString(6, employee.getPassword());
			statement.setDate(7, Date.valueOf(employee.getJoinedDate()));

			int row = statement.executeUpdate();
			boolean isAdded = false;
			if (row == 1) {
				isAdded = true;
			}
			return isAdded;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, "Failed to add employee");
		} finally {
			ConnectionUtil.closeConnection(connection, statement);
		}
	}

	/**
	 * This method is used to check whether an employee id exists or not
	 * 
	 * @param employeeId
	 * @return
	 * @throws DBException
	 */
	public boolean exists(int employeeId) throws DBException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();
			String query = "select id from employees where employee_id = ? and active = true";

			statement = connection.prepareStatement(query);
			statement.setInt(1, employeeId);

			result = statement.executeQuery();
			boolean isExist = false;
			if (result.next()) {
				isExist = true;
			}
			return isExist;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, EMPLOYEE_ERROR_MESSAGE);
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}

	/**
	 * This method is used to verify given email id is present in the records or not
	 * 
	 * @param email
	 * @return boolean
	 * @throws DBException
	 */
	public boolean exists(String email) throws DBException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();
			String query = "select id from employees where email = ? and active = true";

			statement = connection.prepareStatement(query);
			statement.setString(1, email);

			result = statement.executeQuery();
			boolean isExist = false;
			if (result.next()) {
				isExist = true;
			}
			return isExist;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, EMPLOYEE_ERROR_MESSAGE);
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}

	/**
	 * This method is used to verify given mobile number is present in the records
	 * or not
	 * 
	 * @param email
	 * @return boolean
	 * @throws DBException
	 */
	public boolean exists(long mobileNumber) throws DBException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtil.getConnection();
			String query = "select id from employees where mobile_number = ? and active = true";

			statement = connection.prepareStatement(query);
			statement.setLong(1, mobileNumber);

			result = statement.executeQuery();
			boolean isExist = false;
			if (result.next()) {
				isExist = true;
			}
			return isExist;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e, EMPLOYEE_ERROR_MESSAGE);
		} finally {
			ConnectionUtil.closeConnection(connection, statement, result);
		}
	}
}
