package com.example.goaltracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tasks")
public class TaskModel implements Serializable {

    // =========================
    // Task Information
    // =========================

    @PrimaryKey(autoGenerate = true)
    private int taskId;

    // Goal to which this task belongs
    private int goalId;

    private String taskName;

    // =========================
    // Task Type
    // =========================

    public static final int DAILY = 0;
    public static final int WEEKLY = 1;
    public static final int MONTHLY = 2;

    private int taskType;

    // =========================
    // Task Status
    // =========================

    public static final int PENDING = 0;
    public static final int COMPLETED = 1;
    public static final int RESCHEDULED = 2;
    public static final int DROPPED = 3;

    private int status;

    // =========================
    // Dates
    // =========================

    private String createdDate;
    private String completedDate;
    private String rescheduledDate;

    // =========================
    // Constructor
    // =========================

    public TaskModel(int goalId,
                     String taskName,
                     String createdDate) {

        this.goalId = goalId;
        this.taskName = taskName;
        this.createdDate = createdDate;

        // Defaults
        this.taskType = DAILY;      // Will be changed automatically
        this.status = PENDING;
        this.completedDate = "";
        this.rescheduledDate = "";
    }

    // Empty constructor for Room
    public TaskModel() {
    }

    // =========================
    // Getters & Setters
    // =========================

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public String getRescheduledDate() {
        return rescheduledDate;
    }

    public void setRescheduledDate(String rescheduledDate) {
        this.rescheduledDate = rescheduledDate;
    }

    // =========================
    // Helper Methods
    // =========================

    public boolean isCompleted() {
        return status == COMPLETED;
    }

    public boolean isPending() {
        return status == PENDING;
    }

    public boolean isDropped() {
        return status == DROPPED;
    }

    public boolean isRescheduled() {
        return status == RESCHEDULED;
    }
}