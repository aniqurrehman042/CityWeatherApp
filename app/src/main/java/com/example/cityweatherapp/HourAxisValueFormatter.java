package com.example.cityweatherapp;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HourAxisValueFormatter extends ValueFormatter {

    private DateFormat mDataFormat;
    private Date mDate;
    private long ref;

    public HourAxisValueFormatter(long ref) {
        this.mDataFormat = new SimpleDateFormat("hh aa", Locale.ENGLISH);
        this.mDate = new Date();
        this.ref = ref;
    }

    @Override
    public String getFormattedValue(float value) {
        String date = getDate((long) value + ref, "hh aa");
        if (!(date.charAt(0) == '0'))
            return date;
        else{
            char[] chars = new char[date.length() - 1];
            for (int i = 1; i < date.length(); i++) {
                chars[i - 1] = date.charAt(i);
            }
            return String.valueOf(chars);
        }
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
