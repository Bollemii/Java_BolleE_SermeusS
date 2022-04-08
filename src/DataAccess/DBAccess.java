package DataAccess;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DBAccess implements DataAccess {
	private Connection connection;

	public DBAccess() throws DataException {
		connection = SingletonConnexion.getConnection();
	}

	@Override
	public ArrayList<MatchResearch> getAllMatchs() throws DataException {
		try {
			ArrayList<MatchResearch> list = new ArrayList<>();

			String sqlInstruction =
					"select m.*, p.firstName as 'judge', t.name as 'tournament', l.name as 'location'" +
					"from `match` m " +
					"inner join person p on m.judgeID = p.personID " +
					"inner join tournament t on m.tournamentID = t.tournamentID " +
					"inner join location l on m.locationID = l.locationID";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

			ResultSet data = preparedStatement.executeQuery();

			MatchResearch match;
			GregorianCalendar calendar;
			int duration;

			while (data.next()) {
				calendar = new GregorianCalendar();
				calendar.setTime(data.getDate("dateStart"));
				match = new MatchResearch(
						calendar,
						data.getBoolean("isFinal"),
						data.getString("comment"),
						data.getString("tournament"),
						data.getString("judge"),
						data.getString("location")
				);

				duration = data.getInt("duration");
				if (!data.wasNull())
					match.setDuration(duration);

				list.add(match);
			}
			return list;
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<MatchResearch> getMatchsPlayer(Player player) throws DataException {
		try {
			ArrayList<MatchResearch> list = new ArrayList<>();

			String sqlInstruction =
					"select m.*" +
					"from person p " +
					"inner join result r on r.playerID = p.personID " +
					"inner join `match` m on m.matchID = r.matchID " +
					"where p.firstName = (?) and p.lastName = (?) and p.personID = (?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setString(1, player.getFirstName());
			preparedStatement.setString(2, player.getLastName());
			preparedStatement.setInt(3, player.getId());

			MatchResearch match;
			GregorianCalendar calendar;
			int duration;
			ResultSet data = preparedStatement.executeQuery();

			while (data.next()) {
				calendar = new GregorianCalendar();
				calendar.setTime(data.getDate("dateStart"));
				match = new MatchResearch(
						calendar,
						data.getBoolean("isFinal"),
						data.getString("comment"),
						data.getString("tournament"),
						data.getString("judge"),
						data.getString("location")
				);

				duration = data.getInt("duration");
				if (!data.wasNull())
					match.setDuration(duration);

				list.add(match);
			}
			return list;
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}
}
