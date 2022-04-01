package Model;

import java.util.GregorianCalendar;

public abstract class Person {
	private String firstName;
	private String lastName;
	private GregorianCalendar birthDate;
	private char gender;

	public Person(String firstName, String lastName, GregorianCalendar birthDate, char gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
	}
}
