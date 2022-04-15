package Model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Match {
	private Integer id;
	private GregorianCalendar dateStart;
	private Integer duration;
	private Boolean isFinal;
	private String comment;
	private Tournament tournament;
	private Referee referee;
	private Location location;

	public Match(int id, GregorianCalendar dateStart, boolean isFinal, String comment, Tournament tournament, Referee referee, Location location) {
		this.id = id;
		this.dateStart = dateStart;
		this.isFinal = isFinal;
		this.comment = comment;
		this.tournament = tournament;
		this.referee = referee;
		this.location = location;
	}

	public int getId() {
		return id;
	}
	public GregorianCalendar getDateStart() {
		return dateStart;
	}
	public Integer getDuration() {
		return Integer.valueOf(duration);
	}
	public String getComment() {
		return comment;
	}
	public Tournament getTournament() {
		return tournament;
	}
	public Referee getReferee() {
		return referee;
	}
	public Location getLocation() {
		return location;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return  "Match " + id + " { " +
				"dateStart = " + dateStart.get(Calendar.DAY_OF_MONTH) + "/" + dateStart.get(Calendar.MONTH) + "/" + dateStart.get(Calendar.YEAR) +
				", duration = " + duration +
				", isFinal = " + isFinal +
				", comment = '" + comment + '\'' +
				", tournament = '" + tournament.getName() + '\'' +
				", referee = '" + referee.getFirstName() + '\'' +
				", location = '" + location.getName() + '\'' +
				" }";
	}
}
