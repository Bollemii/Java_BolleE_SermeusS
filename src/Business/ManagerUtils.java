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
	public static GregorianCalendar getMatchDateFromDescription(String description) {
		Pattern pattern = Pattern.compile("\\d+ \\((.*)\\)");
		Matcher matcher = pattern.matcher(description);

		if (matcher.find()) {
			String dateText = matcher.group(1);

			return new GregorianCalendar(
				Integer.parseInt(dateText.substring(6, 10)),
				Integer.parseInt(dateText.substring(3, 5))-1,
				Integer.parseInt(dateText.substring(0, 2)),
				Integer.parseInt(dateText.substring(11, 13)),
				Integer.parseInt(dateText.substring(14, 16))
			);
		}
		return null;
	}

	public static String getDateString(GregorianCalendar calendar) {
		return String.format("%02d/%02d/%4d %02d:%02d", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
	}
}
