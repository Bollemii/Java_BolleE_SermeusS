package DataAccess;

import Exceptions.DataException;
import Exceptions.ValueException;
import Model.Match;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface MatchDataAccess {
	// CRUD
	int addMatch(Match match) throws DataException;
	ArrayList<Match> getAllMatchs() throws DataException, ValueException;
	int updateMatch(Match match) throws DataException;
	int deleteMatch(int[] matchsID) throws DataException;

	Match getMatch(int matchID) throws DataException, ValueException;

	boolean isRefereeAvailable(int refereeID, GregorianCalendar date) throws DataException;
	boolean isLocationAvailable(int locationID, GregorianCalendar date) throws DataException;
}
