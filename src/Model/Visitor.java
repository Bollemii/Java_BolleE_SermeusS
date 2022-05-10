package Model;

import java.util.GregorianCalendar;

public class Visitor extends Person {
	private Boolean isVIP;

	public Visitor(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, Boolean isVIP) {
		super(id, firstName, lastName, birthDate, gender);
		this.isVIP = isVIP;
	}
	public Visitor(String firstName, String lastName, GregorianCalendar birthDate, Character gender, Boolean isVIP) {
		this(null, firstName, lastName, birthDate, gender, isVIP);
	}
	public Visitor(Integer id, String firstName, String lastName) {
		this(id, firstName, lastName, null, null, null);
	}
	public Visitor(Integer id) {
		this(id, null, null);
	}

	public Boolean isVIP() {
		return isVIP;
	}
}
