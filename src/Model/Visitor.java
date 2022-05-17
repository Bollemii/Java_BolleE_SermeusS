package Model;

import Exceptions.ValueException;

import java.util.GregorianCalendar;

public class Visitor extends Person {
	private Boolean isVIP;

	public Visitor(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, Boolean isVIP) throws ValueException {
		super(id, firstName, lastName, birthDate, gender);
		this.isVIP = isVIP;
	}
	public Visitor(String firstName, String lastName, GregorianCalendar birthDate, Character gender, Boolean isVIP) throws ValueException {
		this(null, firstName, lastName, birthDate, gender, isVIP);
	}
	public Visitor(Integer id, String firstName, String lastName) throws ValueException {
		this(id, firstName, lastName, null, null, null);
	}
	public Visitor(Integer id) throws ValueException {
		this(id, null, null);
	}

	public Boolean isVIP() {
		return isVIP;
	}
}
