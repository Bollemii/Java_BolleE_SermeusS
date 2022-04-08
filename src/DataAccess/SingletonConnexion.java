package DataAccess;

import java.sql.*;

public class SingletonConnexion {
	private static Connection connection;

	private SingletonConnexion() throws SQLException {
		connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/library",
				"root",
				"root"
		);
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
