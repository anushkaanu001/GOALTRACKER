package com.example.goaltracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(TaskModel task);

    @Update
    void update(TaskModel task);

    @Delete
    void delete(TaskModel task);

    // All tasks of one goal
    @Query("SELECT * FROM tasks WHERE goalId=:goalId")
    List<TaskModel> getTasks(int goalId);

    // Daily tasks
    @Query("SELECT * FROM tasks WHERE goalId=:goalId AND taskType=0")
    List<TaskModel> getDailyTasks(int goalId);

    // Weekly tasks
    @Query("SELECT * FROM tasks WHERE goalId=:goalId AND taskType=1")
    List<TaskModel> getWeeklyTasks(int goalId);

    // Monthly tasks
    @Query("SELECT * FROM tasks WHERE goalId=:goalId AND taskType=2")
    List<TaskModel> getMonthlyTasks(int goalId);

}