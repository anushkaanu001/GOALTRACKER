package com.example.goaltracker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.TextView;

import java.util.Calendar;

public class DatePicker01 {

    public static void show(Context context, TextView textView) {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(
                context,
                (view, year, month, dayOfMonth) -> {
                    String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    textView.setText(date);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        dialog.show();
    }
}
