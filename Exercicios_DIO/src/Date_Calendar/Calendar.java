package Exercicios_DIO.src.Date_Calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Calendar {
    public static void main(String[] args) {

        var calendar = java.util.Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss Z");
        System.out.println(calendar.get(java.util.Calendar.YEAR));
        System.out.println(calendar.get(java.util.Calendar.MONTH));
        System.out.println(calendar.get(java.util.Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(java.util.Calendar.HOUR));
        System.out.println(calendar.get(java.util.Calendar.DAY_OF_YEAR));
        System.out.println(calendar.get(java.util.Calendar.DAY_OF_WEEK));
        System.out.println(formatter.format(calendar.getTime()));


    }
}
