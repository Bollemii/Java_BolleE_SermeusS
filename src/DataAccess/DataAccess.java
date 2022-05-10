package DataAccess;

import Exceptions.DataException;
import Model.*;

import java.util.ArrayList;

public interface DataAccess {
	void closeConnection() throws DataException;

	// CRUD
	int addMatch(Match match) throws DataException;
	ArrayList<Match> getAllMatchs() throws DataException;
	int updateMatch(Match match) throws DataException;
	int deleteMatch(int[] matchsID) throws DataException;

	// RESEARCHES
	ArrayList<MatchPlayerResearch> getMatchsTournament(int tournamentID) throws DataException;
	ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) throws DataException;
	ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException;

	// GET LISTS
	ArrayList<Tournament> getAllTournaments() throws DataException;
	ArrayList<Player> getAllPlayers() throws DataException;
	ArrayList<Referee> getAllReferees() throws DataException;
	ArrayList<Visitor> getAllVisitors() throws DataException;
	ArrayList<Location> getAllLocations() throws DataException;
	ArrayList<Result> getAllResults() throws DataException;

	// BUSINESS TASK
	int addResult(Result result) throws DataException;
	int updateResult(Result result) throws DataException;

	// OTHERS
	Match getMatch(int matchID) throws DataException;
	int addReservation(Reservation reservation) throws DataException;
	boolean isReservationExist(int visitorID, int matchID) throws DataException;
	int addPlayer(Player player) throws DataException;
	int addVisitor(Visitor visitor) throws DataException;
	int addReferee(Referee referee) throws DataException;
}
