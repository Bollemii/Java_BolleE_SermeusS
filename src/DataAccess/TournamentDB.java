package DataAccess;

import Exceptions.DataException;
import Model.Match;
import Model.MatchPlayerResearch;
import Model.Player;
import Model.Tournament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TournamentDB implements TournamentDataAccess {
	private Connection connection;

	public TournamentDB() throws DataException {
		connection = SingletonConnexion.getInstance();
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
				calendar.setTime(data.getTimestamp("date_start"));
				list.add(new MatchPlayerResearch(
					new Match(calendar),
					new Player(
						data.getInt("person_id"),
						data.getString("first_name"),
						data.getString("last_name"),
						data.getInt("elo")
					),
					data.getInt("points")
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
}
