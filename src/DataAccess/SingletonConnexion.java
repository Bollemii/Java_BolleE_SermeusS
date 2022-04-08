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

	public static Connection getConnection() {
		if (connection == null) {
			try {
				new SingletonConnexion();
			} catch (SQLException exception) {
				System.out.println("Connection : " + exception.getMessage());
				return null;
			}
		}
		return connection;
	}
}
