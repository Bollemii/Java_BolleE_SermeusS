package Model;

import Business.ManagerUtils;
import Exceptions.ValueException;

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

	public Match(Integer id, GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, Tournament tournament, Referee referee, Location location) throws ValueException {
		this.id = id;
		this.dateStart = dateStart;
		setDuration(duration);
		this.isFinal = isFinal;
		this.comment = comment;
		this.tournament = tournament;
		this.referee = referee;
		this.location = location;
	}
	public Match(Integer id, GregorianCalendar dateStart, Boolean isFinal, Tournament tournament, Referee referee, Location location) throws ValueException {
		this(id, dateStart, null, isFinal, null, tournament, referee, location);
	}
	public Match(GregorianCalendar dateStart, Integer duration, Boolean isFinal, String comment, Tournament tournament, Referee referee, Location location) throws ValueException {
		this(null, dateStart, duration, isFinal, comment, tournament, referee, location);
	}
	public Match(GregorianCalendar dateStart, Tournament tournament, Referee referee, Location location) throws ValueException {
		this(null, dateStart, null, tournament, referee, location);
	}
	public Match(Integer id, GregorianCalendar dateStart, Tournament tournament, Location location) throws ValueException {
		this(id, dateStart, null, tournament, null, location);
	}
	public Match(GregorianCalendar dateStart) throws ValueException {
		this(null, dateStart, null, null);
	}
	public Match(Integer id) throws ValueException {
		this(id, null, null, null);
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

	public void setDuration(Integer duration) throws ValueException {
		if (duration != null && duration < 0)
			throw new ValueException("La durée d'un match doit être positive");
		this.duration = duration;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + " (" + ManagerUtils.getDateHourString(dateStart) + ")";
	}
}
