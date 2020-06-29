package com.example.cityweatherapp;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class YValueFormatter extends ValueFormatter {

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        int intValue = Integer.valueOf(String.valueOf(value));
        return intValue + "\u00B0C";
    }

    @Override
    public String getFormattedValue(float value) {
        int intValue = (int) value;
        return intValue + "\u00B0C";
    }
}
