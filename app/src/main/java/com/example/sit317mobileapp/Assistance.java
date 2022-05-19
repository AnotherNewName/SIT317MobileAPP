package com.example.sit317mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Assistance extends AppCompatActivity {

    //variables for the UI elements
    Button btnAssistanceBack, btnMentalHealthSurvey, btnTriggerFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistance);

        //link the ui elements to the variables
        btnAssistanceBack = findViewById(R.id.btnAssistanceBack);
        btnMentalHealthSurvey = findViewById(R.id.BtnMentalHealthSurvey);
        btnTriggerFilter = findViewById(R.id.BtnTriggerFilter);

        //creatae the intents for the activity swapping
        Intent ReturnHub = new Intent(this, MainHub.class);
        Intent StartMentalHealthSurvey = new Intent(this, MentalHealthSurvey.class);
        Intent StartTriggerFilter = new Intent(this, TriggerFilter.class);

        //onclick event to return to hub activity
        btnAssistanceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ReturnHub);
            }
        });

        //on click event to swap to mental health survey
        btnMentalHealthSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StartMentalHealthSurvey);
            }
        });

        //on click event to swap to trigger filter
        btnTriggerFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StartTriggerFilter);
            }
        });
    }
}