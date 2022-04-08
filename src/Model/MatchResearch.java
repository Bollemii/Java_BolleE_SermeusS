package Model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MatchResearch {
	private GregorianCalendar dateStart;
	private int duration;
	private boolean isFinal;
	private String comment;
	private String tournament;
	private String referee;
	private String location;

	public MatchResearch(GregorianCalendar dateStart, boolean isFinal, String comment, String tournament, String referee, String location) {
		this.dateStart = dateStart;
		this.isFinal = isFinal;
		this.comment = comment;
		this.tournament = tournament;
		this.referee = referee;
		this.location = location;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "MatchResearch{" +
				"dateStart = " + dateStart.get(Calendar.DAY_OF_MONTH) + "/" + dateStart.get(Calendar.MONTH) + "/" + dateStart.get(Calendar.YEAR) +
				", duration = " + duration +
				", isFinal = " + isFinal +
				", comment = '" + comment + '\'' +
				", tournament = '" + tournament + '\'' +
				", referee = '" + referee + '\'' +
				", location = '" + location + '\'' +
				'}';
	}
}
