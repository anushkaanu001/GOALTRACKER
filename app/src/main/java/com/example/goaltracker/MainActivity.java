package com.example.goaltracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button,exitButton,backButton;
    Button contiueButton;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.enterButton_01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog);

                int width = (int) (360 * getResources().getDisplayMetrics().density);
                int height = (int) (550 * getResources().getDisplayMetrics().density);

                dialog.getWindow().setLayout(width, height);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                contiueButton=dialog.findViewById(R.id.continueButton);
                contiueButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,HomeScreen.class);
                        startActivity(intent);

                    }
                });

                backButton=dialog.findViewById(R.id.backButton);
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dialog!=null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                });



            }
        });

        exitButton=findViewById(R.id.exitButton_01);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}