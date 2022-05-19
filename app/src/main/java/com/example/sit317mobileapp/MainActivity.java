package com.example.sit317mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //variables for the ui elements
    EditText TextboxEmail, TextboxPassword;
    Button btnLogin, btnSignUp;
    TextView lblTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link the UI elements to the variables
        lblTitle= findViewById(R.id.LblTitle);
        TextboxEmail = findViewById(R.id.TxtEmail);
        TextboxPassword = findViewById(R.id.TxtPassword);
        btnLogin = findViewById(R.id.BtnLogin);
        btnSignUp = findViewById(R.id.BtnSignUp);

        //this variable allows connection to the database
        Database db = new Database(this);

        //setup intents to swap over to other activities
        Intent Login = new Intent(this, MainHub.class);
        Intent SignUp = new Intent(this, SignUp.class);

        //get login data so that user logins can be checked
        ArrayList<Login> Logins = db.FetchAllLogins();

        //onclick to check login info and login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean verify = false;//this variable is to enable the checking of whether the login details are valid
                for (int i = 0; i < Logins.size(); i++){//this for loop is to check if the input login details are valid
                    if (Logins.get(i).getEmail().equals(TextboxEmail.getText().toString()) &&
                            Logins.get(i).getPassword().equals(TextboxPassword.getText().toString())){
                        verify = true;
                        String Email = Logins.get(i).getEmail();
                        String Username = Logins.get(i).getUsername();
                        String Name = Logins.get(i).getFull_Name();
                        Login.putExtra("Email", Email);
                        Login.putExtra("Username", Username);
                        Login.putExtra("Name", Name);
                        break;
                    }
                }
                if (verify == true){//if the login details are valid, swap to the main youtube player activity
                    startActivity(Login);
                }else{//if the login details are not valid, inform the user
                    Toast.makeText(MainActivity.this, "The username or password is incorrect.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //on click event to reset the tables in the database
        lblTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.ResetTables();
            }
        });

        //onclick to swap to sign up activity
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SignUp);
            }
        });
    }
}