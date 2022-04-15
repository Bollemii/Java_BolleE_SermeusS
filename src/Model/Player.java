package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Player extends Person implements Serializable {
	private boolean isProfessional;
	private double elo;
	private ArrayList<Result> results;

	public Player(int id, String firstName, String lastName, GregorianCalendar birthDate, char gender, boolean isProfessional, double elo) {
		super(id, firstName, lastName, birthDate, gender);
		this.isProfessional = isProfessional;
		this.elo = elo;
		results = new ArrayList<>();
	}

	public void addResult(Result result) {
		results.add(result);
	}
}
