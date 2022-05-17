package Model;

import Exceptions.ValueException;

import java.util.GregorianCalendar;

public class Player extends Person {
	private Boolean isProfessional;
	private Integer elo;

	public Player(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, Boolean isProfessional, Integer elo) throws ValueException {
		super(id, firstName, lastName, birthDate, gender);
		this.isProfessional = isProfessional;
		setElo(elo);
	}
	public Player(String firstName, String lastName, GregorianCalendar birthDate, Character gender, Boolean isProfessional, Integer elo) throws ValueException {
		this(null, firstName, lastName, birthDate, gender, isProfessional, elo);
	}
	public Player(Integer id, String firstName, String lastName, Integer elo) throws ValueException {
		this(id, firstName, lastName, null, null, null, elo);
	}
	public Player(Integer id, String firstName, String lastName) throws ValueException {
		this(id, firstName, lastName, null, null, null, null);
	}
	public Player(Integer id) throws ValueException {
		this(id, null, null);
	}

	public Boolean isProfessional() {
		return isProfessional;
	}
	public Integer getElo() {
		return elo;
	}

	public void setElo(Integer elo) throws ValueException {
		if (elo != null && elo < 0)
			throw new ValueException("L'elo d'un joueur doit être positif");
		this.elo = elo;
	}
}
