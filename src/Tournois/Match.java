package Tournois;

import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Timer;

public class Match {
	private GregorianCalendar dateStart;
	private int duration;
	private boolean isFinal;
	private String comment;
	private Tournament tournament;
	private ArrayList<Result> results;
	private Referee referee;
	private Location location;

	public Match(GregorianCalendar dateStart, boolean isFinal, String comment, Tournament tournament, ArrayList<Result> results, Referee referee, Location location) {
		this.dateStart = dateStart;
		this.isFinal = isFinal;
		this.comment = comment;
		this.tournament = tournament;
		this.results = results;
		this.referee = referee;
		this.location = location;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
