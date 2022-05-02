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
	public ArrayList<String[]> getAllMatchs() {
		ArrayList<String[]> listMatchs = new ArrayList<>();
		try {
			for(Match match : controller.getAllMatchs()) {
				String[] matchStrings = new String[8];
				matchStrings[0] = match.getId().toString();
				matchStrings[1] = ManagerUtils.getDateString(match.getDateStart());
				matchStrings[2] = match.getDuration() != null ? match.getDuration() + " minutes" : "";
				matchStrings[3] = match.isFinal() ? "finale" : "normal";
				matchStrings[4] = match.getComment() != null ? match.getComment() : "";
				matchStrings[5] = match.getTournament().getName();
				matchStrings[6] = match.getReferee().getIdentity();
				matchStrings[7] = match.getLocation().getName();

				listMatchs.add(matchStrings);
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listMatchs;
	}
	public ArrayList<String[]> getAllPlayers() {
		ArrayList<String[]> listPlayers = new ArrayList<>();
		try {
			for(Player player : controller.getAllPlayers()) {
				String[] playerStrings = new String[7];
				playerStrings[0] = player.getId().toString();
				playerStrings[1] = player.getFirstName();
				playerStrings[2] = player.getLastName();
				playerStrings[3] = ManagerUtils.getDateString(player.getBirthDate());
				playerStrings[4] = player.getGender().toString();
				playerStrings[5] = player.isProfessional() ? "professionel" : "amateur";
				playerStrings[6] = String.valueOf(player.getElo());

				listPlayers.add(playerStrings);
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listPlayers;
	}
	public ArrayList<String[]> getMatchsTournament(int tournamentID) {
		ArrayList<String[]> listMatchs = new ArrayList<>();
		try {
			for(MatchPlayerResearch match : controller.getMatchsTournament(tournamentID)) {
				String[] matchStrings = new String[5];
				matchStrings[0] = ManagerUtils.getDateString(match.getMatch().getDateStart());
				matchStrings[1] = match.getPlayer().getFirstName();
				matchStrings[2] = match.getPlayer().getLastName();
				matchStrings[3] = String.valueOf(match.getPlayer().getElo());
				matchStrings[4] = match.getPoints().toString();

				listMatchs.add(matchStrings);
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listMatchs;
	}
	public ArrayList<String[]> getMatchsPlayer(int playerID) {
		ArrayList<String[]> listMatchs = new ArrayList<>();
		try {
			for(MatchPlayerResearch match : controller.getMatchsPlayer(playerID)) {
				String[] matchStrings = new String[5];
				matchStrings[0] = match.getTournament().getName();
				matchStrings[1] = ManagerUtils.getDateString(match.getMatch().getDateStart());
				matchStrings[2] = match.getLocation().getName();
				matchStrings[3] = match.getReferee().getIdentity();
				matchStrings[4] = match.getPoints().toString();

				listMatchs.add(matchStrings);
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listMatchs;
	}
	public ArrayList<String[]> getReservationsVisitor(int visitorID) {
		ArrayList<String[]> listReservations = new ArrayList<>();
		try {
			for(Reservation reservation : controller.getReservationsVisitor(visitorID)) {
				String[] reservationStrings = new String[5];
				reservationStrings[0] = reservation.getMatch().getTournament().getName();
				reservationStrings[1] = ManagerUtils.getDateString(reservation.getMatch().getDateStart());
				reservationStrings[2] = reservation.getCodeSeat();
				reservationStrings[3] = reservation.getCost().toString() + "€";
				reservationStrings[4] = reservation.getMatch().getLocation().getName();

				listReservations.add(reservationStrings);
			}
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
