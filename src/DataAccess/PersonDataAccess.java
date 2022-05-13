package DataAccess;

import Exceptions.DataException;
import Model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface PersonDataAccess {
	ArrayList<Player> getAllPlayers() throws DataException;
	ArrayList<Referee> getAllReferees() throws DataException;
	ArrayList<Visitor> getAllVisitors() throws DataException;
	ArrayList<Person> getByBirthday(GregorianCalendar date1, GregorianCalendar date2) throws DataException;

	ArrayList<MatchPlayerResearch> getMatchsPlayer(int playerID) throws DataException;

	ArrayList<Reservation> getReservationsVisitor(int visitorID) throws DataException;

	int addPlayer(Player player) throws DataException;
	int addVisitor(Visitor visitor) throws DataException;
	int addReferee(Referee referee) throws DataException;
}
