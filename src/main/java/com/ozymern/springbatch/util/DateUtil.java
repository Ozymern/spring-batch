package com.ozymern.springbatch.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	private static final String simpleDateFormatCollection = "yyyyMMdd";
	private static final String simpleDateFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String timeZone = "America/Santiago";
	
    public static String formatCollectionDate() {
    	
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        DateFormat df = new SimpleDateFormat(simpleDateFormatCollection);
        df.setTimeZone(tz);
        
        return df.format(new Date());
    }
    
    public static String currentDateAddHoursMinutesSecunds(Integer hours, Integer minutes, Integer secunds) {

    	Calendar calendarStart = Calendar.getInstance();
    	calendarStart.set(Calendar.HOUR_OF_DAY, hours);
    	calendarStart.set(Calendar.MINUTE, minutes);
    	calendarStart.set(Calendar.SECOND, secunds);

    	SimpleDateFormat format = new SimpleDateFormat(simpleDateFormat);

    	return format.format(calendarStart.getTime());
    }
}
