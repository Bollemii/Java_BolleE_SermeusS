package Business;

import DataAccess.*;
import Model.*;
import View.*;

import java.util.ArrayList;

public class TournamentManagement {
	private DataAccess dataAccess;
	private ConsoleInteractor consoleInteractor;

	public TournamentManagement() {
		consoleInteractor = new ConsoleInteractor();
		try {
			dataAccess = new DBAccess();
		} catch (DataException exception) {
			consoleInteractor.displayMessage(exception.getMessage());
		}
	}

	public ArrayList<String> getTournamentsList() {
		ArrayList<String> list = new ArrayList<>();
		try {
			for (Tournament tournament : dataAccess.getAllTournaments()) {
				list.add(tournament.toString());
			}
		} catch (DataException exception) {
			consoleInteractor.displayMessage(exception.getMessage());
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
			consoleInteractor.displayMessage(exception.getMessage());
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
			consoleInteractor.displayMessage(exception.getMessage());
		}
		return list;
	}
}
