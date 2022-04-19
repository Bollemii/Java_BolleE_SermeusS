package Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Match implements Serializable {
	private Integer id;
	private GregorianCalendar dateStart;
	private Integer duration;
	private Boolean isFinal;
	private String comment;
	private Tournament tournament;
	private Referee referee;
	private Location location;

	public Match(Integer id, GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, Tournament tournament, Referee referee, Location location) {
		this.id = id;
		this.dateStart = dateStart;
		this.duration = duration;
		this.isFinal = isFinal;
		this.comment = comment;
		this.tournament = tournament;
		this.referee = referee;
		this.location = location;
	}
	public Match(Integer id, GregorianCalendar dateStart, Boolean isFinal, Tournament tournament, Referee referee, Location location) {
		this(id, dateStart, null, isFinal, null, tournament, referee, location);
	}
	public Match(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, Tournament tournament, Referee referee, Location location) {
		this(null, dateStart, duration, isFinal, comment, tournament, referee, location);
	}

	public Integer getId() {
		return id;
	}
	public GregorianCalendar getDateStart() {
		return dateStart;
	}
	public Boolean isFinal() {
		return isFinal;
	}
	public Integer getDuration() {
		return duration;
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
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return  "Match " + id + " { " +
				"dateStart = " + dateStart.get(Calendar.DAY_OF_MONTH) + "/" + (dateStart.get(Calendar.MONTH)+1) + "/" + dateStart.get(Calendar.YEAR) +
				", duration = " + duration +
				", isFinal = " + isFinal +
				", comment = '" + comment + '\'' +
				", tournament = '" + tournament.getName() + '\'' +
				", referee = '" + referee.getFirstName() + '\'' +
				", location = '" + location.getName() + '\'' +
				" }";
	}
}
