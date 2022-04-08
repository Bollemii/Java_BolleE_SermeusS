package Model;

import java.util.GregorianCalendar;

public abstract class Person {
	private int id;
	private String firstName;
	private String lastName;
	private GregorianCalendar birthDate;
	private char gender;

	public Person(int id, String firstName, String lastName, GregorianCalendar birthDate, char gender) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
