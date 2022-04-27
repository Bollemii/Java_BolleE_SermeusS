package Business;

import DataAccess.*;
import Exceptions.DataException;
import Model.*;

import java.util.ArrayList;

public class TournamentManager {
	private DataAccess dataAccess;

	public TournamentManager() throws DataException {
		dataAccess = new DBAccess();
	}

	public void closeConnection() throws DataException {
		dataAccess.closeConnection();
	}

	// lists for combobox from forms
	public ArrayList<Tournament> getTournamentsList() throws DataException {
		return dataAccess.getAllTournaments();
	}
	public ArrayList<Match> getMatchsList() throws DataException {
		return dataAccess.getAllMatchs();
	}
	public ArrayList<Player> getPlayersList() throws DataException {
		return dataAccess.getAllPlayers();
	}
	public ArrayList<Referee> getRefereesList() throws DataException {
		return dataAccess.getAllReferees();
	}
	public ArrayList<Location> getLocationsList() throws DataException {
		return dataAccess.getAllLocations();
	}
	public ArrayList<Visitor> getVisitorsList() throws DataException {
		return dataAccess.getAllVisitors();
	}

	// methods for data operations
	public ArrayList<Match> getAllMatchs() throws DataException {
		return dataAccess.getAllMatchs();
	}
	public ArrayList<Player> getAllPlayers() throws DataException {
		return dataAccess.getAllPlayers();
	}
	public ArrayList<MatchPlayerResearch> getMatchsTournament(int tournamentID) throws DataException {
		return dataAccess.getMatchsTournament(tournamentID);
	}
	public ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) throws DataException {
		return dataAccess.getMatchsPlayer(playerID);
	}
	public ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException {
		return dataAccess.getReservationsVisitor(visitorID);
	}
	public Match getMatch(int matchID) throws DataException {
		return dataAccess.getMatch(matchID);
	}
	public int addMatch(Match match) throws DataException {
		return dataAccess.addMatch(match);
	}
	public int updateMatch(Match match) throws DataException {
		return dataAccess.updateMatch(match);
	}
	public int deleteMatch(int[] matchsID) throws DataException {
		return dataAccess.deleteMatch(matchsID);
	}
}
