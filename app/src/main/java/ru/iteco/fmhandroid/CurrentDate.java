package ru.iteco.fmhandroid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CurrentDate {
    static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static String currentDate = dateFormat.format(new Date());
    static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    public static String currentTime = timeFormat.format(new Date());
}
