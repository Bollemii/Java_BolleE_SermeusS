package Business;

import DataAccess.*;
import Model.*;
import View.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

	// methods for data operations
	public ArrayList<String[]> getAllMatchs() {
		try {
			ArrayList<String[]> listMatchs = new ArrayList<>();
			for(Match match : dataAccess.getAllMatchs()) {
				String[] matchStrings = new String[8];
				matchStrings[0] = match.getId().toString();
				matchStrings[1] = match.getDateStart().get(Calendar.DAY_OF_MONTH) + "/" + (match.getDateStart().get(Calendar.MONTH)+1) + "/" + match.getDateStart().get(Calendar.YEAR);
				matchStrings[2] = match.getDuration() != null ? match.getDuration() + " minutes" : "";
				matchStrings[3] = match.isFinal() ? "finale" : "normal";
				matchStrings[4] = match.getComment() != null ? match.getComment() : "";
				matchStrings[5] = match.getTournament().getName();
				matchStrings[6] = match.getReferee().getIdentity();
				matchStrings[7] = match.getLocation().getName();

				listMatchs.add(matchStrings);
			}
			return listMatchs;
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
			return new ArrayList();
		}
	}

	public void addMatch(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) {
		Match match = new Match(dateStart, duration, isFinal, comment, new Tournament(tournamentID), new Referee(refereeID), new Location(locationID));
		try {
			userInteraction.displayDataUpdate(dataAccess.addMatch(match));
		} catch (DataException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}
}
