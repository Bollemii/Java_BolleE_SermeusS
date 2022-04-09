package DataAccess;

import Model.*;

import java.sql.Connection;
import java.util.ArrayList;

public interface DataAccess {
	void closeConnection() throws DataException;

	// CRUD
	ArrayList<MatchResearch> getAllMatchs() throws DataException;

	// RESEARCHES
	ArrayList<MatchResearch> getMatchsPlayer(Player player) throws DataException;
}
