package com.example.sit317mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Social extends AppCompatActivity {

    //create variables for the ui elements
    Button btnSend, btnSocialBack;
    EditText TextboxChatInput;
    ListView listViewChatLog;
    int MessageCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        //create variable to allow database connection
        Database db = new Database(this);

        //link UI elements to the variables
        btnSend = findViewById(R.id.BtnSend);
        btnSocialBack = findViewById(R.id.BtnSocialBack);
        TextboxChatInput = findViewById(R.id.TxtChatInput);
        listViewChatLog = findViewById(R.id.ListViewChatLog);

        //create intent to allow activity swapping
        Intent ReturnHub = new Intent(this, MainHub.class);

        //on click event for button to swap activities
        btnSocialBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ReturnHub);
            }
        });

        //set up the list view with items
        SetupChatLogs();

        //button to send message to chatroom
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextboxChatInput.getText().toString().equals("")){
                    String FullName = "Name";
                    Bundle Name = getIntent().getExtras();
                    if (Name!= null){//get the users name that was sent from the main hub activity
                        FullName = Name.getString("Name");
                    }
                    //"send" the message to the database table
                    Logs Temp = new Logs(FullName, TextboxChatInput.getText().toString());
                    db.InsertLog(Temp);

                    //create responses for the simulated people in the chat room
                    String Message1 = "Response";
                    String Message2 = "Response";

                    if (MessageCount == 0){
                        Message1 = "Response " + 1;
                        Message2 = "Response " + 2;
                        MessageCount++;
                    }else if (MessageCount == 1){
                        Message1 = "Response " + 3;
                        Message2 = "Response " + 4;
                        MessageCount++;
                    }else if (MessageCount == 2){
                        Message1 = "Response " + 5;
                        Message2 = "Response " + 5;
                        MessageCount++;
                    }

                    Logs Temp2 = new Logs("Person A", Message1);
                    db.InsertLog(Temp2);

                    Logs Temp3 = new Logs("Person B", Message2);
                    db.InsertLog(Temp3);

                    //update the listview
                    SetupChatLogs();
                }
            }
        });
    }
    private void SetupChatLogs(){//sets up or updates the list view with the messages in the chat logs table
        Database db = new Database(this);
        ArrayList<Logs> Logs = db.FetchAllLogs();
        ArrayList<String> StringLogs = new ArrayList<>();

        for (int i =0; i<Logs.size();i++){
            StringLogs.add(Logs.get(i).getPerson()+": "+Logs.get(i).getMessage());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, StringLogs);
        listViewChatLog.setAdapter(adapter);
    }
}