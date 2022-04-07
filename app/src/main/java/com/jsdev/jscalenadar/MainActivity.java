package com.jsdev.jscalenadar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.onItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();

        selectedData = LocalDate.now();

        setMouthView();

    }

    private void setMouthView() {

        monthYearText.setText(mouthYearFromData(selectedData));

        ArrayList<String> dayInMonth = dayInMouthArray(selectedData);

        CalendarAdapter calendarAdapter = new CalendarAdapter(dayInMonth, this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);

        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }

    private ArrayList<String> dayInMouthArray(LocalDate data) {

        ArrayList<String> dayInMonthArray = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(data);

        int dayInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedData.withDayOfMonth(1);

        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++ ) {

            if (i <= dayOfWeek || i > dayInMonth + dayOfWeek) {

                dayInMonthArray.add("");

            } else {

                dayInMonthArray.add(String.valueOf(i - dayOfWeek));

            }

        }

        return dayInMonthArray;
    }

    public String mouthYearFromData(LocalDate data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");

        return data.format(formatter);

    }

    private void initWidgets() {

        monthYearText = findViewById(R.id.monthYearTV);
        calendarRecyclerView =  findViewById(R.id.calendarRecyclerView);

    }

    public void previousMouthAction(View view) {

        selectedData = selectedData.minusMonths(1);
        setMouthView();

    }

    public void nextMouthAction(View view) {

        selectedData = selectedData.plusMonths(1);
        setMouthView();

    }

    @Override
    public void itemClick(int position, String dayText) {

        if (dayText.equals("")) {

            String message = "Selected Date" + dayText + " " + mouthYearFromData(selectedData);

            Toast.makeText(this, message,Toast.LENGTH_LONG).show();

        }

    }
}