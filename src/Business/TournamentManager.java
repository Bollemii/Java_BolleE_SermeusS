package Business;

import DataAccess.*;
import Exceptions.DataException;
import Exceptions.ValueException;
import Model.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class TournamentManager {
	private DataAccess dataAccess;
	private LocationDataAccess locationDataAccess;
	private MatchDataAccess matchDataAccess;
	private PersonDataAccess personDataAccess;
	private ReservationDataAccess reservationDataAccess;
	private	ResultDataAccess resultDataAccess;
	private TournamentDataAccess tournamentDataAccess;

	public TournamentManager() throws DataException {
		dataAccess = new DBAccess();

		locationDataAccess = new LocationDB();
		matchDataAccess = new MatchDB();
		personDataAccess = new PersonDB();
		reservationDataAccess = new ReservationDB();
		resultDataAccess = new ResultDB();
		tournamentDataAccess = new TournamentDB();
	}

	public void closeConnection() throws DataException {
		dataAccess.closeConnection();
	}

	// lists for combobox from forms
	public ArrayList<Tournament> getTournamentsList() throws DataException {
		return tournamentDataAccess.getAllTournaments();
	}
	public ArrayList<Match> getMatchsList() throws DataException {
		return matchDataAccess.getAllMatchs();
	}
	public ArrayList<Player> getPlayersList() throws DataException {
		return personDataAccess.getAllPlayers();
	}
	public ArrayList<Referee> getRefereesList() throws DataException {
		return personDataAccess.getAllReferees();
	}
	public ArrayList<Location> getLocationsList() throws DataException {
		return locationDataAccess.getAllLocations();
	}
	public ArrayList<Visitor> getVisitorsList() throws DataException {
		return personDataAccess.getAllVisitors();
	}
	public ArrayList<Result> getResultList() throws DataException {
		return resultDataAccess.getAllResults();
	}

	// methods for data operations
	public ArrayList<Match> getAllMatchs() throws DataException {
		return matchDataAccess.getAllMatchs();
	}
	public ArrayList<Player> getAllPlayers() throws DataException {
		return personDataAccess.getAllPlayers();
	}
	public ArrayList<MatchPlayerResearch> getMatchsTournament(int tournamentID) throws DataException {
		return tournamentDataAccess.getMatchsTournament(tournamentID);
	}
	public ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) throws DataException {
		return personDataAccess.getMatchsPlayer(playerID);
	}
	public ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException {
		return personDataAccess.getReservationsVisitor(visitorID);
	}
	public Match getMatch(int matchID) throws DataException {
		return matchDataAccess.getMatch(matchID);
	}
	public int addMatch(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) throws DataException {
		Match match = new Match(dateStart, duration, isFinal, comment, new Tournament(tournamentID), new Referee(refereeID), new Location(locationID));
		return matchDataAccess.addMatch(match);
	}
	public int updateMatch(int matchID, GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) throws DataException {
		Match match = new Match(matchID, dateStart, duration, isFinal, comment, new Tournament(tournamentID), new Referee(refereeID), new Location(locationID));
		return matchDataAccess.updateMatch(match);
	}
	public int deleteMatch(ArrayList<Match> matchs) throws DataException {
		int[] matchsID = new int[matchs.size()];
		int i = 0;
		for (Match match : matchs) {
			matchsID[i] = match.getId();
			i++;
		}
		return matchDataAccess.deleteMatch(matchsID);
	}
	public int addReservation(int visitorID, int matchID, String seatType, char seatRow, int seatNumber, double cost) throws DataException, ValueException {
		String errorMessage = "";
		Match match = matchDataAccess.getMatch(matchID);
		if ((seatRow-'A') >= match.getLocation().getNbRows()) {
			errorMessage += "\n  - La rangée est supérieure à " + (char)(match.getLocation().getNbRows() + 'A' - 1);
		}
		if (seatNumber > match.getLocation().getNbSeatsPerRow()) {
			errorMessage += "\n  - Le numéro de siège est supérieur à " + match.getLocation().getNbSeatsPerRow();
		}

		if (errorMessage != "") {
			throw new ValueException(errorMessage);
		} else {
			if (reservationDataAccess.isReservationExist(visitorID, matchID)) {
				throw new ValueException("\n  Ce visiteur a déjà réservé une place pour ce match");
			} else {
				Reservation reservation = new Reservation(new Visitor(visitorID), new Match(matchID), seatType, seatRow, seatNumber, cost);
				return reservationDataAccess.addReservation(reservation);
			}
		}
	}
	public int addPerson(String type, String firstName, String lastName, Character gender, GregorianCalendar birthDate, Boolean isProfessional, Integer elo, Boolean isVIP, String level) throws DataException, ValueException {
		if (birthDate.getTime().compareTo(Date.from(Instant.now())) > 0)
			throw new ValueException("  La date d'anniversaire est incorrecte");

		switch(type) {
			case "Player" :
				Player player = new Player(firstName, lastName, birthDate, gender, isProfessional, elo);
				return personDataAccess.addPlayer(player);
			case "Visitor" :
				Visitor visitor = new Visitor(firstName, lastName, birthDate, gender, isVIP);
				return personDataAccess.addVisitor(visitor);
			case "Referee" :
				Referee referee = new Referee(firstName, lastName, birthDate, gender, level);
				return personDataAccess.addReferee(referee);
			default : return -1;
		}
	}
}