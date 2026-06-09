package demo.basics;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Stream;

public class DemoTemporal {

    @Test
    void demoDate(){
        // Java 1.0
        Date date = new Date(); // date + time + tz
        System.out.println(date); // Tue Jun 09 10:26:06 CEST 2026
    }

    @Test
    void demoCalendar(){
        // Java 1.1
        Calendar date = Calendar.getInstance();
        System.out.println(date); // date + time + timezone
        // java.util.GregorianCalendar[time=1780993739489,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Europe/Paris",offset=3600000,dstSavings=3600000,useDaylight=true,transitions=310,lastRule=java.util.SimpleTimeZone[id=Europe/Paris,offset=3600000,dstSavings=3600000,useDaylight=true,startYear=0,startMode=2,startMonth=2,startDay=-1,startDayOfWeek=1,startTime=3600000,startTimeMode=2,endMode=2,endMonth=9,endDay=-1,endDayOfWeek=1,endTime=3600000,endTimeMode=2]],firstDayOfWeek=2,minimalDaysInFirstWeek=4,ERA=1,YEAR=2026,MONTH=5,WEEK_OF_YEAR=24,WEEK_OF_MONTH=2,DAY_OF_MONTH=9,DAY_OF_YEAR=160,DAY_OF_WEEK=3,DAY_OF_WEEK_IN_MONTH=2,AM_PM=0,HOUR=10,HOUR_OF_DAY=10,MINUTE=28,SECOND=59,MILLISECOND=489,ZONE_OFFSET=3600000,DST_OFFSET=3600000]
        System.out.println(date.getClass()); // class java.util.GregorianCalendar
        System.out.println(date.get(Calendar.YEAR));
    }

    @Test
    void demoJavaTime(){
        // Java 8 : package java.time
        // conformity ISO 8601
        // IANA dictionary with timezones : Europe/Paris
        LocalDate d1 = LocalDate.now();
        LocalDate d2 = LocalDate.now(ZoneId.of("Australia/Sydney"));
        LocalDate d3 = LocalDate.now(ZoneId.of("Pacific/Pago_Pago"));
        LocalTime t1 = LocalTime.now();
        LocalDateTime dt1 = LocalDateTime.now();
        ZonedDateTime zdt1 = ZonedDateTime.now();
        ZonedDateTime zdt2 = ZonedDateTime.now(ZoneId.of("Australia/Sydney"));
        Stream.of(d1, d2, d3, t1, dt1, zdt1, zdt2)
                .forEach(System.out::println);

        LocalDateTime dt2 = dt1.minusMinutes(10);
        System.out.println(dt2);
        LocalDateTime dt3 = LocalDateTime.of(2026, 6, 12, 17, 0);
        System.out.println(dt3);

        long delta = dt1.until(dt3, ChronoUnit.HOURS);
        System.out.println(delta);
    }

    @Test
    void demoFormatGetter(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        DateTimeFormatter formatISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatLocal = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        DateTimeFormatter formatFr = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"); // add hour, minute, seconds
        // formatFrLit with day and month with letters
        DateTimeFormatter formatFrLit = DateTimeFormatter.ofPattern("EEEE dd MMM yyyy");
        System.out.println(localDateTime.format(formatISO));
        System.out.println(zonedDateTime.format(formatLocal));
        System.out.println(localDateTime.format(formatFr));
        System.out.println(localDateTime.format(formatFrLit));

        String dateTimeStr1 = "2026-06-12T17:30";
        LocalDateTime dateTimeRead = LocalDateTime.parse(dateTimeStr1); // ISO
        System.out.println(dateTimeRead);

        String dateTimeStr2 = "17:30 12/06/2026";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime dateTimeRead2 = LocalDateTime.parse(dateTimeStr2, formatter);
        System.out.println(dateTimeRead2);

        System.out.println("Year: " + dateTimeRead.getYear()); // int
        System.out.println("Month: " + dateTimeRead.getMonth()); // Month
        System.out.println("Month: " + dateTimeRead.getMonthValue()); // int
    }
}







