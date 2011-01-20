package com.soebes.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateDifferenceExample {
    public static final long MILLIS_PER_SECOND = 1000;

    public static final long SECONDS_PER_MINUTE = 60;
    public static final long MINUTES_PER_HOUR = 60;
    public static final long SECONDS_PER_HOUR = 3600;
    public static final long SECONDS_PER_DAY = 86400;

    public static final long DAYS_PER_YEAR = 365;
    public static final long DAYS_PER_MONTH = 30;
    public static final long HOURS_PER_DAY = 24;
    public static final long MINUTES_PER_DAY = 1440;

    public static Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date result = null;

        try {
            result = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public enum Dates {
        YEARS(DAYS_PER_YEAR * HOURS_PER_DAY * SECONDS_PER_DAY
                * MILLIS_PER_SECOND), MONTHS(DAYS_PER_MONTH * HOURS_PER_DAY
                * SECONDS_PER_DAY * MILLIS_PER_SECOND), DAYS(SECONDS_PER_DAY
                * MILLIS_PER_SECOND), HOURS(SECONDS_PER_HOUR
                * MILLIS_PER_SECOND), MINUTES(SECONDS_PER_MINUTE
                * MILLIS_PER_SECOND), SECONDS(MILLIS_PER_SECOND);

        private long divider;

        Dates(long divider) {
            this.divider = divider;
        }

        public long getDivider() {
            return divider;
        }

    }

    public static void delta(String startDate, String endDate) {
        // Creates two calendars instances
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Set the date for both of the calendar instance
        cal1.setTime(parseDate(startDate));
        cal2.setTime(parseDate(endDate));

        // Get the represented date in milliseconds
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();

        long diff = milis2 - milis1;

        System.out.print("Delta: ");
        for (Dates item : Dates.values()) {
            long result = diff / item.getDivider();
            if (result == 0) {
                continue;
            }
            System.out.print(result + " " + item.name() + " ");
            diff -= result * item.getDivider();
        }
        System.out.println("");
    }

    public static void diff() {

        Calendar gregCal1 = GregorianCalendar.getInstance();
        gregCal1.setTime(parseDate("12.04.2010 21:26:00"));

        Calendar gregCal2 = GregorianCalendar.getInstance();
        gregCal2.setTime(parseDate("25.04.2010 14:05:02"));

        gregCal2.add(Calendar.SECOND, -gregCal1.get(Calendar.SECOND));
        gregCal2.add(Calendar.MINUTE, -gregCal1.get(Calendar.MINUTE));
        gregCal2.add(Calendar.HOUR_OF_DAY, -gregCal1.get(Calendar.HOUR_OF_DAY));
        gregCal2.add(Calendar.DAY_OF_MONTH,
                -gregCal1.get(Calendar.DAY_OF_MONTH));
        gregCal2.add(Calendar.MONTH, -gregCal1.get(Calendar.MONTH));
        gregCal2.add(Calendar.YEAR, -gregCal1.get(Calendar.YEAR));
        System.out.println("result: " + gregCal2.toString());
        System.out.println(" seconds: " + gregCal2.get(Calendar.SECOND));
        System.out.println(" minutes: " + gregCal2.get(Calendar.MINUTE));
        System.out.println(" hours: " + gregCal2.get(Calendar.HOUR_OF_DAY));
        System.out.println(" days: " + gregCal2.get(Calendar.DAY_OF_MONTH));
        System.out.println(" months: " + gregCal2.get(Calendar.MONTH));
        System.out.println(" years: " + gregCal2.get(Calendar.YEAR));
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args) {
        delta("12.04.2010 21:26:00", "25.04.2010 14:05:00");
        diff();
        // Creates two calendars instances
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Set the date for both of the calendar instance
        cal1.setTime(parseDate("22.04.2010 12:04:00"));
        cal2.setTime(parseDate("22.04.2010 12:05:05"));

        // Get the represented date in milliseconds
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = milis2 - milis1;

        // Calculate difference in seconds
        long diffSeconds = diff / 1000;

        // Calculate difference in minutes
        long diffMinutes = diff / (60 * 1000);

        // Calculate difference in hours
        long diffHours = diff / (60 * 60 * 1000);

        // Calculate difference in days
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.println("In milliseconds: " + diff + " milliseconds.");
        System.out.println("In seconds: " + diffSeconds + " seconds.");
        System.out.println("In minutes: " + diffMinutes + " minutes.");
        System.out.println("In hours: " + diffHours + " hours.");
        System.out.println("In days: " + diffDays + " days.");
    }

}
