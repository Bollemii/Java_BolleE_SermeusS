package View;

import Controller.TournamentController;
import Exceptions.DataException;
import Exceptions.ValueException;
import Model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TournamentFormatter {
	private TournamentController controller;
	private UserInteraction userInteraction;

	public TournamentFormatter() {
		try {
			userInteraction = new UserInteraction();
			controller = new TournamentController();
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
			System.exit(1);
		}
	}

	/**
	 * Close dataBase connection
	 */
	public void closeConnection() {
		try {
			controller.closeConnection();
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}

	/**
	 * get the descriptions list from all tournaments
	 * @return tournaments descriptions list
	 */
	public ArrayList<String> getTournamentsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Tournament tournament : controller.getTournamentsList()) {
				list.add(tournament.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	/**
	 * get the descriptions list from all matchs
	 * @return matchs descriptions list
	 */
	public ArrayList<String> getMatchsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Match match : controller.getMatchsList()) {
				list.add(match.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	/**
	 * get the descriptions list from all locations
	 * @return locations descriptions list
	 */
	public ArrayList<String> getLocationsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Location location : controller.getLocationsList()) {
				list.add(location.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	/**
	 * get the descriptions list from all players
	 * @return players descriptions list
	 */
	public ArrayList<String> getPlayersList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Player player : controller.getPlayersList()) {
				list.add(player.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	/**
	 * get the descriptions list from all referees
	 * @return referees descriptions list
	 */
	public ArrayList<String> getRefereesList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Referee referee : controller.getRefereesList()) {
				list.add(referee.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	/**
	 * get the descriptions list from all visitors
	 * @return visitors descriptions list
	 */
	public ArrayList<String> getVisitorsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Visitor visitor : controller.getVisitorsList()) {
				list.add(visitor.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	/**
	 * get the descriptions list from all matchs that haven't result
	 * @return matchs that haven't result descriptions list
	 */
	public ArrayList<String> getMatchsWithoutResultList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Match match : controller.getMatchsWithoutResultList()) {
				list.add(match.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}

	//methods for data operations
	/**
	 * get all information of all matchs
	 * @return matchs list
	 */
	public ArrayList<Match> getAllMatchs() {
		ArrayList<Match> listMatchs = new ArrayList<>();
		try {
			listMatchs = controller.getAllMatchs();
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listMatchs;
	}
	/**
	 * get all information of all matchs
	 * @return players list
	 */
	public ArrayList<Player> getAllPlayers() {
		ArrayList<Player> listPlayers = new ArrayList<>();
		try {
			listPlayers = controller.getAllPlayers();
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listPlayers;
	}

	/**
	 * get all information of all matchs of a tournament
	 * @param tournamentID id of tournament
	 * @return all matchs of a tournament
	 */
	public ArrayList<MatchPlayerResearch> getMatchsTournament(int tournamentID) {
		ArrayList<MatchPlayerResearch> listMatchs = new ArrayList<>();
		try {
			listMatchs = controller.getMatchsTournament(tournamentID);
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listMatchs;
	}

	/**
	 * get all information of all matchs of a player
	 * @param playerID id of player
	 * @return all matchs of a player
	 */
	public ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) {
		ArrayList<MatchPlayerResearch> listMatchs = new ArrayList<>();
		try {
			listMatchs = controller.getMatchsPlayer(playerID);
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listMatchs;
	}

	/**
	 * get all information of all reservations of a visitor
	 * @param visitorID id of visitor
	 * @return all reservations of a visitor
	 */
	public ArrayList<Reservation> getReservationsVisitor(int visitorID) {
		ArrayList<Reservation> listReservations = new ArrayList<>();
		try {
			listReservations = controller.getReservationsVisitor(visitorID);
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listReservations;
	}

	/**
	 * get all persons who have her birthdays between the dates
	 * @param date1 date start of period
	 * @param date2 date end of period
	 * @return persons list who have their birthday in the period
	 */
	public ArrayList<Person> getPersonsBirthdays(GregorianCalendar date1, GregorianCalendar date2) {
		ArrayList<Person> listPersons = new ArrayList<>();
		try {
			listPersons = controller.getPersonsBirthdays(date1, date2);
		} catch(DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch(ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listPersons;
	}

	/**
	 * get all information of a match
	 * @param matchID id of match
	 * @return match
	 */
	public Match getMatch(int matchID) {
		Match match = null;
		try {
			match = controller.getMatch(matchID);
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return match;
	}

	/**
	 * get all information of a player
	 * @param playerID id of player
	 * @return player
	 */
	public Player getPlayer(int playerID) {
		Player player = null;
		try {
			player = controller.getPlayer(playerID);
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return player;
	}

	/**
	 * add a new match to the dataBase
	 * @param dateStart date of match
	 * @param duration duration of match
	 * @param isFinal if is a final
	 * @param comment comment of match
	 * @param tournamentID id of tournament
	 * @param refereeID id of referee
	 * @param locationID id of location
	 */
	public void addMatch(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) {
		try {
			userInteraction.displayDataUpdate(controller.addMatch(dateStart, duration, isFinal, comment, tournamentID, refereeID, locationID));
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}

	/**
	 * update a match into the dataBase
	 * @param matchID id of match
	 * @param dateStart date of match
	 * @param duration duration of match
	 * @param isFinal if is a final
	 * @param comment comment of match
	 * @param tournamentID id of tournament
	 * @param refereeID id of referee
	 * @param locationID id of location
	 */
	public void updateMatch(int matchID, GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) 	{
		try {
			userInteraction.displayDataUpdate(controller.updateMatch(matchID, dateStart, duration, isFinal, comment, tournamentID, refereeID, locationID));
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}

	/**
	 * delete matchs of the list in the dataBase
	 * @param matchs matchs list
	 */
	public void deleteMatch(ArrayList<Match> matchs) {
		try {
			userInteraction.displayDataUpdate(controller.deleteMatch(matchs));
		} catch(DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}

	/**
	 * add a new reservation in the dataBase
	 * @param visitorID id of visitor
	 * @param matchID id of match
	 * @param seatType type of seat
	 * @param seatRow the row of seat
	 * @param seatNumber number in row of seat
	 * @param cost cost of seat
	 */
	public void addReservation(int visitorID, int matchID, String seatType, char seatRow, int seatNumber, double cost) {
		try {
			userInteraction.displayDataUpdate(controller.addReservation(visitorID, matchID, seatType, seatRow, seatNumber, cost));
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}

	/**
	 * add a new Person : Player, Referee or Visitor
	 * @param type type of person
	 * @param firstName first name of person
	 * @param lastName last name of person
	 * @param gender gender of person
	 * @param birthDate birthdate of person
	 * @param isProfessional if is a professional player
	 * @param elo elo of player
	 * @param isVIP if is a vip visitor
	 * @param level level of referee
	 */
	public void addPerson(String type, String firstName, String lastName, Character gender, GregorianCalendar birthDate, Boolean isProfessional, Integer elo, Boolean isVIP, String level) {
		try {
			userInteraction.displayDataUpdate(controller.addPerson(type, firstName, lastName, gender, birthDate, isProfessional, elo, isVIP, level));
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}

	/**
	 * add results of player and calc their new elo
	 * @param player1Result result of first player in the match
	 * @param player2Result result of second player in the match
	 * @return if everything is ok
	 */
	public boolean addResult(Result player1Result, Result player2Result) {
		try {
			int nbLinesUpdated = controller.addResults(player1Result, player2Result);
			userInteraction.displayDataUpdate(nbLinesUpdated);
			return nbLinesUpdated == 2;
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return false;
	}
}
