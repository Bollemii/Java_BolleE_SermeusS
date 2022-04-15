package DataAccess;

import Model.*;

import java.sql.Connection;
import java.util.ArrayList;

public interface DataAccess {
	void closeConnection() throws DataException;

	// CRUD
	void addMatch(Match match) throws DataException;
	ArrayList<Match> getAllMatchs() throws DataException;
	int deleteMatch(int... matchID) throws DataException;

	// RESEARCHES
	ArrayList<Match> getMatchsPlayer(Player player) throws DataException;
}
