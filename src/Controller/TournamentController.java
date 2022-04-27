package Controller;

import Business.ManagerUtils;
import Business.TournamentManager;
import Exceptions.DataException;
import Model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class TournamentController {
	private TournamentManager manager;

	public TournamentController() throws DataException {
		manager = new TournamentManager();
	}

	public void closeConnection() throws DataException {
		manager.closeConnection();
	}

	// lists for combobox from forms
	public ArrayList<Tournament> getTournamentsList() throws DataException {
		return manager.getTournamentsList();
	}
	public ArrayList<Match> getMatchsList() throws DataException {
		return manager.getMatchsList();
	}
	public ArrayList<Player> getPlayersList() throws DataException {
		return manager.getPlayersList();
	}
	public ArrayList<Referee> getRefereesList() throws DataException {
		return manager.getRefereesList();
	}
	public ArrayList<Location> getLocationsList() throws DataException {
		return manager.getLocationsList();
	}
	public ArrayList<Visitor> getVisitorsList() throws DataException {
		return manager.getVisitorsList();
	}

	// methods for data operations
	public ArrayList<Match> getAllMatchs() throws DataException {
		return manager.getAllMatchs();
	}
	public ArrayList<Player> getAllPlayers() throws DataException {
		return manager.getAllPlayers();
	}
	public ArrayList<MatchPlayerResearch> getMatchsTournament(int tournamentID) throws DataException {
		return manager.getMatchsTournament(tournamentID);
	}
	public ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) throws DataException {
		return manager.getMatchsPlayer(playerID);
	}
	public ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException {
		return manager.getReservationsVisitor(visitorID);
	}
	public Match getMatch(int matchID) throws DataException {
		return manager.getMatch(matchID);
	}
	public int addMatch(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) throws DataException {
		Match match = new Match(dateStart, duration, isFinal, comment, new Tournament(tournamentID), new Referee(refereeID), new Location(locationID));
		return manager.addMatch(match);
	}
	public int updateMatch(int matchID, GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) throws DataException {
		Match match = new Match(matchID, dateStart, duration, isFinal, comment, new Tournament(tournamentID), new Referee(refereeID), new Location(locationID));
		return manager.updateMatch(match);
	}
	public int deleteMatch(List matchs) throws DataException {
		int[] matchsID = new int[matchs.size()];
		int i = 0;
		for (Object match : matchs) {
			matchsID[i] = ManagerUtils.getMatchIDFromDescription(match.toString());
			i++;
		}
		return manager.deleteMatch(matchsID);
	}
}
