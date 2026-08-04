package com.example.goaltracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GoalDao {

    @Insert
    void insert(GoalModel goal);

    @Update
    void update(GoalModel goal);

    @Delete
    void delete(GoalModel goal);

    @Query("SELECT * FROM goals")
    List<GoalModel> getAllGoals();

}