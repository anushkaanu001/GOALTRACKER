package com.example.goaltracker;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TaskModel> taskList;
    private OnTaskClickListener listener;

    public interface OnTaskClickListener {
        void onTaskChecked(TaskModel task, boolean isChecked);
        void onMenuClicked(TaskModel task, View view);
    }

    public TaskAdapter(List<TaskModel> taskList,
                       OnTaskClickListener listener) {

        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder,
                                 int position) {

        TaskModel task = taskList.get(position);

        holder.txtTaskName.setText(task.getTaskName());

        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setChecked(task.isCompleted());

        if(task.isCompleted()){

            holder.txtTaskName.setPaintFlags(
                    holder.txtTaskName.getPaintFlags()
                            | Paint.STRIKE_THRU_TEXT_FLAG
            );

            holder.txtTaskName.setAlpha(0.5f);

        }else{

            holder.txtTaskName.setPaintFlags(
                    holder.txtTaskName.getPaintFlags()
                            & (~Paint.STRIKE_THRU_TEXT_FLAG)
            );

            holder.txtTaskName.setAlpha(1f);

        }

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(listener != null)
                listener.onTaskChecked(task,isChecked);

        });

        holder.menu.setOnClickListener(v -> {

            if(listener!=null)
                listener.onMenuClicked(task,v);

        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void updateList(List<TaskModel> newList){

        taskList = newList;

        notifyDataSetChanged();

    }

    static class TaskViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        TextView txtTaskName;
        ImageView menu;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkboxTask);

            txtTaskName = itemView.findViewById(R.id.txtTaskName);

            menu = itemView.findViewById(R.id.imgMenu);

        }
    }
}