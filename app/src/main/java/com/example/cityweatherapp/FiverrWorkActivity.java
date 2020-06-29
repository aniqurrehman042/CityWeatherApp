package com.example.cityweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FiverrWorkActivity extends AppCompatActivity {

    private LineChart lcHourlyForecasts;
    private ExpandableListView elvThisWeek;
    private ScrollView scrollView;
    private LinearLayout llExpandable;
    private ImageView ivArrow;
    private boolean expanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiverr_work);

        setScreenTheme();

        scrollView = (ScrollView) findViewById(R.id.nsv_main);

//        ((NestedScrollView) findViewById(R.id.nsv_main)).fullScroll(ScrollView.FOCUS_UP);

        setFullScreen();

        findViews();

        initLineChart();

        initExpandableList();

        setListeners();

    }

    private void setScreenTheme() {
        String theme = getIntent().getExtras().getString("Theme");
        if (theme.equals("night")) {
            ((ImageView) findViewById(R.id.iv_bg_img)).setImageResource(R.drawable.bg_high_moon);
            findViewById(R.id.ll_bg_panel).setBackground(ContextCompat.getDrawable(this, R.drawable.bg_night_round));
        }
    }

    private void setListeners() {
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expanded) {
                    ivArrow.setImageResource(R.drawable.ic_arrow_up_black);
                    llExpandable.setVisibility(View.GONE);
                    expanded = false;
                }
                else {
                    ivArrow.setImageResource(R.drawable.ic_down_arrow);
                    llExpandable.setVisibility(View.VISIBLE);
                    expanded = true;
                }

            }
        });
    }

    private void initExpandableList() {
        ExpandableListAdapter adapter = new ExpandableListAdapter();
        elvThisWeek.setAdapter(adapter);
        setExpandableListViewHeight(elvThisWeek);
        elvThisWeek.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });
    }

    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private void initLineChart() {

        List<Entry> entries = new ArrayList<Entry>();
//        List<Entry> entries1 = new ArrayList<Entry>();

        Drawable cloudDrawable = ContextCompat.getDrawable(this, R.drawable.ic_cloud_g);
        Drawable cloudSunDrawable = ContextCompat.getDrawable(this, R.drawable.ic_cloud_sun_g);
        Drawable sunDrawable = ContextCompat.getDrawable(this, R.drawable.ic_sun_grey_g);

        int color = ContextCompat.getColor(this, R.color.dark_grey);

        Date date = Calendar.getInstance().getTime();
        Date date1 = Calendar.getInstance().getTime();
        Date date2 = Calendar.getInstance().getTime();
        Date date3 = Calendar.getInstance().getTime();
        Date date4 = Calendar.getInstance().getTime();
        Date date5 = Calendar.getInstance().getTime();
        Date date6 = Calendar.getInstance().getTime();
        Date date7 = Calendar.getInstance().getTime();
        Date dateMin = Calendar.getInstance().getTime();
        Date dateMax = Calendar.getInstance().getTime();

        long referenceNumber = (long) 999900000000f;

        date.setHours(6);
        date1.setHours(9);
        date2.setHours(12);
        date3.setHours(15);
        date4.setHours(18);
        date5.setHours(21);
        date6.setHours(23);
        date7.setHours(3);
        dateMin.setHours(2);
        dateMax.setHours(24);

        // turn your data into Entry objects
        entries.add(new Entry(date7.getTime() - referenceNumber, 13, cloudDrawable));
        entries.add(new Entry(date.getTime() - referenceNumber, 15, cloudSunDrawable));
        entries.add(new Entry(date1.getTime() - referenceNumber, 10, cloudSunDrawable));
        entries.add(new Entry(date2.getTime() - referenceNumber, 21, sunDrawable));
        entries.add(new Entry(date3.getTime() - referenceNumber, 16, cloudSunDrawable));
        entries.add(new Entry(date4.getTime() - referenceNumber, 20, cloudDrawable));
        entries.add(new Entry(date5.getTime() - referenceNumber, 18, cloudDrawable));
        entries.add(new Entry(date6.getTime() - referenceNumber, 21, cloudDrawable));

        LineDataSet dataSet = new LineDataSet(entries, "");
        MPPointF offset = MPPointF.getInstance();
        offset.y = -35;
        dataSet.setIconsOffset(offset);

        dataSet.setDrawValues(true);
        dataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSet.setDrawIcons(true);
        dataSet.setColor(color);
        dataSet.setCircleColor(color);

        dataSet.setValueFormatter(new YValueFormatter());

        LineData lineData = new LineData();
        lineData.addDataSet(dataSet);
        lineData.setValueTextSize(15);

        lcHourlyForecasts.setData(lineData);
        lcHourlyForecasts.getAxisLeft().setAxisMinValue(0);
        lcHourlyForecasts.getAxisLeft().setAxisMaxValue(34);
        lcHourlyForecasts.getXAxis().setAxisMinValue(dateMin.getTime() - referenceNumber);
        lcHourlyForecasts.getXAxis().setAxisMaxValue(dateMax.getTime() - referenceNumber);
        lcHourlyForecasts.invalidate();
//        lcHourlyForecasts.setVisibleXRange(dateMin.getTime(), dateMax.getTime());

        setStyling(referenceNumber);

    }

    private void setStyling(long ref) {
        lcHourlyForecasts.setDrawGridBackground(false);
        lcHourlyForecasts.setDrawBorders(false);
        lcHourlyForecasts.getAxisLeft().setDrawGridLines(false);
        lcHourlyForecasts.getAxisRight().setDrawGridLines(false);
        lcHourlyForecasts.getAxisLeft().setDrawTopYLabelEntry(false);
        lcHourlyForecasts.getAxisLeft().setDrawGridLines(false);
        lcHourlyForecasts.getAxisRight().setDrawGridLines(false);
        lcHourlyForecasts.getAxisLeft().setDrawGridLinesBehindData(false);
        lcHourlyForecasts.getAxisRight().setDrawGridLinesBehindData(false);
        lcHourlyForecasts.getAxisLeft().setDrawAxisLine(false);
        lcHourlyForecasts.getAxisRight().setDrawAxisLine(false);
        lcHourlyForecasts.getXAxis().setDrawGridLinesBehindData(false);
        lcHourlyForecasts.getXAxis().setDrawGridLines(false);
        lcHourlyForecasts.getXAxis().setDrawAxisLine(false);
        lcHourlyForecasts.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lcHourlyForecasts.getAxisRight().setEnabled(false);
        lcHourlyForecasts.getAxisLeft().setEnabled(false);
        lcHourlyForecasts.getDescription().setEnabled(false);
        lcHourlyForecasts.getLegend().setEnabled(false);


        lcHourlyForecasts.getXAxis().setValueFormatter(new HourAxisValueFormatter(ref));
        lcHourlyForecasts.getAxisLeft().setValueFormatter(new YValueFormatter());
        lcHourlyForecasts.getAxisRight().setValueFormatter(new YValueFormatter());
        lcHourlyForecasts.zoom(1.5f, 0, 0, 0);
        lcHourlyForecasts.setScaleEnabled(false);
        lcHourlyForecasts.getAxisLeft().setDrawAxisLine(false);

        lcHourlyForecasts.getData().setHighlightEnabled(false);
    }

    private void findViews() {
        lcHourlyForecasts = findViewById(R.id.lc_hourly_forecasts);
        elvThisWeek = findViewById(R.id.elv_this_week);
        llExpandable = findViewById(R.id.ll_expandable);
        ivArrow = findViewById(R.id.iv_arrow);
    }

    private void setFullScreen() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void setExpandableListViewHeight(ExpandableListView listView) {
        try {
            ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getGroupCount(); i++) {
                View listItem = listAdapter.getGroupView(i, false, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            int height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
            if (height < 10) height = 200;
            params.height = height;
            listView.setLayoutParams(params);
            listView.requestLayout();
            scrollView.post(new Runnable() {
                public void run() {
                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
