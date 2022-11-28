package src.games.basic.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Logger {

	private static Logger instance = null;
	
	private Logger() {
	}
	
	public static synchronized Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		
		return instance;
	}
	
	
	public void log(String message) {
		Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//		Date date = new Date();
		Date date = gmtCal.getTime();
		String timeStamp;
		
		timeStamp = date.toString();
		System.out.println( timeStamp + ": " + message );
		
//		timeStamp = (new SimpleDateFormat()).format( date );
//		System.out.println( timeStamp + ": " + message );
		
		System.out.printf( "%tF %tT %s %n",  gmtCal, gmtCal, message);
	}

	
}
