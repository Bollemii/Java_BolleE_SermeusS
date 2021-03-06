package DataAccess;

import Exceptions.DataException;

import java.sql.*;

public class SingletonConnexion {
	private static Connection connection;

	private SingletonConnexion() throws SQLException {
		connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/java_project",
				"root",
				"root"
		);
	}

	public static Connection getInstance() throws DataException {
		if (connection == null) {
			try {
				new SingletonConnexion();
			} catch (SQLException exception) {
				throw new DataException(exception.getMessage());
			}
		}
		return connection;
	}
}
