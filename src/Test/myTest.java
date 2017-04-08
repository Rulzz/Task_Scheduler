package Test;

	
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;

public class myTest {
	
	public static void main(String[] args) throws ParseException {
		/*String myTime = "14:10";
		 SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		 Date d = df.parse(myTime); 
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(d);
		 cal.add(Calendar.MINUTE, 10);
		 String newTime = df.format(cal.getTime());
		 
		 System.out.println(newTime);*/
		 
		String dateString = "10-01-2010 12:10";
		 SimpleDateFormat dayTimeFormat = new SimpleDateFormat("dd-M-yyyy HH:mm");
		 Date myDate = dayTimeFormat.parse(dateString);
		 Timestamp myDateTime = new Timestamp(myDate.getTime());
		 System.out.println(myDateTime);
		 
		 SimpleDateFormat dayFormat = new SimpleDateFormat("dd-M-yyyy");
		 Date myDay = DateUtils.truncate(myDate, Calendar.DATE);
		 Timestamp myDayTime = new Timestamp(myDay.getTime());
		 System.out.println(myDayTime);
		 
        
		 Date myDay2 = dayFormat.parse(myDateTime.toString());
		 Timestamp myDayTime2 = new Timestamp(myDay2.getTime());
		 System.out.println(myDayTime2);
		 Date myDate3 = DateUtils.addDays(myDate, -1);
		 System.out.println(myDate3);
		
		 String startDateStr = "10-01-2010 9:10";
		 Date startDate = dayTimeFormat.parse(startDateStr);
		 String endDateStr = "10-01-2010 21:20";
		 Date endDate = dayTimeFormat.parse(endDateStr);
		 
		 long duration  = endDate.getTime() - startDate.getTime();

		 long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		 long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
		 System.out.println(diffInMinutes%60);
		 System.out.println(diffInHours);
		 
		 SimpleDateFormat hrFormat = new SimpleDateFormat("HH:mm");
		 String time = hrFormat.format(endDate);
		 
		 System.out.println(time);
		 
	}

}
