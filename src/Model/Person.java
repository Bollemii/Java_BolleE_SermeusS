package Model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public abstract class Person implements Serializable {
	private Integer id;
	private String firstName;
	private String lastName;
	private GregorianCalendar birthDate;
	private Character gender;

	public Person(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
	}
	public Person(Integer id, String firstName, String lastName) {
		this (id, firstName, lastName, null, null);
	}
	public Person(String firstName) {
		this (null, firstName, null, null, null);
	}

	public Integer getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
}
