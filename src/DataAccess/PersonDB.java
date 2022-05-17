package DataAccess;

import Exceptions.DataException;
import Exceptions.ValueException;
import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PersonDB implements PersonDataAccess {
	private Connection connection;

	public PersonDB() throws DataException {
		connection = SingletonConnexion.getInstance();
	}

	@Override
	public int addPlayer(Player player) throws DataException {
		if (player == null) {
			return 0;
		}

		try {
			String sqlInstruction =
				"insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo) " +
				"values(?,?,?,?,'Player',?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement = setPersonValues(preparedStatement, player);
			preparedStatement.setBoolean(5, player.isProfessional());
			preparedStatement.setInt(6, player.getElo());

			return preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public int addVisitor(Visitor visitor) throws DataException {
		if (visitor == null) {
			return 0;
		}

		try {
			String sqlInstruction =
					"insert into person(first_name, last_name, birth_date, gender, type_person, is_vip) " +
							"values(?,?,?,?,'Visitor',?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement = setPersonValues(preparedStatement, visitor);
			preparedStatement.setBoolean(5, visitor.isVIP());

			return preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public int addReferee(Referee referee) throws DataException {
		if (referee == null) {
			return 0;
		}

		try {
			String sqlInstruction =
					"insert into person(first_name, last_name, birth_date, gender, type_person, level) " +
							"values(?,?,?,?,'Referee',?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement = setPersonValues(preparedStatement, referee);
			preparedStatement.setString(5, referee.getLevel());

			return preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	private PreparedStatement setPersonValues(PreparedStatement preparedStatement, Person person) throws SQLException {
		preparedStatement.setString(1, person.getFirstName());
		preparedStatement.setString(2, person.getLastName());
		preparedStatement.setDate(3, new java.sql.Date(person.getBirthDate().getTimeInMillis()));
		preparedStatement.setString(4, person.getGender().toString());

		return preparedStatement;
	}

	@Override
	public int updateEloPlayer(int playerID, int elo) throws DataException {
		try {
			String sqlInstruction = "update person set elo = ? where person_id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, elo);
			preparedStatement.setInt(2, playerID);

			return preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Player> getAllPlayers() throws DataException, ValueException {
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
						data.getInt("elo")
				));
			}

			return list;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Referee> getAllReferees() throws DataException, ValueException {
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
	public ArrayList<Visitor> getAllVisitors() throws DataException, ValueException {
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
	public ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) throws DataException, ValueException {
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
				calendar.setTime(data.getTimestamp("date_start"));
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
	public ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException, ValueException {
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
				calendar.setTime(data.getTimestamp("date_start"));
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
	public ArrayList<Person> getByBirthday(GregorianCalendar date1, GregorianCalendar date2) throws DataException, ValueException {
		try {
			String sqlInstruction =
				"select person_id, first_name, last_name, birth_date, gender, type_person " +
				"from person " +
				"where birth_date between ? and ? " +
				"order by birth_date";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setDate(1, new java.sql.Date(date1.getTimeInMillis()));
			preparedStatement.setDate(2, new java.sql.Date(date2.getTimeInMillis()));
			ResultSet data = preparedStatement.executeQuery();

			ArrayList<Person> list = new ArrayList<>();
			while(data.next()) {
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(data.getDate("birth_date"));
				list.add(new Person(
					data.getInt("person_id"),
					data.getString("first_name"),
					data.getString("last_name"),
					calendar,
					data.getString("gender").charAt(0),
					data.getString("type_person")
				));
			}
			return list;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public Player getPlayerById(int playerID) throws DataException, ValueException {
		try {
			String sqlInstruction =
				"select * from person where person_id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, playerID);
			ResultSet data = preparedStatement.executeQuery();

			data.next();

			return new Player(
				data.getInt("person_id"),
				data.getString("first_name"),
				data.getString("last_name"),
				data.getInt("elo")
			);
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}
}
