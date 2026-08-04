package com.example.goaltracker;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class GoalDetails extends AppCompatActivity {

    private GoalModel goal;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_goal_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom);

            return insets;
        });

        // Receive selected goal

        goal = (GoalModel) getIntent().getSerializableExtra("goal");

        tabLayout = findViewById(R.id.tabLayout);

        viewPager = findViewById(R.id.viewPager);

        GoalPagerAdapter adapter =
                new GoalPagerAdapter(this, goal.getGoalId());

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {

                    if(position==0)
                        tab.setText("Daily");

                    else if(position==1)
                        tab.setText("Weekly");

                    else
                        tab.setText("Monthly");

                }).attach();

    }
}