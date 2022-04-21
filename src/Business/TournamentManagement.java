package Business;

import DataAccess.*;
import Model.*;
import View.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class TournamentManagement {
	private DataAccess dataAccess;
	private UserInteraction userInteraction;

	public TournamentManagement() {
		userInteraction = new UserInteraction();
		try {
			dataAccess = new DBAccess();
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}

	// lists for combobox from forms
	public ArrayList<String> getTournamentsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Tournament tournament : dataAccess.getAllTournaments()) {
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
			for (Match match : dataAccess.getAllMatchs()) {
				list.add(match.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	public ArrayList<String> getPlayersList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Player player : dataAccess.getAllPlayers()) {
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
			for (Referee referee : dataAccess.getAllReferees()) {
				list.add(referee.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	public ArrayList<String> getLocationsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Location location : dataAccess.getAllLocations()) {
				list.add(location.toString());
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return list;
	}
	public ArrayList<String> getVisitorsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Visitor visitor : dataAccess.getAllVisitors()) {
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
			for(Match match : dataAccess.getAllMatchs()) {
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
	public ArrayList<String[]> getReservationsVisitor(int visitorID) {
		ArrayList<String[]> listReservations = new ArrayList<>();
		try {
			for(Reservation reservation : dataAccess.getReservationsVisitor(visitorID)) {
				String[] reservationStrings = new String[3];
				reservationStrings[0] = reservation.getMatch().toString();
				reservationStrings[1] = reservation.getCodeSeat();
				reservationStrings[2] = reservation.getCost().toString();

				listReservations.add(reservationStrings);
			}
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
		return listReservations;
	}
	public void addMatch(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) {
		Match match = new Match(dateStart, duration, isFinal, comment, new Tournament(tournamentID), new Referee(refereeID), new Location(locationID));
		try {
			userInteraction.displayDataUpdate(dataAccess.addMatch(match));
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}
	public void deleteMatch(List matchsID) {
		StringBuilder matchsList = new StringBuilder();
		boolean isFirst = true;
		for (Object match : matchsID) {
			if (!isFirst) {
				matchsList.append(",");
			} else {
				isFirst = false;
			}
			matchsList.append(ManagerUtils.getMatchIDFromDescription(match.toString()));
		}

		try {
			userInteraction.displayDataUpdate(dataAccess.deleteMatch(matchsList.toString()));
		} catch(DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}
}
