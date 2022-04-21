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

	public static String getDateString(GregorianCalendar calendar) {
		return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.YEAR);
	}
}
