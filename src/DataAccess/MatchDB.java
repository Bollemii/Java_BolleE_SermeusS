package DataAccess;

import Exceptions.DataException;
import Model.Location;
import Model.Match;
import Model.Referee;
import Model.Tournament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MatchDB implements MatchDataAccess {
	private Connection connection;

	public MatchDB() throws DataException {
		connection = SingletonConnexion.getInstance();
	}

	@Override
	public int addMatch(Match match) throws DataException {
		if (match == null)
			return 0;

		try {
			int nbLinesUpdated;
			String sqlInstruction = "insert into `match`(location_id, tournament_id, referee_id, date_start, is_final) values(?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, match.getLocation().getId());
			preparedStatement.setInt(2, match.getTournament().getId());
			preparedStatement.setInt(3, match.getReferee().getId());
			preparedStatement.setTimestamp(4, new java.sql.Timestamp(match.getDateStart().getTimeInMillis()));
			preparedStatement.setBoolean(5, match.isFinal());
			nbLinesUpdated = preparedStatement.executeUpdate();

			ResultSet data = preparedStatement.getGeneratedKeys();
			data.next();
			match.setId(data.getInt(1));

			optionalsColumnsMatch(match);
			return nbLinesUpdated;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public int updateMatch(Match match) throws DataException {
		if (match == null)
			return 0;

		try {
			int nbLinesUpdated;
			String sqlInstruction;
			sqlInstruction = "update `match` set date_start = ?, is_final = ?, tournament_id = ?, referee_id = ?, location_id = ? " +
					"where match_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setDate(1, new java.sql.Date(match.getDateStart().getTimeInMillis()));
			preparedStatement.setBoolean(2, match.isFinal());
			preparedStatement.setInt(3, match.getTournament().getId());
			preparedStatement.setInt(4, match.getReferee().getId());
			preparedStatement.setInt(5, match.getLocation().getId());
			preparedStatement.setInt(6, match.getId());
			nbLinesUpdated = preparedStatement.executeUpdate();

			optionalsColumnsMatch(match);
			return nbLinesUpdated;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	private void optionalsColumnsMatch(Match match) throws DataException {
		try {
			String sqlInstruction;
			PreparedStatement preparedStatement;
			if (match.getDuration() != null) {
				sqlInstruction = "update `match` set duration = ? where match_id = ?";
				preparedStatement = connection.prepareStatement(sqlInstruction);
				preparedStatement.setInt(1, match.getDuration());
				preparedStatement.setInt(2, match.getId());
				preparedStatement.executeUpdate();
			}
			if (match.getComment() != null) {
				sqlInstruction = "update `match` set comment = ? where match_id = ?";
				preparedStatement = connection.prepareStatement(sqlInstruction);
				preparedStatement.setString(1, match.getComment());
				preparedStatement.setInt(2, match.getId());
				preparedStatement.executeUpdate();
			}
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Match> getAllMatchs() throws DataException {
		try {
			String sqlInstruction =
					"select * " +
							"from `match` m " +
							"inner join person p on m.referee_id = p.person_id " +
							"inner join tournament t on m.tournament_id = t.tournament_id " +
							"inner join location l on m.location_id = l.location_id " +
							"order by m.match_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

			return getMatchs(preparedStatement.executeQuery());
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public Match getMatch(int matchID) throws DataException {
		try {
			String sqlInstruction = "select * " +
					"from `match` m " +
					"inner join tournament t on t.tournament_id = m.tournament_id " +
					"inner join person r on r.person_id = m.referee_id " +
					"inner join location l on l.location_id = m.location_id " +
					"where m.match_id = (?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, matchID);

			ResultSet data = preparedStatement.executeQuery();

			Match match = null;
			if (data.next()) {
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(data.getTimestamp("date_start"));
				match = new Match(
						data.getInt("match_id"),
						calendar,
						data.getBoolean("is_final"),
						new Tournament(data.getInt("tournament_id"), data.getString("t.name")),
						new Referee(data.getInt("referee_id"), data.getString("first_name"), data.getString("last_name")),
						new Location(data.getInt("location_id"), data.getString("l.name"), data.getInt("nb_rows"), data.getInt("nb_seats_per_row"))
				);

				int duration = data.getInt("duration");
				if (!data.wasNull())
					match.setDuration(duration);

				String comment = data.getString("comment");
				if (!data.wasNull())
					match.setComment(comment);
			}
			return match;
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	private ArrayList<Match> getMatchs(ResultSet data) throws SQLException {
		ArrayList<Match> list = new ArrayList<>();

		Match match;
		GregorianCalendar calendar;
		int duration;
		String comment;

		while (data.next()) {
			calendar = new GregorianCalendar();
			calendar.setTime(data.getTimestamp("date_start"));

			match = new Match(
					data.getInt("match_id"),
					calendar,
					data.getBoolean("is_final"),
					new Tournament(data.getInt("tournament_id"), data.getString("t.name")),
					new Referee(data.getInt("person_id"), data.getString("first_name"), data.getString("last_name")),
					new Location(data.getInt("location_id"), data.getString("l.name"), data.getInt("l.nb_rows"), data.getInt("l.nb_seats_per_row"))
			);

			duration = data.getInt("duration");
			if (!data.wasNull())
				match.setDuration(duration);

			comment = data.getString("comment");
			if (!data.wasNull())
				match.setComment(comment);

			list.add(match);
		}
		return list;
	}

	@Override
	public boolean isRefereeAvailable(int refereeID, GregorianCalendar date) throws DataException {
		try {
			String sqlInstruction = "select * from `match` where referee_id = ? and date_start between ? and ?";

			ResultSet data = isAvailableQuery(sqlInstruction, refereeID, date);
			return !data.next();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public boolean isLocationAvailable(int locationID, GregorianCalendar date) throws DataException {
		try {
			String sqlInstruction = "select * from `match` where location_id = ? and date_start between ? and ?";

			ResultSet data = isAvailableQuery(sqlInstruction, locationID, date);
			return !data.next();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	private ResultSet isAvailableQuery(String sqlInstruction, int id, GregorianCalendar date) throws SQLException {
		GregorianCalendar dateStart = new GregorianCalendar(
				date.get(Calendar.YEAR),
				date.get(Calendar.MONTH),
				date.get(Calendar.DAY_OF_MONTH),
				0,
				0
		);
		GregorianCalendar dateEnd = new GregorianCalendar(
				date.get(Calendar.YEAR),
				date.get(Calendar.MONTH),
				date.get(Calendar.DAY_OF_MONTH),
				23,
				59
		);
		PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
		preparedStatement.setInt(1, id);
		preparedStatement.setTimestamp(2, new java.sql.Timestamp(dateStart.getTimeInMillis()));
		preparedStatement.setTimestamp(3, new java.sql.Timestamp(dateEnd.getTimeInMillis()));
		return preparedStatement.executeQuery();
	}

	@Override
	public int deleteMatch(int[] matchsID) throws DataException {
		if (matchsID == null)
			return 0;

		try {
			boolean first = true;
			StringBuilder inClause = new StringBuilder("(");
			for (int i = 0; i < matchsID.length; i++) {
				if (first)
					first = false;
				else
					inClause.append(",");
				inClause.append("?");
			}
			inClause.append(")");
			String sqlInstruction = "delete from `match` where match_id in" + inClause;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

			// ne fonctionne pas !
			//preparedStatement.setArray(1, connection.createArrayOf("INTEGER", new int[][]{matchsID}));
			// internet : ajouter autant de '?' que de valeurs puis set ces valeurs
			for (int i = 0; i < matchsID.length; i++) {
				preparedStatement.setInt(i+1, matchsID[i]);
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}
}
