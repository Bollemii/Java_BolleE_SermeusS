package Model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Referee extends Person implements Serializable {
	private String level;

	public Referee(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, String level) {
		super(id, firstName, lastName, birthDate, gender);
		this.level = level;
	}
	public Referee(Integer id, String firstName, String lastName) {
		super(id, firstName, lastName);
		this.level = null;
	}
	public Referee(String firstName) {
		super(firstName);
		this.level = null;
	}
}
