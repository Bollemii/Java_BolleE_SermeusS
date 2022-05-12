package DataAccess;

import Exceptions.DataException;

import java.sql.*;

public class DBAccess implements DataAccess {
	private Connection connection;

	public DBAccess() throws DataException {
		connection = SingletonConnexion.getInstance();
	}

	public void closeConnection() throws DataException {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}
}