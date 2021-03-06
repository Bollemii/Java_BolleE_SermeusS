package Controller;

import Business.TournamentManager;
import Exceptions.DataException;
import Exceptions.ValueException;
import Model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

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
	public ArrayList<Player> getPlayersList() throws DataException, ValueException {
		return manager.getPlayersList();
	}
	public ArrayList<Referee> getRefereesList() throws DataException, ValueException {
		return manager.getRefereesList();
	}
	public ArrayList<Visitor> getVisitorsList() throws DataException, ValueException {
		return manager.getVisitorsList();
	}
	public ArrayList<Location> getLocationsList() throws DataException, ValueException {
		return manager.getLocationsList();
	}
	public ArrayList<Match> getMatchsWithoutResultList() throws DataException, ValueException {
		ArrayList<Integer> resultMatchID = (ArrayList<Integer>) manager.getResultList().stream().map(r -> r.getMatch().getId()).collect(Collectors.toList());
		ArrayList<Match> matchs = manager.getAllMatchs();
		matchs.removeIf(match -> resultMatchID.contains(match.getId()));
		return matchs;
	}

	// methods for data operations
	public ArrayList<Match> getAllMatchs() throws DataException, ValueException {
		return manager.getAllMatchs();
	}
	public ArrayList<Player> getAllPlayers() throws DataException, ValueException {
		return manager.getAllPlayers();
	}
	public ArrayList<MatchPlayerResearch> getMatchsTournament(int tournamentID) throws DataException, ValueException {
		return manager.getMatchsTournament(tournamentID);
	}
	public ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) throws DataException, ValueException {
		return manager.getMatchsPlayer(playerID);
	}
	public ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException, ValueException {
		return manager.getReservationsVisitor(visitorID);
	}
	public ArrayList<Person> getPersonsBirthdays(GregorianCalendar date1, GregorianCalendar date2) throws DataException, ValueException {
		return manager.getPersonsBirthdays(date1, date2);
	}
	public Match getMatch(int matchID) throws DataException, ValueException {
		return manager.getMatch(matchID);
	}
	public Player getPlayer(int playerID) throws DataException, ValueException {
		return manager.getPlayer(playerID);
	}
	public int addMatch(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) throws DataException, ValueException {
		return manager.addMatch(dateStart, duration, isFinal, comment, tournamentID, refereeID, locationID);
	}
	public int updateMatch(int matchID, GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) throws DataException, ValueException {
		return manager.updateMatch(matchID, dateStart, duration, isFinal, comment, tournamentID, refereeID, locationID);
	}
	public int deleteMatch(ArrayList<Match> matchs) throws DataException {
		return manager.deleteMatch(matchs);
	}
	public int addReservation(int visitorID, int matchID, String seatType, char seatRow, int seatNumber, double cost) throws DataException, ValueException {
		return manager.addReservation(visitorID, matchID, seatType, seatRow, seatNumber, cost);
	}
	public int addPerson(String type, String firstName, String lastName, Character gender, GregorianCalendar birthDate, Boolean isProfessional, Integer elo, Boolean isVIP, String level) throws DataException, ValueException {
		return manager.addPerson(type, firstName, lastName, gender, birthDate, isProfessional, elo, isVIP, level);
	}
	public int addResults(Result player1Result, Result player2Result) throws DataException, ValueException {
		return manager.addResult(player1Result, player2Result);
	}
}
