package Business;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerUtils {
	public static Integer getPersonIDFromDescription(String description) {
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
		return String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)) + "/" + String.format("%02d", (calendar.get(Calendar.MONTH)+1)) + "/" + String.format("%4d", calendar.get(Calendar.YEAR));
	}
}
