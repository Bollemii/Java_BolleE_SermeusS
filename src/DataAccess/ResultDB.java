package DataAccess;

import Exceptions.DataException;
import Exceptions.ValueException;
import Model.Match;
import Model.Player;
import Model.Result;

import java.sql.*;
import java.util.ArrayList;

public class ResultDB implements ResultDataAccess {
	private Connection connection;

	public ResultDB() throws DataException {
		connection = SingletonConnexion.getInstance();
	}

	@Override
	public int addResult(Result result) throws DataException {
		if (result == null)
			return 0;

		try {
			String sqlInstruction = "insert into result(player_id, match_id, points) values(?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			preparedStatement.setInt(1, result.getPlayer().getId());
			preparedStatement.setInt(2, result.getMatch().getId());
			preparedStatement.setInt(3, result.getPoints());
			return preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}

	@Override
	public ArrayList<Result> getAllResults() throws DataException, ValueException {
		try {
			String sqlInstruction = "select * from result";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
			ResultSet data = preparedStatement.executeQuery();

			ArrayList<Result> list = new ArrayList<>();
			while (data.next()) {
				list.add(new Result(
						new Player(data.getInt("player_id")),
						new Match(data.getInt("match_id")),
						data.getInt("points")
				));
			}
			return list;
		} catch (SQLException exception) {
			throw new DataException(exception.getMessage());
		}
	}
}
