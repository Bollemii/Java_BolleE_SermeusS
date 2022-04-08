package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Referee extends Person {
	private String level;
	private ArrayList<Match> matchs;

	public Referee(int id, String firstName, String lastName, GregorianCalendar birthDate, char gender, String level) {
		super(id, firstName, lastName, birthDate, gender);
		this.level = level;
		matchs = new ArrayList<>();
	}

	public void addMatch(Match match) {
		matchs.add(match);
	}
}
