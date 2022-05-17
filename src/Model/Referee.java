package Model;

import Exceptions.ValueException;

import java.util.GregorianCalendar;

public class Referee extends Person {
	private String level;

	public Referee(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, String level) throws ValueException {
		super(id, firstName, lastName, birthDate, gender);
		this.level = level;
	}
	public Referee(String firstName, String lastName, GregorianCalendar birthDate, Character gender, String level) throws ValueException {
		this(null, firstName, lastName, birthDate, gender, level);
	}
	public Referee(Integer id, String firstName, String lastName) throws ValueException {
		this(id, firstName, lastName, null, null, null);
	}
	public Referee(String firstName, String lastName) throws ValueException {
		this(null, firstName, lastName);
	}
	public Referee(Integer id) throws ValueException {
		this(id, null, null);
	}

	public String getLevel() {
		return level;
	}
}
