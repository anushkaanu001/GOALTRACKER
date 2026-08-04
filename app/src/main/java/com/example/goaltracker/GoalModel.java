package com.example.goaltracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Ignore;
import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "goals")
public class GoalModel implements Serializable {

    // =========================
    // Goal Information
    // =========================
    @PrimaryKey(autoGenerate = true)
    private int goalId;
    private String goalName;
    private String description;
    private String category;
    private int goalIcon;
    private int priority;

    // =========================
    // Dates
    // =========================
    private String startDate;
    private String deadline;
    private boolean noDeadline;
    private String estimatedTime;

    // =========================
    // Reminder
    // =========================
    private boolean notificationEnabled;
    private String reminderFrequency;
    private String reminderTime;
    private ArrayList<String> reminderDays;

    // =========================
    // Goal Progress
    // =========================
    private int totalTasks;
    private int completedTasks;

    // =========================
    // Goal Status
    // =========================
    public static final int IN_PROGRESS = 0;
    public static final int COMPLETED = 1;
    public static final int PAUSED = 2;

    private int status;
    private int streak;

    // =========================
    // Constructor
    // =========================

    public GoalModel(
            String goalName,
            String description,
            String category,
            int goalIcon,
            int priority,
            String startDate,
            String deadline,
            boolean noDeadline,
            String estimatedTime,
            boolean notificationEnabled,
            String reminderFrequency,
            String reminderTime,
            ArrayList<String> reminderDays,
            int totalTasks,
            int completedTasks,
            int status,
            int streak) {

        this.goalName = goalName;
        this.description = description;
        this.category = category;
        this.goalIcon = goalIcon;
        this.priority = priority;
        this.startDate = startDate;
        this.deadline = deadline;
        this.noDeadline = noDeadline;
        this.estimatedTime = estimatedTime;
        this.notificationEnabled = notificationEnabled;
        this.reminderFrequency = reminderFrequency;
        this.reminderTime = reminderTime;
        this.reminderDays = reminderDays;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.status = status;
        this.streak = streak;
    }

    // =========================
    // Getters & Setters
    // =========================

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getGoalIcon() {
        return goalIcon;
    }

    public void setGoalIcon(int goalIcon) {
        this.goalIcon = goalIcon;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isNoDeadline() {
        return noDeadline;
    }

    public void setNoDeadline(boolean noDeadline) {
        this.noDeadline = noDeadline;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public boolean isNotificationEnabled() {
        return notificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    public String getReminderFrequency() {
        return reminderFrequency;
    }

    public void setReminderFrequency(String reminderFrequency) {
        this.reminderFrequency = reminderFrequency;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public ArrayList<String> getReminderDays() {
        return reminderDays;
    }

    public void setReminderDays(ArrayList<String> reminderDays) {
        this.reminderDays = reminderDays;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    // =========================
    // Helper Methods
    // =========================

    public int getProgressPercentage() {

        if (totalTasks == 0)
            return 0;

        return (completedTasks * 100) / totalTasks;
    }

    public int getRemainingTasks() {
        return totalTasks - completedTasks;
    }

}
