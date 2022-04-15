package DataAccess;

import Model.*;

import java.sql.*;
import java.util.*;

public class DBAccess implements DataAccess {
	private Connection connection;

	public DBAccess() throws DataException {
		connection = SingletonConnexion.getConnection();
	}

	public void closeConnection() throws DataException {
		try {
			connection.close();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public int addMatch(Match match) throws DataException {
		if (match == null)
			return 0;

		try {
			int nbLinesUpdated;
			String sqlInstruction;
			sqlInstruction = "insert into `match`(matchID, locationID, tournamentID, judgeID, dateStart, isFinal) " +
							 "values(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, match.getId());
			preparedStatement.setInt(2, match.getLocation().getId());
			preparedStatement.setInt(3, match.getTournament().getId());
			preparedStatement.setInt(4, match.getReferee().getId());
			preparedStatement.setDate(5, new java.sql.Date(match.getDateStart().getTimeInMillis()));
			nbLinesUpdated = preparedStatement.executeUpdate();

			optionalsColumnsMatch(match, preparedStatement);
			return nbLinesUpdated;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Match> getAllMatchs() throws DataException {
		try {
			String sqlInstruction =
					"select m.*, p.firstName as 'judge', t.name as 'tournament', l.name as 'location'" +
					"from `match` m " +
					"inner join person p on m.judgeID = p.personID " +
					"inner join tournament t on m.tournamentID = t.tournamentID " +
					"inner join location l on m.locationID = l.locationID";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

			ResultSet data = preparedStatement.executeQuery();

			return getMatchs(data);
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Match> getMatchsPlayer(Player player) throws DataException {
		if (player == null)
			return new ArrayList<>();

		try {
			String sqlInstruction =
					"select m.*, l.name as 'location', t.name as 'tournament', j.firstName as 'judge', r.points " +
					"from person p " +
					"inner join result r on r.playerID = p.personID " +
					"inner join `match` m on m.matchID = r.matchID " +
					"inner join person j on m.judgeID = j.personID " +
					"inner join tournament t on m.tournamentID = t.tournamentID " +
					"inner join location l on m.locationID = l.locationID " +
					"where p.firstName = (?) and p.lastName = (?) and p.personID = (?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setString(1, player.getFirstName());
			preparedStatement.setString(2, player.getLastName());
			preparedStatement.setInt(3, player.getId());

			ResultSet data = preparedStatement.executeQuery();

			return getMatchs(data);
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	private ArrayList<Match> getMatchs(ResultSet data) throws SQLException {
		ArrayList<Match> list = new ArrayList<>();

		Match match;
		GregorianCalendar calendar;
		int duration;

		while (data.next()) {
			calendar = new GregorianCalendar();
			calendar.setTime(data.getDate("dateStart"));
			match = new Match(
					data.getInt("matchID"),
					calendar,
					data.getBoolean("isFinal"),
					data.getString("comment"),
					new Tournament(data.getString("tournament")),
					new Referee(data.getString("judge")),
					new Location(data.getString("location"))
			);

			duration = data.getInt("duration");
			if (!data.wasNull())
				match.setDuration(duration);

			list.add(match);
		}
		return list;
	}

	public int updateMatch(Match match) throws DataException {
		if (match == null)
			return 0;

		try {
			int nbLinesUpdated;
			String sqlInstruction;
			sqlInstruction = "update `match` set locationID = ?, tournamentID = ?, judgeID = ?, dateStart = ?, isFinal = ?) " +
							 "where matchID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, match.getLocation().getId());
			preparedStatement.setInt(2, match.getTournament().getId());
			preparedStatement.setInt(3, match.getReferee().getId());
			preparedStatement.setDate(4, new java.sql.Date(match.getDateStart().getTimeInMillis()));
			preparedStatement.setBoolean(5, match.isFinal());
			preparedStatement.setInt(6, match.getId());
			nbLinesUpdated = preparedStatement.executeUpdate();

			optionalsColumnsMatch(match, preparedStatement);
			return nbLinesUpdated;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	private void optionalsColumnsMatch (Match match, PreparedStatement preparedStatement) throws DataException {
		try {
			String sqlInstruction;
			if (match.getDuration() != null) {
				sqlInstruction = "update `match` set duration = ? where matchID = ?";
				preparedStatement = connection.prepareStatement(sqlInstruction);
				preparedStatement.setInt(1, match.getDuration());
				preparedStatement.setInt(2, match.getId());
				preparedStatement.executeUpdate();
			}
			if (match.getComment() != null) {
				sqlInstruction = "update `match` set comment = ? where matchID = ?";
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
	public int deleteMatch(int... matchID) throws DataException {
		if (matchID == null)
			return 0;

		try {
			String sqlInstruction = "delete from `match` where matchID in(?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

			StringBuilder matchList = new StringBuilder();
			boolean isFirst = true;
			for (int id : matchID) {
				if (!isFirst) {
					matchList.append(",");
				} else {
					isFirst = false;
				}
				matchList.append(id);
			}

			preparedStatement.setString(1, matchList.toString());
			return preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}
}