package david.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SimpleDateFormatExample {
	public static void main(String[] arg) throws ParseException {
		System.out.println(TimeZone.getDefault().getDisplayName());
		Date now = new Date();
		System.out.println(now);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		System.out.println(sdf.format(now));
		sdf.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		System.out.println(sdf.format(now));
		
		System.out.println(sdf.parse("2016-03-02 23:17:00.000"));
		
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date1= sdf1.parse("2016-03-03 12:12:12.011");
		Date date2= sdf1.parse("2016-03-03 12:12:13.011");
		System.out.println(date1.before(date2));
		
		
		
		
		
		
		

		Calendar cal = Calendar.getInstance();
		// You cannot use Date class to extract individual Date fields
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH); // 0 to 11
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		System.out.printf("Now is %4d/%02d/%02d %02d:%02d:%02d\n", // Pad with
																	// zero
				year, month + 1, day, hour, minute, second);
	}
}
