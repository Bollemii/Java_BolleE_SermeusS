package Model;

import java.util.GregorianCalendar;

public class Referee extends Person {
	private String level;

	public Referee(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, String level) {
		super(id, firstName, lastName, birthDate, gender);
		this.level = level;
	}
	public Referee(String firstName) {
		super(firstName);
		this.level = null;
	}
}
