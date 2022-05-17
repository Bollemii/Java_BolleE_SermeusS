package Model;

import Exceptions.ValueException;

import java.util.Arrays;
import java.util.GregorianCalendar;

public class Person {
	private final static String[] TYPES_PERSON = {"Player", "Referee", "Visitor"};
	private Integer id;
	private String firstName;
	private String lastName;
	private GregorianCalendar birthDate;
	private Character gender;
	private String typePerson;

	public Person(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, String typePerson) throws ValueException {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		setTypePerson(typePerson);
	}
	public Person(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender) throws ValueException {
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

	public void setTypePerson(String typePerson) throws ValueException {
		if (typePerson != null && Arrays.stream(TYPES_PERSON).noneMatch(x -> x.equals(typePerson)))
			throw new ValueException(typePerson + " n'est pas un type de personne valide");
		this.typePerson = typePerson;
	}

	@Override
	public String toString() {
		return getIdentity() + " (#" + getId() + ")";
	}
}
