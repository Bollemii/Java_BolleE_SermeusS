package Model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Visitor extends Person implements Serializable {
	private Boolean isVIP;

	public Visitor(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, Boolean isVIP) {
		super(id, firstName, lastName, birthDate, gender);
		this.isVIP = isVIP;
	}
	public Visitor(Integer id, String firstName, String lastName) {
		this(id, firstName, lastName, null, null, null);
	}
}
