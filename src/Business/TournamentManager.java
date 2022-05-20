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
		try {
			return matchDataAccess.getAllMatchs();
		} catch (ValueException exception) {
			throw new RuntimeException(exception);
		}
	}
	public ArrayList<Player> getPlayersList() throws DataException, ValueException {
		return personDataAccess.getAllPlayers();
	}
	public ArrayList<Referee> getRefereesList() throws DataException, ValueException {
		return personDataAccess.getAllReferees();
	}
	public ArrayList<Location> getLocationsList() throws DataException, ValueException {
		return locationDataAccess.getAllLocations();
	}
	public ArrayList<Visitor> getVisitorsList() throws DataException, ValueException {
		return personDataAccess.getAllVisitors();
	}
	public ArrayList<Result> getResultList() throws DataException, ValueException {
		return resultDataAccess.getAllResults();
	}

	// methods for data operations
	public ArrayList<Match> getAllMatchs() throws DataException, ValueException {
		return matchDataAccess.getAllMatchs();
	}
	public ArrayList<Player> getAllPlayers() throws DataException, ValueException {
		return personDataAccess.getAllPlayers();
	}
	public ArrayList<MatchPlayerResearch> getMatchsTournament(int tournamentID) throws DataException, ValueException {
		return tournamentDataAccess.getMatchsTournament(tournamentID);
	}
	public ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) throws DataException, ValueException {
		return personDataAccess.getMatchsPlayer(playerID);
	}
	public ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException, ValueException {
		return personDataAccess.getReservationsVisitor(visitorID);
	}
	public ArrayList<Person> getPersonsBirthdays(GregorianCalendar date1, GregorianCalendar date2) throws DataException, ValueException {
		if (date1.compareTo(date2) > 0)
			throw new ValueException("  La première date doit être inférieure à la deuxième");

		return personDataAccess.getByBirthday(date1, date2);
	}
	public Match getMatch(int matchID) throws DataException, ValueException {
		return matchDataAccess.getMatch(matchID);
	}
	public Player getPlayer(int playerID) throws DataException, ValueException {
		return personDataAccess.getPlayerById(playerID);
	}
	public int addMatch(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) throws DataException, ValueException {
		String errorMessage = "";
		if (!matchDataAccess.isRefereeAvailable(refereeID, dateStart)) {
			errorMessage += "\n  - Cet arbitre est déjà occupé à ce moment";
		}
		if (!matchDataAccess.isLocationAvailable(locationID, dateStart)) {
			errorMessage += "\n  - Cet emplacement est déjà occupé à ce moment";
		}
		if (!errorMessage.equals("")) {
			throw new ValueException(errorMessage);
		}

		Match match = new Match(dateStart, duration, isFinal, comment, new Tournament(tournamentID), new Referee(refereeID), new Location(locationID));
		return matchDataAccess.addMatch(match);
	}
	public int updateMatch(int matchID, GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) throws DataException, ValueException {
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
		if (cost < 0) {
			errorMessage += "\n  - Le coût doit être positif";
		}
		if (!errorMessage.equals("")) {
			throw new ValueException(errorMessage);
		}

		if (reservationDataAccess.isReservationExist(visitorID, matchID)) {
			throw new ValueException("\n  Ce visiteur a déjà réservé une place pour ce match");
		} else {
			Reservation reservation = new Reservation(new Visitor(visitorID), new Match(matchID), seatType, seatRow, seatNumber, cost);
			return reservationDataAccess.addReservation(reservation);
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
	public int addResult(Result player1Result, Result player2Result) throws DataException, ValueException {
		String errorMessage = "";
		if (player1Result.getPoints() < 0)
			errorMessage += "\n  - les points du 1er joueur doivent être positifs";
		if (player2Result.getPoints() < 0)
			errorMessage += "\n  - les points du 2e joueur doivent être positifs";
		if (!errorMessage.equals(""))
			throw new ValueException(errorMessage);

		int nbLinesUpdated = 0;
		nbLinesUpdated += resultDataAccess.addResult(player1Result);
		nbLinesUpdated += resultDataAccess.addResult(player2Result);

		int oldElo1 = player1Result.getPlayer().getElo();
		int oldElo2 = player2Result.getPlayer().getElo();
		int points1 = player1Result.getPoints();
		int points2 = player2Result.getPoints();
		personDataAccess.updateEloPlayer(player1Result.getPlayer().getId(), calcElo(oldElo1, oldElo2, points1, points2));
		personDataAccess.updateEloPlayer(player2Result.getPlayer().getId(), calcElo(oldElo2, oldElo1, points1, points2));

		return nbLinesUpdated;
	}

	// calculations
	private int calcElo(int elo1, int points1, int elo2, int points2) {
		// Formules :	P(D) = 1 / (1 + 10 ^ (-D / 400))
		// 				E(n+1) = E(n) + K * (W - P(D))

		int d = Math.min(Math.abs(elo1 - elo2), 400);
		if (elo1 - elo2 < 0)
			d *= -1;

		double pD = 1 / (1 + Math.pow(10, -d / 400.));

		double w;
		if (points1 == points2) {
			w = 0.5;
		} else if (points1 > points2) {
			w = 1;
		} else {
			w = 0;
		}

		int k = elo1 < 2400 ? 30 : 10;

		return Math.max((int)Math.round(elo1 + k * (w - pD)), 0);
	}
}