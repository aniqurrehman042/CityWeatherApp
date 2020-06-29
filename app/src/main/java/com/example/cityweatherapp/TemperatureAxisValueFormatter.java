package com.example.cityweatherapp;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TemperatureAxisValueFormatter extends ValueFormatter {
    private DateFormat mDataFormat;
    private Date mDate;

    public TemperatureAxisValueFormatter() {
        this.mDataFormat = new SimpleDateFormat("hh aa", Locale.ENGLISH);
        this.mDate = new Date();
    }

    @Override
    public String getFormattedValue(float value) {
        int intValue = (int) value;
        return intValue + "\u00B0C";
    }

    @Override
    public String getPointLabel(Entry entry) {
        int intValue = (int) entry.getY();
        return intValue + "\u00B0C";
    }


}
