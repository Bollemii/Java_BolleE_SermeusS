package Model;

import java.util.GregorianCalendar;

public class Player extends Person {
	private Boolean isProfessional;
	private Integer elo;

	public Player(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, Boolean isProfessional, Integer elo) {
		super(id, firstName, lastName, birthDate, gender);
		this.isProfessional = isProfessional;
		this.elo = elo;
	}
	public Player(String firstName, String lastName, GregorianCalendar birthDate, Character gender, Boolean isProfessional, Integer elo) {
		this(null, firstName, lastName, birthDate, gender, isProfessional, elo);
	}
	public Player(Integer id, String firstName, String lastName, Integer elo) {
		this(id, firstName, lastName, null, null, null, elo);
	}
	public Player(Integer id, String firstName, String lastName) {
		this(id, firstName, lastName, null, null, null, null);
	}
	public Player(Integer id) {
		this(id, null, null);
	}

	public Boolean isProfessional() {
		return isProfessional;
	}
	public Integer getElo() {
		return elo;
	}
}
