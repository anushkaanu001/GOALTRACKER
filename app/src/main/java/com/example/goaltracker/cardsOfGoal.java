package com.example.goaltracker;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class cardsOfGoal extends AppCompatActivity {

    Button Btn;

    RecyclerView recyclerView;

    ArrayList<GoalModel> goalList;

    GoalAdapter adapter;

    GoalDatabase database;
    GoalDao dao;

    TaskDao taskDao;

// --------------------
// Dialog Views
// --------------------

    EditText edtGoalName;
    EditText edtDescription;
    TextView edtEstimatedTime;

    TextView showDate;

    Spinner spinnerFrequency;
    Spinner spinnerAmPm;

    Switch switchDeadline;
    Switch switchNotification;

    Button btnLetsGo;

    LinearLayout txtDeadline;
    LinearLayout addgoallogo;

// --------------------
// Rating
// --------------------

    RatingBar ratingBar;

    ImageView star1;
    ImageView star2;
    ImageView star3;
    ImageView star4;
    ImageView star5;

// --------------------
// Selected Values
// --------------------

    String category = "Other";

    int selectedIcon = R.drawable.group_1;

    ArrayList<String> reminderDays = new ArrayList<>();

    String reminderTime = "";

    String currentDate = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maingoalcardscreen);

        currentDate = new SimpleDateFormat(
                "dd MMM yyyy",
                Locale.getDefault()
        ).format(new Date());

        recyclerView = findViewById(R.id.recyclerGoals);

        database = Room.databaseBuilder(
                        getApplicationContext(),
                        GoalDatabase.class,
                        "GoalDatabase"
                )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        dao = database.goalDao();
        taskDao = database.taskDao();

        dao = database.goalDao();

        goalList = new ArrayList<>(dao.getAllGoals());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GoalAdapter(this, goalList);

        recyclerView.setAdapter(adapter);

        Log.d("GOAL", "Items = " + goalList.size());

        addgoallogo=findViewById(R.id.addgoallogo01);
        addgoallogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "Other";

                selectedIcon = R.drawable.group_1;

                reminderDays.clear();

                reminderTime = "";
                Dialog dialog = new Dialog(cardsOfGoal.this);
                dialog.setContentView(R.layout.activity_add_goal);

//====================
// EditTexts
//====================

                edtGoalName = dialog.findViewById(R.id.editTextText);
                edtDescription = dialog.findViewById(R.id.editTextText4);
                edtEstimatedTime = dialog.findViewById(R.id.edtEstimatedTime);

//====================
// TextViews
//====================

                showDate = dialog.findViewById(R.id.showDate);

//====================
// Switches
//====================

                switchDeadline = dialog.findViewById(R.id.switchDeadline);
                switchNotification = dialog.findViewById(R.id.switch1);

//====================
// Spinners
//====================

                spinnerFrequency = dialog.findViewById(R.id.spinnerFrequency);
                spinnerAmPm = dialog.findViewById(R.id.spinnerAmPm);

//====================
// Buttons
//====================

                Btn = dialog.findViewById(R.id.button2);

//====================
// Date Picker
//====================

                txtDeadline = dialog.findViewById(R.id.txtDeadline);

                DatePicker01 datePicker = new DatePicker01();

                txtDeadline.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       datePicker.show(dialog.getContext(), showDate);
                                                   }
                                               }
                );

//====================
// Rating Stars
//====================

                star1 = dialog.findViewById(R.id.star1);
                star2 = dialog.findViewById(R.id.star2);
                star3 = dialog.findViewById(R.id.star3);
                star4 = dialog.findViewById(R.id.star4);
                star5 = dialog.findViewById(R.id.star5);

                ratingBar = new RatingBar(star1, star2, star3, star4, star5);

                star1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ratingBar.setRating(1);
                    }
                });
                star2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ratingBar.setRating(2);
                    }
                });
                star3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ratingBar.setRating(3);
                    }
                });
                star4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ratingBar.setRating(4);
                    }
                });
                star5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ratingBar.setRating(5);
                    }
                });

//====================
// Reminder Day Buttons
//====================

                AppCompatButton btnMon = dialog.findViewById(R.id.btnMon);
                AppCompatButton btnTue = dialog.findViewById(R.id.btnTue);
                AppCompatButton btnWed = dialog.findViewById(R.id.btnWed);
                AppCompatButton btnThu = dialog.findViewById(R.id.btnThu);
                AppCompatButton btnFri = dialog.findViewById(R.id.btnFri);
                AppCompatButton btnSat = dialog.findViewById(R.id.btnSat);
                AppCompatButton btnSun = dialog.findViewById(R.id.btnSun);

//====================
// Category TextViews
//====================

                TextView txtStudy = dialog.findViewById(R.id.textView18);
                TextView txtCoding = dialog.findViewById(R.id.textView19);
                TextView txtWork = dialog.findViewById(R.id.textView20);

                TextView txtFinance = dialog.findViewById(R.id.textView17);
                TextView txtHealth = dialog.findViewById(R.id.textView21);
                TextView txtFitness = dialog.findViewById(R.id.textView22);

                TextView txtTravel = dialog.findViewById(R.id.textView16);
                TextView txtHobby = dialog.findViewById(R.id.textView23);
                TextView txtPersonalGrowth = dialog.findViewById(R.id.textView24);

                TextView txtHome = dialog.findViewById(R.id.textView15);
                TextView txtSocial = dialog.findViewById(R.id.textView14);
                TextView txtOther = dialog.findViewById(R.id.textView13);

                // ====================
