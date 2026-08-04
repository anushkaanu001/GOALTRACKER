package com.example.goaltracker;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {
                GoalModel.class,
                TaskModel.class
        },
        version = 2,
        exportSchema = false
)

@TypeConverters(Converters.class)
public abstract class GoalDatabase extends RoomDatabase {

    public abstract GoalDao goalDao();
    public abstract TaskDao taskDao();

}