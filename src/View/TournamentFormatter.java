package View;

import Business.ManagerUtils;
import Controller.TournamentController;
import Exceptions.DataException;
import Exceptions.ValueException;
import Model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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

	public void closeConnection() {
		try {
			controller.closeConnection();
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}

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
	public ArrayList<String> getLocationsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Location location : controller.getLocationsList()) {
				list.add(location.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	public ArrayList<String> getPlayersList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Player player : controller.getPlayersList()) {
				list.add(player.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	public ArrayList<String> getRefereesList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Referee referee : controller.getRefereesList()) {
				list.add(referee.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	public ArrayList<String> getVisitorsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Visitor visitor : controller.getVisitorsList()) {
				list.add(visitor.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}

	// methods for data operations
	public ArrayList<Match> getAllMatchs() {
		ArrayList<Match> listMatchs = new ArrayList<>();
		try {
			listMatchs = controller.getAllMatchs();
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listMatchs;
	}
	public ArrayList<Player> getAllPlayers() {
		ArrayList<Player> listPlayers = new ArrayList<>();
		try {
			listPlayers = controller.getAllPlayers();
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listPlayers;
	}
	public ArrayList<MatchPlayerResearch> getMatchsTournament(int tournamentID) {
		ArrayList<MatchPlayerResearch> listMatchs = new ArrayList<>();
		try {
			listMatchs = controller.getMatchsTournament(tournamentID);
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listMatchs;
	}
	public ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) {
		ArrayList<MatchPlayerResearch> listMatchs = new ArrayList<>();
		try {
			listMatchs = controller.getMatchsPlayer(playerID);
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listMatchs;
	}
	public ArrayList<Reservation> getReservationsVisitor(int visitorID) {
		ArrayList<Reservation> listReservations = new ArrayList<>();
		try {
			listReservations = controller.getReservationsVisitor(visitorID);
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listReservations;
	}
	public Match getMatch(int matchID) {
		Match match = null;
		try {
			match = controller.getMatch(matchID);
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return match;
	}
	public void addMatch(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) {
		try {
			userInteraction.displayDataUpdate(controller.addMatch(dateStart, duration, isFinal, comment, tournamentID, refereeID, locationID));
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}
	public void updateMatch(int matchID, GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) 	{
		try {
			userInteraction.displayDataUpdate(controller.updateMatch(matchID, dateStart, duration, isFinal, comment, tournamentID, refereeID, locationID));
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}
	public void deleteMatch(List matchs) {
		try {
			userInteraction.displayDataUpdate(controller.deleteMatch(matchs));
		} catch(DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}
	public void addReservation(int visitorID, int matchID, String seatType, char seatRow, int seatNumber, double cost) {
		try {
			userInteraction.displayDataUpdate(controller.addReservation(visitorID, matchID, seatType, seatRow, seatNumber, cost));
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		} catch (ValueException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}
}
