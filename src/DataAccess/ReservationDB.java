package DataAccess;

import Exceptions.DataException;
import Model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDB implements ReservationDataAccess {
	private Connection connection;

	public ReservationDB() throws DataException {
		connection = SingletonConnexion.getInstance();
	}

	@Override
	public int addReservation(Reservation reservation) throws DataException {
		if (reservation == null)
			return 0;

		try {
			String sqlInstruction = "insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost) values(?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, reservation.getVisitor().getId());
			preparedStatement.setInt(2, reservation.getMatch().getId());
			preparedStatement.setString(3, reservation.getSeatType());
			preparedStatement.setString(4, reservation.getSeatRow().toString());
			preparedStatement.setInt(5, reservation.getSeatNumber());
			preparedStatement.setDouble(6, reservation.getCost());

			return preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public boolean isReservationExist(int visitorID, int matchID) throws DataException {
		try {
			String sqlInstruction = "select * from reservation where visitor_id = ? and match_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, visitorID);
			preparedStatement.setInt(2, matchID);

			ResultSet data = preparedStatement.executeQuery();
			return data.next();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}
}
