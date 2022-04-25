package DataAccess;

import Exceptions.DataException;

import java.sql.*;

public class SingletonConnexion {
	private static Connection connection;

	private SingletonConnexion() throws SQLException {
		connection = DriverManager.getConnection(
				"jdbc:mysql://DESKTOP-G9LT55S:3306/java_project",
				"bolle",
				"1111"
		);
		/*connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/java_project",
				"root",
				"root"
		);*/
	}

	public static Connection getConnection() throws DataException {
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
