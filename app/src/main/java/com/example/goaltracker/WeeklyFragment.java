package com.example.goaltracker;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeeklyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeeklyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private int goalId;
    TaskDao taskDao;

    ArrayList<TaskModel> taskList;

    TaskAdapter adapter;

    GoalDatabase database;

    RecyclerView rvDaily;

    TextView addTask;

    public WeeklyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameter
     * @return A new instance of fragment WeeklyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeeklyFragment newInstance(int goalId) {

        WeeklyFragment fragment = new WeeklyFragment();

        Bundle bundle = new Bundle();

        bundle.putInt("goalId", goalId);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            goalId = getArguments().getInt("goalId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weekly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        database = androidx.room.Room.databaseBuilder(
                        requireContext(),
                        GoalDatabase.class,
                        "GoalDatabase"
                )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

// FIRST initialize taskDao
        taskDao = database.taskDao();

// THEN RecyclerView
        rvDaily = view.findViewById(R.id.rvWeekly);

        taskList = new ArrayList<>();

        taskList.addAll(taskDao.getWeeklyTasks(goalId));

        adapter = new TaskAdapter(taskList, null);

        rvDaily.setLayoutManager(
                new androidx.recyclerview.widget.LinearLayoutManager(getContext())
        );

        rvDaily.setAdapter(adapter);

        addTask = view.findViewById(R.id.addTask);

        addTask.setOnClickListener(v -> {

            Dialog dialog = new Dialog(requireContext());

            dialog.setContentView(R.layout.dialog_add_task);

            EditText edtTask = dialog.findViewById(R.id.edtTask);
            Button btnSave = dialog.findViewById(R.id.btnSave);
            Button btnCancel = dialog.findViewById(R.id.btnCancel);

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String taskName = edtTask.getText().toString().trim();

                    if (taskName.isEmpty()) {
                        edtTask.setError("Enter task name");
                        return;
                    }

                    TaskModel task = new TaskModel();

                    task.setGoalId(goalId);

                    task.setTaskName(taskName);

                    task.setTaskType(TaskModel. WEEKLY);

                    // 👇 ADD THESE LINES HERE
                    task.setStatus(TaskModel.PENDING);

                    task.setCreatedDate(
                            new java.text.SimpleDateFormat(
                                    "dd MMM yyyy",
                                    java.util.Locale.getDefault()
                            ).format(new java.util.Date())
                    );

                    // Save task
                    taskDao.insert(task);

                    // Refresh RecyclerView
                    taskList.clear();
                    taskList.addAll(taskDao.getWeeklyTasks(goalId));
                    adapter.notifyDataSetChanged();

                    dialog.dismiss();
                }
            });
            dialog.show();

        });

    }
}