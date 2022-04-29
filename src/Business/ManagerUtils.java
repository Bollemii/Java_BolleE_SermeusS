package Business;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerUtils {
	public static Integer getIDFromDescription(String description) {
		Pattern pattern = Pattern.compile(".+\\(#(\\d+)\\)");
		Matcher matcher = pattern.matcher(description);

		return matcher.find() ? Integer.parseInt(matcher.group(1)) : null;
	}
	public static Integer getMatchIDFromDescription(String description) {
		Pattern pattern = Pattern.compile("(\\d+) \\(.*\\)");
		Matcher matcher = pattern.matcher(description);

		return matcher.find() ? Integer.parseInt(matcher.group(1)) : null;
	}

	public static String getDateString(GregorianCalendar calendar) {
		return String.format("%02d/%02d/%4d %02d:%02d", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
	}
}
