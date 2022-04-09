package DataAccess;

import Model.*;

import java.sql.Connection;
import java.util.ArrayList;

public interface DataAccess {
	void closeConnection() throws DataException;

	// CRUD
	int addMatch(MatchResearch match) throws DataException;
	ArrayList<MatchResearch> getAllMatchs() throws DataException;
	int deleteMatch(int... matchID) throws DataException;

	// RESEARCHES
	ArrayList<MatchResearch> getMatchsPlayer(Player player) throws DataException;
}
