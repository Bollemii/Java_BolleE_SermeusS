package Model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Player extends Person implements Serializable {
	private Boolean isProfessional;
	private Double elo;

	public Player(Integer id, String firstName, String lastName, GregorianCalendar birthDate, Character gender, Boolean isProfessional, Double elo) {
		super(id, firstName, lastName, birthDate, gender);
		this.isProfessional = isProfessional;
		this.elo = elo;
	}
	public Player(String firstName, String lastName, Double elo) {
		this(null, firstName, lastName, null, null, null, elo);
	}
	public Player(Integer id, String firstName, String lastName) {
		this(id, firstName, lastName, null, null, null, null);
	}

	public double getElo() {
		return elo;
	}
}