// Category Selection
// ====================

                txtStudy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Study";
                    }
                });

                txtCoding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Coding";
                    }
                });

                txtWork.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Work";
                    }
                });

                txtFinance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Finance";
                    }
                });

                txtHealth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Health";
                    }
                });

                txtFitness.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Fitness";
                    }
                });

                txtTravel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Travel";
                    }
                });

                txtHobby.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Hobby";
                    }
                });

                txtPersonalGrowth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Personal Growth";
                    }
                });

                txtHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Home";
                    }
                });

                txtSocial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Social";
                    }
                });

                txtOther.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "Other";
                    }
                });

//====================
// Goal Icons
//====================

                ImageView img1 = dialog.findViewById(R.id.imageView7);
                ImageView img2 = dialog.findViewById(R.id.imageView9);
                ImageView img3 = dialog.findViewById(R.id.imageView10);
                ImageView img4 = dialog.findViewById(R.id.imageView11);
                ImageView img5 = dialog.findViewById(R.id.imageView100);

                ImageView img6 = dialog.findViewById(R.id.imageView12);
                ImageView img7 = dialog.findViewById(R.id.imageView13);
                ImageView img8 = dialog.findViewById(R.id.imageView14);
                ImageView img9 = dialog.findViewById(R.id.imageView22);
                ImageView img10 = dialog.findViewById(R.id.imageView81);

                ImageView img11 = dialog.findViewById(R.id.imageView6);
                ImageView img12 = dialog.findViewById(R.id.imageView);
                ImageView img13 = dialog.findViewById(R.id.imageView4);
                ImageView img14 = dialog.findViewById(R.id.imageView2);
                ImageView img15 = dialog.findViewById(R.id.imageView85);

                // ==========================

                // ==========================
// Goal Icon Selection
// ==========================

                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = android.R.drawable.btn_star_big_on;
                    }
                });

                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = R.drawable.group_1;
                    }
                });

                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = android.R.drawable.btn_star;
                    }
                });

                img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = R.drawable.ic_launcher_foreground;
                    }
                });

                img5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = android.R.drawable.btn_star_big_on;
                    }
                });

                img6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = android.R.drawable.btn_star_big_on;
                    }
                });

                img7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = R.drawable.group_1;
                    }
                });

                img8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = android.R.drawable.btn_star;
                    }
                });

                img9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = R.drawable.ic_launcher_foreground;
                    }
                });

                img10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = android.R.drawable.btn_star_big_on;
                    }
                });

                img11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = android.R.drawable.btn_star_big_on;
                    }
                });

                img12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = R.drawable.group_1;
                    }
                });

                img13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = android.R.drawable.btn_star;
                    }
                });

                img14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = R.drawable.ic_launcher_foreground;
                    }
                });

                img15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIcon = android.R.drawable.btn_star_big_on;
                    }
                });

                Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // =========================
                        // Read User Inputs
                        // =========================

                        String goalName = edtGoalName.getText().toString().trim();

                        String description = edtDescription.getText().toString().trim();

                        String estimatedTime = edtEstimatedTime.getText().toString().trim();

                        boolean noDeadline = switchDeadline.isChecked();

                        boolean notification = switchNotification.isChecked();

                        String reminderFrequency =
                                spinnerFrequency.getSelectedItem().toString();

                        String reminderAmPm =
                                spinnerAmPm.getSelectedItem().toString();

                        String deadline = showDate.getText().toString();

                        int priority = ratingBar.getRating();

                        reminderTime = "8:00 " + reminderAmPm;

                        // =========================
                        // Validation
                        // =========================

                        if (goalName.isEmpty()) {

                            edtGoalName.setError("Enter Goal Name");

                            return;
                        }

                        if (!noDeadline && deadline.equalsIgnoreCase("Select Date")) {

                            showDate.setError("Select Deadline");

                            return;
                        }

                        // =========================
                        // Create Goal Object
                        // =========================

                        GoalModel goal = new GoalModel(

                                goalName,
                                description,
                                category,
                                selectedIcon,
                                priority,
                                currentDate,
                                deadline,
                                noDeadline,
                                estimatedTime,
                                notification,
                                reminderFrequency,
                                reminderTime,
                                reminderDays,
                                0,
                                0,
                                GoalModel.IN_PROGRESS,
                                0
                        );

                        // =========================
                        // Save to Room Database
                        // =========================

                        dao.insert(goal);

                        // =========================
                        // Refresh RecyclerView
                        // =========================

                        goalList.clear();

                        goalList.addAll(dao.getAllGoals());

                        adapter.notifyDataSetChanged();

                        // =========================
                        // Close Dialog
                        // =========================

                        dialog.dismiss();

                    }
                });




                Spinner01 S=new Spinner01();

                S.setSpinner(dialog.getContext(), spinnerFrequency, R.array.reminder_frequency);
                S.setSpinner(dialog.getContext(), spinnerAmPm, R.array.am_pm);


                int width = (int) (342 * getResources().getDisplayMetrics().density);
                int height = (int) (527 * getResources().getDisplayMetrics().density);

                dialog.show();

                dialog.getWindow().setLayout(width, height);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            }
        });

    }
}