package DataAccess;

import Exceptions.DataException;
import Model.Reservation;

public interface ReservationDataAccess {
	int addReservation(Reservation reservation) throws DataException;

	boolean isReservationExist(int visitorID, int matchID) throws DataException;
}
