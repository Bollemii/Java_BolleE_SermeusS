package DataAccess;

import Exceptions.DataException;
import Model.*;

import java.sql.*;
import java.util.*;

public class DBAccess implements DataAccess {
	// CONNECTION
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
	public int addResult(Result result) throws DataException {
		if (result == null)
			return 0;

		try {
			String sqlInstruction = "insert into result(player_id, match_id) values(?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, result.getPlayer().getId());
			preparedStatement.setInt(2, result.getMatch().getId());
			return preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
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


	// GET
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
	public ArrayList<MatchPlayerResearch> getMatchsTournament(int tournamentID) throws DataException {
		try {
			String sqlInstruction = "select m.date_start, p.*, r.points " +
									"from tournament t " +
									"inner join `match` m on m.tournament_id = t.tournament_id " +
									"inner join result r on r.match_id = m.match_id " +
									"inner join person p on p.person_id = r.player_id " +
									"where t.tournament_id = (?) " +
									"order by date_start, points desc";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, tournamentID);

			ResultSet data = preparedStatement.executeQuery();

			GregorianCalendar calendar;
			ArrayList<MatchPlayerResearch> list = new ArrayList<>();
			while (data.next()) {
				calendar = new GregorianCalendar();
				calendar.setTime(data.getDate("date_start"));
				list.add(new MatchPlayerResearch(
						new Match(calendar),
						new Player(data.getString("first_name"), data.getString("last_name"), data.getDouble("elo")),
						data.getInt("points")
				));
			}
			return list;
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) throws DataException {
		try {
			String sqlInstruction = "select t.*, m.date_start, l.*, j.*, r.points " +
									"from person p " +
									"inner join result r on r.player_id = p.person_id " +
									"inner join `match` m on m.match_id = r.match_id " +
									"inner join person j on m.referee_id = j.person_id " +
									"inner join tournament t on m.tournament_id = t.tournament_id " +
									"inner join location l on m.location_id = l.location_id " +
									"where p.person_id = (?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, playerID);

			ResultSet data = preparedStatement.executeQuery();

			GregorianCalendar calendar;
			ArrayList<MatchPlayerResearch> list = new ArrayList<>();
			while (data.next()) {
				calendar = new GregorianCalendar();
				calendar.setTime(data.getDate("date_start"));
				list.add(
					new MatchPlayerResearch(
						new Match(
							calendar,
							new Tournament(data.getInt("tournament_id"), data.getString("t.name")),
							new Referee(data.getInt("person_id"), data.getString("first_name"), data.getString("last_name")),
							new Location(data.getInt("location_id"), data.getString("l.name"), data.getInt("l.nb_rows"), data.getInt("l.nb_seats_per_row"))
						),
						data.getInt("points")
					)
				);
			}
			return list;
		} catch(SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException {
		try {
			String sqlInstruction = "select t.*, m.date_start, r.*, l.* " +
									"from person v " +
									"inner join reservation r on r.visitor_id = v.person_id " +
									"inner join `match` m on m.match_id = r.match_id " +
									"inner join tournament t on t.tournament_id = m.tournament_id " +
									"inner join location l on l.location_id = m.location_id " +
									"where v.person_id = (?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, visitorID);

			ResultSet data = preparedStatement.executeQuery();

			ArrayList<Reservation> list = new ArrayList<>();
			while (data.next()) {
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(data.getDate("date_start"));
				list.add(
					new Reservation(
						new Match(
							data.getInt("match_id"),
							calendar,
							new Tournament(
								data.getInt("tournament_id"),
								data.getString("t.name")
							),
							new Location(
								data.getInt("location_id"),
								data.getString("l.name"),
								data.getInt("nb_rows"),
								data.getInt("nb_seats_per_row")
							)
						),
						data.getString("seat_type"),
						data.getString("seat_row").charAt(0),
						data.getInt("seat_number"),
						data.getDouble("cost")
					)
				);
			}
			return list;
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

			ResultSet data = preparedStatement.executeQuery();

			GregorianCalendar calendar;
			ArrayList<Player> list = new ArrayList<>();
			while (data.next()) {
				calendar = new GregorianCalendar();
				calendar.setTime(data.getDate("birth_date"));
				list.add(new Player(
						data.getInt("person_id"),
						data.getString("first_name"),
						data.getString("last_name"),
						calendar,
						data.getString("gender").charAt(0),
						data.getBoolean("is_professional"),
						data.getDouble("elo")
				));
			}

			return list;
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


	// UPDATE
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
	public int updateResult(Result result) throws DataException {
		try {
			int nbLinesUpdated = 0;
			if (result.getPoints() != null) {
				String sqlInstruction = "update result set points = ? where player_id = ? and match_id = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
				preparedStatement.setInt(1, result.getPoints());
				preparedStatement.setInt(2, result.getPlayer().getId());
				preparedStatement.setInt(3, result.getMatch().getId());
				nbLinesUpdated = preparedStatement.executeUpdate();
			}
			return nbLinesUpdated;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}


	// DELETE
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