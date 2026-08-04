package com.example.goaltracker;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class Converters {

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {

        if (list == null)
            return "";

        return String.join(",", list);
    }

    @TypeConverter
    public static ArrayList<String> toArrayList(String data) {

        if (data == null || data.isEmpty())
            return new ArrayList<>();

        return new ArrayList<>(Arrays.asList(data.split(",")));
    }

}