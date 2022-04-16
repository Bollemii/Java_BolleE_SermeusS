package DataAccess;

import Model.*;

import java.util.ArrayList;

public interface DataAccess {
	void closeConnection() throws DataException;

	// CRUD
	int addMatch(Match match) throws DataException;
	ArrayList<Match> getAllMatchs() throws DataException;
	int updateMatch(Match match) throws DataException;
	int deleteMatch(int... matchID) throws DataException;

	// RESEARCHES
	ArrayList<Match> getMatchsPlayer(Player player) throws DataException;
	ArrayList<Tournament> getAllTournaments() throws DataException;
	ArrayList<Referee> getAllReferees() throws DataException;
	ArrayList<Location> getAllLocations() throws DataException;
}
