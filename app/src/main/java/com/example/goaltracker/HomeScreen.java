package com.example.goaltracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.MotionEvent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeScreen extends AppCompatActivity {
    ConstraintLayout
            envelopeCard,featureDrawer;
    EditText noteInput;

    private float dX, dY;
    private float downRawX, downRawY;

    TextView textCounter,messageBar,showDate;
    Button saveButton,exitButton,exitButton3,plus,progressdashboard;
    float originalX;
    float originalY;

    FloatingActionButton AI;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);

        envelopeCard = findViewById(R.id.envelopeCard);

        envelopeCard.post(() -> {
            originalX = envelopeCard.getTranslationX();
            originalY = envelopeCard.getTranslationY();
        });
        envelopeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                envelopeCard.getLocationOnScreen(location);

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                float screenCenterX = displayMetrics.widthPixels / 2f;
                float screenCenterY = displayMetrics.heightPixels / 2f;

                float cardCenterX = location[0] + envelopeCard.getWidth() / 2f;
                float cardCenterY = location[1] + envelopeCard.getHeight() / 2f;

                float moveX = screenCenterX - cardCenterX;
                float moveY = screenCenterY - cardCenterY;

                envelopeCard.animate()
                        .translationX(moveX)
                        .translationY(moveY)
                        .setDuration(600)
                        .start();

                ImageView star = findViewById(R.id.star);
                ImageView flap=findViewById(R.id.flap);

                star.animate()
                        .scaleX(1.2f)
                        .scaleY(1.2f)
                        .alpha(0f)
                        .setDuration(300)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {

                                // Hide the star completely
                                star.setVisibility(View.GONE);
                                removeFlap();


                            }
                            void removeFlap() {
                                flap.animate()
                                        .alpha(0f)
                                        .translationY(-30f)
                                        .setDuration(250)
                                        .withEndAction(new Runnable() {
                                            @Override
                                            public void run() {

                                                flap.setVisibility(View.GONE);
                                                disappearEnvelope();

                                            }
                                        })
                                        .start();
                            }

                            void disappearEnvelope() {

                                envelopeCard.animate()
                                        .alpha(0f)
                                        .scaleX(0.8f)
                                        .scaleY(0.8f)
                                        .setDuration(350)
                                        .withEndAction(new Runnable() {
                                            @Override
                                            public void run() {

                                                envelopeCard.setVisibility(View.GONE);

                                                appearDialog();

                                            }
                                        })
                                        .start();
                            }
                            void appearDialog(){
                                Dialog dialog = new Dialog(HomeScreen.this);
                                dialog.setContentView(R.layout.message);


                                noteInput=dialog.findViewById(R.id.noteInput);
                                noteInput.setFilters(new InputFilter[]{
                                        new InputFilter.LengthFilter(180)
                                });

                                textCounter = dialog.findViewById(R.id.textCounter);
                                messageBar=findViewById(R.id.messageBar);

                                saveButton=dialog.findViewById(R.id.saveButton);
                                saveButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String message=noteInput.getText().toString();
                                        messageBar.setText(message);
                                        messageBar.setVisibility(View.VISIBLE);
                                        startSlidingMessage();
                                        dialog.dismiss();

                                    }


                                });
                                noteInput.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                        textCounter.setText(s.length() + " / 180");

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                    }
                                });
                                exitButton=dialog.findViewById(R.id.exitButton2);
                                exitButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        restoreEnvelope();
                                    }
                                    void restoreEnvelope() {

                                        envelopeCard.setVisibility(View.VISIBLE);

                                        flap.setVisibility(View.VISIBLE);
                                        flap.setAlpha(1f);
                                        flap.setTranslationY(0);

                                        star.setVisibility(View.VISIBLE);
                                        star.setAlpha(1f);
                                        star.setScaleX(1f);
                                        star.setScaleY(1f);

                                        envelopeCard.setAlpha(0f);
                                        envelopeCard.setScaleX(0.8f);
                                        envelopeCard.setScaleY(0.8f);

                                        envelopeCard.animate()
                                                .alpha(1f)
                                                .scaleX(1f)
                                                .scaleY(1f)
                                                .translationX(originalX)
                                                .translationY(originalY)
                                                .setDuration(500)
                                                .start();
                                    }
                                });

                                int width = (int) (301* getResources().getDisplayMetrics().density);
                                int height = (int) (301 * getResources().getDisplayMetrics().density);

                                dialog.getWindow().setLayout(width, height);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                                dialog.show();

                            }
                        })
                        .start();
                        }

            private void startSlidingMessage() {

                messageBar.post(new Runnable() {
                    @Override
                    public void run() {

                        messageBar.setTranslationX(messageBar.getRootView().getWidth());

                        messageBar.animate()
                                .translationX(-messageBar.getWidth())
                                .setDuration(12000)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {

                                        Intent intent=new Intent(HomeScreen.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .start();
                    }
                });
            }

        });

        exitButton3=findViewById(R.id.exitButton3);
        exitButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        plus=findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this,cardsOfGoal.class);
                startActivity(intent);
            }
        });

        progressdashboard=findViewById(R.id.progressdashboard);
        progressdashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this, progressdashboard.class);
                startActivity(intent);
            }
        });

        AI=findViewById(R.id.AiButton);
        AI.setOnTouchListener((view, event) -> {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    downRawX = event.getRawX();
                    downRawY = event.getRawY();

                    dX = view.getX() - event.getRawX();
                    dY = view.getY() - event.getRawY();
                    return true;

                case MotionEvent.ACTION_MOVE:
                    view.setX(event.getRawX() + dX);
                    view.setY(event.getRawY() + dY);
                    return true;

                case MotionEvent.ACTION_UP:

                    float dx = event.getRawX() - downRawX;
                    float dy = event.getRawY() - downRawY;

                    // If finger barely moved, treat it as a click
                    if (Math.abs(dx) < 15 && Math.abs(dy) < 15) {

                        BottomSheetDialog dialog = new BottomSheetDialog(HomeScreen.this);

                        dialog.setContentView(R.layout.bottomai);

                        CardView goalSuggestion = dialog.findViewById(R.id.goalSuggestion);
                        CardView goalAnalyser = dialog.findViewById(R.id.goalAnalyser);
                        CardView successPredictor = dialog.findViewById(R.id.successPredictor);
                        CardView burnoutDetector = dialog.findViewById(R.id.burnoutDetector);
                        CardView accountabilityPartner = dialog.findViewById(R.id.accountabilityPartner);

                        goalSuggestion.setOnClickListener(v -> {
                            Intent intent = new Intent(HomeScreen.this, AIChatActivity.class);
                            intent.putExtra("mode", "goalSuggestion");
                            startActivity(intent);
                            dialog.dismiss();
                        });

                        goalAnalyser.setOnClickListener(v -> {
                            Intent intent = new Intent(HomeScreen.this, AIChatActivity.class);
                            intent.putExtra("mode", "goalAnalyser");
                            startActivity(intent);
                            dialog.dismiss();
                        });

                        successPredictor.setOnClickListener(v -> {
                            Intent intent = new Intent(HomeScreen.this, AIChatActivity.class);
                            intent.putExtra("mode", "successPredictor");
                            startActivity(intent);
                            dialog.dismiss();
                        });

                        burnoutDetector.setOnClickListener(v -> {
                            Intent intent = new Intent(HomeScreen.this, AIChatActivity.class);
                            intent.putExtra("mode", "burnoutDetector");
                            startActivity(intent);
                            dialog.dismiss();
                        });

                        accountabilityPartner.setOnClickListener(v -> {
                            Intent intent = new Intent(HomeScreen.this, AIChatActivity.class);
                            intent.putExtra("mode", "accountabilityPartner");
                            startActivity(intent);
                            dialog.dismiss();
                        });

                        dialog.show();
                    }

                    return true;

            }

            return false;

        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}