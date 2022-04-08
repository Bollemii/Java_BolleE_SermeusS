package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Visitor extends Person {
	private boolean isVIP;
	private ArrayList<Reservation> reservations;

	public Visitor(int id, String firstName, String lastName, GregorianCalendar birthDate, char gender, boolean isVIP) {
		super(id, firstName, lastName, birthDate, gender);
		this.isVIP = isVIP;
		this.reservations = new ArrayList<>();
	}

	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
}
