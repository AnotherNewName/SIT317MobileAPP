package com.example.sit317mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends AppCompatActivity {

    //variable for the UI elements
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //link variable to UI element
        btnBack = findViewById(R.id.btnHelpBack);

        //create intent for activity swapping
        Intent ReturnHub = new Intent(this, MainHub.class);

        //on click event for button to swap activities
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ReturnHub);
            }
        });
    }
}