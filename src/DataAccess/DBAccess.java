package DataAccess;

import Model.*;

import java.sql.*;
import java.util.*;

public class DBAccess implements DataAccess {
	// CONNECTION
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


	// ADD
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
			preparedStatement.setDate(4, new java.sql.Date(match.getDateStart().getTimeInMillis()));
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


	// GET
	@Override
	public ArrayList<Match> getAllMatchs() throws DataException {
		try {
			String sqlInstruction =
					"select m.*, p.first_name, p.last_name, t.name as 'tournament', l.name as 'location'" +
					"from `match` m " +
					"inner join person p on m.referee_id = p.person_id " +
					"inner join tournament t on m.tournament_id = t.tournament_id " +
					"inner join location l on m.location_id = l.location_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

			return getMatchs(preparedStatement.executeQuery());
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Player> getPlayersTournament(int tournamentID) throws DataException {
		try {
			String sqlInstruction =
					"select p.*" +
							"from tournament t " +
							"inner join `match` m on m.tournament_id = t.tournament_id " +
							"inner join result r on r.match_id = m.match_id " +
							"inner join person p on p.person_id = r.player_id " +
							"where t.tournament_id = (?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, tournamentID);

			return getPlayers(preparedStatement.executeQuery());
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Match> getMatchsPlayer(int playerID) throws DataException {
		try {
			String sqlInstruction =
					"select m.*, l.name as 'location', t.name as 'tournament', j.first_name, j.last_name, r.points " +
					"from person p " +
					"inner join result r on r.player_id = p.person_id " +
					"inner join `match` m on m.match_id = r.match_id " +
					"inner join person j on m.referee_id = j.person_id " +
					"inner join tournament t on m.tournament_id = t.tournament_id " +
					"inner join location l on m.location_id = l.location_id " +
					"where p.person_id = (?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, playerID);

			return getMatchs(preparedStatement.executeQuery());
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException {
		try {
			String sqlInstruction =
					"select r.*, m.date_start " +
							"from person v " +
							"inner join reservation r on r.visitor_id = v.person_id " +
							"inner join `match` m on m.match_id = r.match_id " +
							"where v.person_id = (?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, visitorID);

			ResultSet data = preparedStatement.executeQuery();

			ArrayList<Reservation> list = new ArrayList<>();
			while (data.next()) {
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(data.getDate("date_start"));
				list.add(new Reservation(
						new Match(data.getInt("match_id"), calendar),
						data.getString("seat_type"),
						data.getString("seat_row").charAt(0),
						data.getInt("seat_number"),
						data.getDouble("cost")
				));
			}
			return list;
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Tournament> getAllTournaments() throws DataException {
		try {
			String sqlInstruction = "select * from tournament";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			ResultSet data = preparedStatement.executeQuery();

			ArrayList<Tournament> list = new ArrayList<>();
			while (data.next()) {
				list.add(new Tournament(
						data.getInt("tournament_id"),
						data.getString("name")
				));
			}
			return list;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Player> getAllPlayers() throws DataException {
		try {
			String sqlInstruction = "select * from person where type_person = 'Player'";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

			return getPlayers(preparedStatement.executeQuery());
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Referee> getAllReferees() throws DataException {
		try {
			String sqlInstruction = "select * from person where type_person = 'Referee'";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			ResultSet data = preparedStatement.executeQuery();

			ArrayList<Referee> list = new ArrayList<>();
			while (data.next()) {
				list.add(new Referee(
						data.getInt("person_id"),
						data.getString("first_name"),
						data.getString("last_name")
				));
			}
			return list;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Visitor> getAllVisitors() throws DataException {
		try {
			String sqlInstruction = "select * from person where type_person = 'Visitor'";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			ResultSet data = preparedStatement.executeQuery();

			ArrayList<Visitor> list = new ArrayList<>();
			while (data.next()) {
				list.add(new Visitor(
						data.getInt("person_id"),
						data.getString("first_name"),
						data.getString("last_name")
				));
			}
			return list;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
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

	private ArrayList<Match> getMatchs(ResultSet data) throws SQLException {
		ArrayList<Match> list = new ArrayList<>();

		Match match;
		GregorianCalendar calendar;
		int duration;
		String comment;

		while (data.next()) {
			calendar = new GregorianCalendar();
			calendar.setTime(data.getDate("date_start"));
			match = new Match(
					data.getInt("match_id"),
					calendar,
					data.getBoolean("is_final"),
					new Tournament(data.getString("tournament")),
					new Referee(data.getString("first_name"), data.getString("last_name")),
					new Location(data.getString("location"))
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

	private ArrayList<Player> getPlayers(ResultSet data) throws SQLException {
		ArrayList<Player> list = new ArrayList<>();
		while (data.next()) {
			list.add(new Player(
					data.getInt("person_id"),
					data.getString("first_name"),
					data.getString("last_name")
			));
		}
		return list;
	}

	// UPDATE
	@Override
	public int updateMatch(Match match) throws DataException {
		if (match == null)
			return 0;

		try {
			int nbLinesUpdated;
			String sqlInstruction;
			sqlInstruction = "update `match` set location_id = ?, tournament_id = ?, referee_id = ?, date_start = ?, is_final = ? " +
							 "where match_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, match.getLocation().getId());
			preparedStatement.setInt(2, match.getTournament().getId());
			preparedStatement.setInt(3, match.getReferee().getId());
			preparedStatement.setDate(4, new java.sql.Date(match.getDateStart().getTimeInMillis()));
			preparedStatement.setBoolean(5, match.isFinal());
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


	// DELETE
	@Override
	public int deleteMatch(int... matchID) throws DataException {
		if (matchID == null)
			return 0;

		try {
			String sqlInstruction = "delete from `match` where match_id in(?)";
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