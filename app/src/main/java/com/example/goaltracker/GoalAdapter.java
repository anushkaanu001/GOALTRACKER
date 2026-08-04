package com.example.goaltracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalViewHolder> {

    private Context context;
    private ArrayList<GoalModel> goalList;

    public GoalAdapter(Context context, ArrayList<GoalModel> goalList) {
        this.context = context;
        this.goalList = goalList;
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goal_card, parent, false);

        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {

        GoalModel goal = goalList.get(position);

        holder.txtGoalName.setText(goal.getGoalName());
        holder.txtCategory.setText("#" + goal.getCategory());

        holder.imgGoalIcon.setImageResource(goal.getGoalIcon());

        holder.txtStarted.setText(goal.getStartDate());

        if(goal.isNoDeadline()){
            holder.txtDeadline.setText("No Deadline");
        }else{
            holder.txtDeadline.setText(goal.getDeadline());
        }

        holder.txtTasks.setText(String.valueOf(goal.getTotalTasks()));
        holder.txtCompleted.setText(String.valueOf(goal.getCompletedTasks()));
        holder.txtStreak.setText(goal.getStreak() + " Days");

        holder.txtReminder.setText(
                goal.getReminderFrequency() + " • " + goal.getReminderTime());

        holder.txtEstimate.setText(goal.getEstimatedTime());

        int progress = goal.getProgressPercentage();

        holder.progressGoal.setProgress(progress);
        holder.txtPercentage.setText(progress + "%");

        switch (goal.getStatus()) {

            case GoalModel.IN_PROGRESS:
                holder.txtStatus.setText("🟢 In Progress");
                break;

            case GoalModel.COMPLETED:
                holder.txtStatus.setText("✅ Completed");
                break;

            case GoalModel.PAUSED:
                holder.txtStatus.setText("⏸ Paused");
                break;
        }

        // Priority Stars
        for(int i=0;i<5;i++){

            if(i < goal.getPriority()){
                holder.priorityStars[i].setImageResource(R.drawable.selectedstar); // Filled star
            }else{
                holder.priorityStars[i].setImageResource(R.drawable.group_1); // Change this to empty star drawable later
            }
        }

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, GoalDetails.class);

            intent.putExtra("goal", goal);

            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }

    static class GoalViewHolder extends RecyclerView.ViewHolder{

        TextView txtGoalName,txtCategory,txtStatus,
                txtDeadline,txtStarted,txtTasks,
                txtCompleted,txtStreak,
                txtReminder,txtEstimate,
                txtPercentage;

        ImageView imgGoalIcon;

        ImageView[] priorityStars = new ImageView[5];

        ProgressBar progressGoal;

        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);

            txtGoalName = itemView.findViewById(R.id.txtGoalName);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtDeadline = itemView.findViewById(R.id.txtDeadline);
            txtStarted = itemView.findViewById(R.id.txtStarted);
            txtTasks = itemView.findViewById(R.id.txtTasks);
            txtCompleted = itemView.findViewById(R.id.txtCompleted);
            txtStreak = itemView.findViewById(R.id.txtStreak);
            txtReminder = itemView.findViewById(R.id.txtReminder);
            txtEstimate = itemView.findViewById(R.id.txtEstimate);
            txtPercentage = itemView.findViewById(R.id.txtPercentage);

            imgGoalIcon = itemView.findViewById(R.id.imgGoalIcon);

            progressGoal = itemView.findViewById(R.id.progressGoal);

            priorityStars[0] = itemView.findViewById(R.id.starie1);
            priorityStars[1] = itemView.findViewById(R.id.starie2);
            priorityStars[2] = itemView.findViewById(R.id.starie3);
            priorityStars[3] = itemView.findViewById(R.id.starie4);
            priorityStars[4] = itemView.findViewById(R.id.starie5);
        }
    }
}