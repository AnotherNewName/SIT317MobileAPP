package com.example.sit317mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainHub extends AppCompatActivity {

    //create variables for UI elements
    TextView LabelDisplayName, LabelDisplayEmail;
    Button btnMusic, btnSocial, btnAssistance, btnHelp, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);

        //create intents to allow activity swapping
        Intent StartHelp = new Intent(this, Help.class);
        Intent StartMusic = new Intent(this, Music.class);
        Intent StartSocial = new Intent(this, Social.class);
        Intent StartAssistance = new Intent(this, Assistance.class);
        Intent Logout = new Intent(this, MainActivity.class);

        //link variables to UI elements
        LabelDisplayName = findViewById(R.id.LblDisplayName);
        LabelDisplayEmail = findViewById(R.id.LblDisplayEmail);
        btnMusic = findViewById(R.id.BtnMusic);
        btnSocial = findViewById(R.id.BtnSocial);
        btnAssistance = findViewById(R.id.BtnAssistance);
        btnHelp = findViewById(R.id.BtnHelp);
        btnLogout = findViewById(R.id.BtnLogout);

        //create a variable to enable database connection
        Database db = new Database(this);

        //sending the users name to the social activity
        StartSocial.putExtra("Name", "Qwerty");

        //on click events for buttons to swap activities
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StartHelp);
            }
        });
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StartMusic);
            }
        });

        btnSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StartSocial);
            }
        });
        btnAssistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StartAssistance);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Logout);
            }
        });

    }
}