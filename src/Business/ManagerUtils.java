package Business;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ManagerUtils {
	/**
	 * get the id from description
	 * @Format: text (id)
	 * @param description description of Person, Location, Tournament... except Match
	 * @return identifier
	 */
	public static Integer getIDFromDescription(String description) {
		Pattern pattern = Pattern.compile(".+\\(#(\\d+)\\)");
		Matcher matcher = pattern.matcher(description);

		return matcher.find() ? Integer.parseInt(matcher.group(1)) : null;
	}

	/**
	 * get the match id from his description
	 * @Format: id (date)
	 * @param description description of Match
	 * @return match identifier
	 */
	public static Integer getMatchIDFromDescription(String description) {
		Pattern pattern = Pattern.compile("(\\d+) \\(.*\\)");
		Matcher matcher = pattern.matcher(description);

		return matcher.find() ? Integer.parseInt(matcher.group(1)) : null;
	}

	/**
	 * get the toString from calendar with hour
	 * @param calendar date to get date and hour description
	 * @return toString from calendar
	 */
	public static String getDateHourString(GregorianCalendar calendar) {
		return getDateString(calendar) + String.format(" %02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
	}

	/**
	 * get the toString from calendar
	 * @param calendar date to get date description
	 * @return toString from calendar
	 */
	public static String getDateString(GregorianCalendar calendar) {
		return String.format("%02d/%02d/%4d", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
	}
}
