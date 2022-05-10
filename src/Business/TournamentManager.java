package Business;

import DataAccess.*;
import Exceptions.DataException;
import Exceptions.ValueException;
import Model.*;

import java.sql.Ref;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
	public ArrayList<Result> getResultList() throws DataException {
		return dataAccess.getAllResults();
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
	public int addMatch(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) throws DataException {
		Match match = new Match(dateStart, duration, isFinal, comment, new Tournament(tournamentID), new Referee(refereeID), new Location(locationID));
		return dataAccess.addMatch(match);
	}
	public int updateMatch(int matchID, GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) throws DataException {
		Match match = new Match(matchID, dateStart, duration, isFinal, comment, new Tournament(tournamentID), new Referee(refereeID), new Location(locationID));
		return dataAccess.updateMatch(match);
	}
	public int deleteMatch(List matchs) throws DataException {
		int[] matchsID = new int[matchs.size()];
		int i = 0;
		for (Object match : matchs) {
			matchsID[i] = ManagerUtils.getMatchIDFromDescription(match.toString());
			i++;
		}
		return dataAccess.deleteMatch(matchsID);
	}
	public int addReservation(int visitorID, int matchID, String seatType, char seatRow, int seatNumber, double cost) throws DataException, ValueException {
		String errorMessage = "";
		Match match = dataAccess.getMatch(matchID);
		if ((seatRow-'A') >= match.getLocation().getNbRows()) {
			errorMessage += "\n  - La rangée est supérieure à " + (char)(match.getLocation().getNbRows() + 'A' - 1);
		}
		if (seatNumber > match.getLocation().getNbSeatsPerRow()) {
			errorMessage += "\n  - Le numéro de siège est supérieur à " + match.getLocation().getNbSeatsPerRow();
		}

		if (errorMessage != "") {
			throw new ValueException(errorMessage);
		} else {
			if (dataAccess.isReservationExist(visitorID, matchID)) {
				throw new ValueException("\n  Ce visiteur a déjà réservé une place pour ce match");
			} else {
				Reservation reservation = new Reservation(new Visitor(visitorID), new Match(matchID), seatType, seatRow, seatNumber, cost);
				return dataAccess.addReservation(reservation);
			}
		}
	}
	public int addPerson(String type, String firstName, String lastName, Character gender, GregorianCalendar birthDate, Boolean isProfessional, Integer elo, Boolean isVIP, String level) throws DataException, ValueException {
		if (birthDate.getTime().compareTo(Date.from(Instant.now())) > 0)
			throw new ValueException("  La date d'anniversaire est incorrecte");

		switch(type) {
			case "Player" :
				Player player = new Player(firstName, lastName, birthDate, gender, isProfessional, elo);
				return dataAccess.addPlayer(player);
			case "Visitor" :
				Visitor visitor = new Visitor(firstName, lastName, birthDate, gender, isVIP);
				return dataAccess.addVisitor(visitor);
			case "Referee" :
				Referee referee = new Referee(firstName, lastName, birthDate, gender, level);
				return dataAccess.addReferee(referee);
			default : return -1;
		}
	}
}
