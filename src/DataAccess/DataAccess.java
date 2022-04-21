package DataAccess;

import Model.*;

import java.util.ArrayList;

public interface DataAccess {
	void closeConnection() throws DataException;

	// CRUD
	int addMatch(Match match) throws DataException;
	ArrayList<Match> getAllMatchs() throws DataException;
	int updateMatch(Match match) throws DataException;
	int deleteMatch(String matchsID) throws DataException;

	// RESEARCHES
	ArrayList<Player> getPlayersTournament(int tournamentID) throws DataException;
	ArrayList<Match> getMatchsPlayer(int playerID) throws DataException;
	ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException;

	// GET LISTS
	ArrayList<Tournament> getAllTournaments() throws DataException;
	ArrayList<Player> getAllPlayers() throws DataException;
	ArrayList<Referee> getAllReferees() throws DataException;
	ArrayList<Visitor> getAllVisitors() throws DataException;
	ArrayList<Location> getAllLocations() throws DataException;
}
