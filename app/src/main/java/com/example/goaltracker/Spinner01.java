package com.example.goaltracker;


import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Spinner01 {

    public static void setSpinner(Context context, Spinner spinner, int arrayId) {

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        context,
                        arrayId,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }
}
