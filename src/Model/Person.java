package Model;

import java.util.GregorianCalendar;

public class Person {
	private Integer id;
	private String firstName;
	private String lastName;
	private GregorianCalendar birthDate;
	private Character gender;
	private String typePerson;

	public Person(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, String typePerson) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.typePerson = typePerson;
	}
	public Person(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender) {
		this(id, firstName, lastName, birthDate, gender, null);
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
	public GregorianCalendar getBirthDate() {
		return birthDate;
	}
	public Character getGender() {
		return gender;
	}
	public String getTypePerson() {
		return typePerson;
	}
	public String getIdentity() {
		return firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return getIdentity() + " (#" + getId() + ")";
	}
}
