package DataAccess;

import Exceptions.DataException;
import Model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationDB implements LocationDataAccess {
	private Connection connection;

	public LocationDB() throws DataException {
		connection = SingletonConnexion.getInstance();
	}

	@Override
	public ArrayList<Location> getAllLocations() throws DataException {
		try {
			String sqlInstruction = "select * from location";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			ResultSet data = preparedStatement.executeQuery();

			ArrayList<Location> list = new ArrayList<>();
			while (data.next()) {
				list.add(new Location(
						data.getInt("location_id"),
						data.getString("name"),
						data.getInt("nb_rows"),
						data.getInt("nb_seats_per_row")
				));
			}
			return list;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}
}
