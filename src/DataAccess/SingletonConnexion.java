package DataAccess;

import java.sql.*;

public class SingletonConnexion {
	private static Connection connection;

	private SingletonConnexion() throws SQLException {
		connection = DriverManager.getConnection(
				"jdbc:mysql://DESKTOP-G9LT55S:3306/library",
				"bolle",
				"1111"
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
