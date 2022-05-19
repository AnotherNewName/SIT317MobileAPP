package com.example.sit317mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    //create variables for the UI elements
    EditText textboxCreateFullName, textboxCreateEmail, textboxCreateUsername, textboxCreatePassword, textboxConfirmPassword;
    Button btnCreateAccount;
    CheckBox CheckboxTandC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //link the variables to the UI elements
        CheckboxTandC = findViewById(R.id.CheckBoxTandC);
        textboxCreateFullName = findViewById(R.id.TextboxCreateFullName);
        textboxCreateEmail = findViewById(R.id.TextboxCreateEmail);
        textboxCreateUsername = findViewById(R.id.TextboxCreateUsername);
        textboxCreatePassword = findViewById(R.id.TextboxCreatePassword);
        textboxConfirmPassword = findViewById(R.id.TextboxConfirmPassword);
        btnCreateAccount = findViewById(R.id.BtnCreateAccount);

        //this variable allows connection to the database
        Database db = new Database(this);

        //set up the intent to return to the main activity
        Intent ReturnMain = new Intent(this, MainActivity.class);

        //this on click event checks and creaete the login account
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the required fields are filled
                if (!textboxCreateFullName.getText().toString().equals("") &&
                        !textboxCreateEmail.getText().toString().equals("") &&
                        !textboxCreateUsername.getText().toString().equals("") &&
                        !textboxCreatePassword.getText().toString().equals("") &&
                        !textboxConfirmPassword.getText().toString().equals("") && CheckboxTandC.isChecked() &&
                        textboxCreatePassword.getText().toString().equals(textboxConfirmPassword.getText().toString())){
                    //if the fields are filled properly, create the account
                    Login account = new Login(textboxCreateEmail.getText().toString(), textboxCreateUsername.getText().toString(),
                            textboxConfirmPassword.getText().toString(),textboxCreateFullName.getText().toString());
                    //insert the account into the table
                    db.InsertLogin(account);
                    //return to the main activity
                    startActivity(ReturnMain);

                    //check if the passwords match
                }else if(!CheckboxTandC.isChecked()){
                    Toast.makeText(SignUp.this, "You need to agree to the Terms and Conditions", Toast.LENGTH_SHORT).show();
                }else if (!textboxCreatePassword.getText().toString().equals(textboxConfirmPassword.getText().toString())){
                    //tell the user when the passwords dont match
                    Toast.makeText(SignUp.this, "The passwords need to match", Toast.LENGTH_LONG).show();
                }else{
                    //tell the user if the fields are not filled properly
                    Toast.makeText(SignUp.this, "Fill in all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}