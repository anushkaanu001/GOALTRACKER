package com.example.goaltracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class GoalPagerAdapter extends FragmentStateAdapter {

    private final int goalId;

    public GoalPagerAdapter(@NonNull FragmentActivity activity, int goalId) {
        super(activity);
        this.goalId = goalId;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {

            case 0:
                return DailyFragment.newInstance(goalId);

            case 1:
                return WeeklyFragment.newInstance(goalId);

            case 2:
                return MonthlyFragment.newInstance(goalId);

            default:
                return DailyFragment.newInstance(goalId);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}