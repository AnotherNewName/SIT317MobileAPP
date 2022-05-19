package com.example.sit317mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MusicPlaylist extends AppCompatActivity {

    //create variables for the ui elements
    Button btnAdd, btnRemove, btnPlaylistBack;
    EditText txtPlaylistInput;
    ListView listViewPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_playlist);

        //create variable to allow database connection
        Database db = new Database(this);

        //link ui elements to the variables
        btnAdd = findViewById(R.id.BtnAdd);
        btnRemove = findViewById(R.id.BtnRemove);
        btnPlaylistBack = findViewById(R.id.BtnPlaylistBack);
        txtPlaylistInput = findViewById(R.id.TxtPlaylistInput);
        listViewPlaylist = findViewById(R.id.ListViewPlaylist);

        //create intent for activity swapping
        Intent ReturnMusic = new Intent(this, Music.class);

        //setup the list view with items
        SetupVideos();

        //on click event to add youtube url to the youtube table
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Input = txtPlaylistInput.getText().toString();
                if (!Input.equals("")){
                    String[] Split = Input.split("watch?v=");
                    db.InsertYoutubeURL(Split[Split.length-1]);
                    SetupVideos();//update the list view
                }
            }
        });

        //on click event to remove youtube url from the youtube table
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Input = txtPlaylistInput.getText().toString();
                if (!Input.equals("")) {
                    String[] Split = Input.split("watch?v=");
                    db.DeleteYoutubeURL(Split[Split.length - 1]);
                    SetupVideos();//update the list view
                }
            }
        });

        //on click event to swap activities
        btnPlaylistBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ReturnMusic);
            }
        });
    }

    private ArrayList<String> SetupVideos(){//set up the list view to show or update youtube links
        Database db = new Database(this);
        ArrayList<String> Videos = db.FetchAllYoutubeURL();
        ArrayList<String> VideosFullURL = new ArrayList<>();

        for (int i = 0; i < Videos.size();i++){
            VideosFullURL.add("https://www.youtube.com/watch?v="+Videos.get(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, VideosFullURL);
        listViewPlaylist.setAdapter(adapter);
        return Videos;
    }
}