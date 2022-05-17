package DataAccess;

import Exceptions.DataException;
import Exceptions.ValueException;
import Model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface PersonDataAccess {
	ArrayList<Player> getAllPlayers() throws DataException, ValueException;
	ArrayList<Referee> getAllReferees() throws DataException, ValueException;
	ArrayList<Visitor> getAllVisitors() throws DataException, ValueException;
	ArrayList<Person> getByBirthday(GregorianCalendar date1, GregorianCalendar date2) throws DataException, ValueException;
	Player getPlayerById(int playerID) throws DataException, ValueException;

	ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) throws DataException, ValueException;
	ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException, ValueException;

	int addPlayer(Player player) throws DataException;
	int addVisitor(Visitor visitor) throws DataException;
	int addReferee(Referee referee) throws DataException;

	int updateEloPlayer(int playerID, int elo) throws DataException;
}
