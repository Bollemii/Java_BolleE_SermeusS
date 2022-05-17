package DataAccess;

import Exceptions.DataException;
import Exceptions.ValueException;
import Model.MatchPlayerResearch;
import Model.Tournament;

import java.util.ArrayList;

public interface TournamentDataAccess {
	ArrayList<Tournament> getAllTournaments() throws DataException;

	ArrayList<MatchPlayerResearch> getMatchsTournament(int tournamentID) throws DataException, ValueException;
}
