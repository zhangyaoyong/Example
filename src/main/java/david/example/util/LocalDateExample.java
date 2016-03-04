package david.example.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateExample {
	public static void main(String[] arg) {
		
		LocalDateTime todayInLos = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
		System.out.println(todayInLos);
		LocalDateTime localToday=LocalDateTime.of(todayInLos.getYear(), todayInLos.getMonth(), todayInLos.getDayOfMonth(), todayInLos.getHour(), todayInLos.getMinute(), todayInLos.getSecond(), todayInLos.getNano());
		
		Instant instant = localToday.atZone(ZoneId.systemDefault()).toInstant();
	    Date dateFromOld = Date.from(instant);
	    
	    System.out.println(dateFromOld);
		
		
		
	}

}
