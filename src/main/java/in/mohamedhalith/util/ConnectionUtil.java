package in.mohamedhalith.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	
	private ConnectionUtil() {
		//Default constructor
	}

	private static final String DATABASE_NAME = "lms-app";
	private static final String HOST = "localhost";
	private static final int PORT = 5432;
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";
	// jdbc:postgresql:5432//localhost/lms-app
	private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE_NAME;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection connection = null;
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return connection;
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Method overriding
	public static void closeConnection(Connection connection, Statement statement, ResultSet result) {
		try {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void closeConnection(Connection connection, Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
