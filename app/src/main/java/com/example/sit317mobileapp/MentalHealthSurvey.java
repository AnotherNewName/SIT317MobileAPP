package com.example.sit317mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MentalHealthSurvey extends AppCompatActivity {

    //variable for UI element
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_health_survey);

        //link variable to UI element
        btnSubmit = findViewById(R.id.BtnSubmit);

        //create intent for swapping activities
        Intent ReturnAssistance = new Intent(this, Assistance.class);

        //on click event for button to swap activities
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ReturnAssistance);
            }
        });
    }
}