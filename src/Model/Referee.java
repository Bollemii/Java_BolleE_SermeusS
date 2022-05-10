package Model;

import java.util.GregorianCalendar;

public class Referee extends Person {
	private String level;

	public Referee(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, String level) {
		super(id, firstName, lastName, birthDate, gender);
		this.level = level;
	}
	public Referee(String firstName, String lastName, GregorianCalendar birthDate, Character gender, String level) {
		this(null, firstName, lastName, birthDate, gender, level);
	}
	public Referee(Integer id, String firstName, String lastName) {
		this(id, firstName, lastName, null, null, null);
	}
	public Referee(String firstName, String lastName) {
		this(null, firstName, lastName);
	}
	public Referee(Integer id) {
		this(id, null, null);
	}

	public String getLevel() {
		return level;
	}
}
