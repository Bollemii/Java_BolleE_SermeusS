package DataAccess;

import Model.*;

import java.util.ArrayList;

public interface DataAccess {
	// CRUD
	ArrayList<MatchResearch> getAllMatchs() throws DataException;

	// RESEARCHES
	ArrayList<MatchResearch> getMatchsPlayer(Player player) throws DataException;
}
