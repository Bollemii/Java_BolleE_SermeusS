package Business;

import DataAccess.*;
import Model.*;
import View.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TournamentManagement {
	private DataAccess dataAccess;
	private UserInteraction consoleInteractor;

	public TournamentManagement() {
		consoleInteractor = new UserInteraction();
		try {
			dataAccess = new DBAccess();
		} catch (DataException exception) {
			consoleInteractor.displayErrorMessage(exception.getMessage());
		}
	}

	public ArrayList<String> getTournamentsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Tournament tournament : dataAccess.getAllTournaments()) {
				list.add(tournament.toString());
			}
		} catch (DataException exception) {
			consoleInteractor.displayErrorMessage(exception.getMessage());
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
			consoleInteractor.displayErrorMessage(exception.getMessage());
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
			consoleInteractor.displayErrorMessage(exception.getMessage());
		}
		return list;
	}

	public int addMatch(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, int tournamentID, int refereeID, int locationID) {
		Match match = new Match(dateStart, duration, isFinal, comment, new Tournament(tournamentID), new Referee(refereeID), new Location(locationID));
		try {
			return dataAccess.addMatch(match);
		} catch (DataException exception) {
			consoleInteractor.displayErrorMessage(exception.getMessage());
			return -1;
		}
	}
}
