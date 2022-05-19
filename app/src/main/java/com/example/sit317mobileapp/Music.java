package com.example.sit317mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class Music extends YouTubeBaseActivity {

    //create variables for the ui elements
    Button btnPlaylist, btnBack;
    ListView listViewVideos;
    YouTubePlayerView youtubePlayerView;
    YouTubePlayer.OnInitializedListener InitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        //link the ui elements to the variables
        youtubePlayerView = findViewById(R.id.YoutubePlayerView);
        listViewVideos = findViewById(R.id.ListViewVideos);
        btnPlaylist = findViewById(R.id.BtnPlaylist);
        btnBack = findViewById(R.id.BtnBack);

        //create intents to allow activity swapping
        Intent StartPlaylist = new Intent(this, MusicPlaylist.class);
        Intent ReturnHub = new Intent(this, MainHub.class);

        //on click events for buttons for activity swapping
        btnPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StartPlaylist);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ReturnHub);
            }
        });

        //create a variable the allows database connection
        Database db = new Database(this);

        //setup the list view with items
        SetupVideos();

        //on youtube player initialised event to load videos
        InitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideos(db.FetchAllYoutubeURL());

                //only load the video if the initialisation was successful
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        //attempt to initialisation of the youtube player
        youtubePlayerView.initialize("AIzaSyBNxtXpoXCmRvPqD3PwK812oJZQJ_f0gCM", InitializedListener);
    }

    private ArrayList<String> SetupVideos(){//setup the list view to show youtube links
        Database db = new Database(this);
        ArrayList<String> Videos = db.FetchAllYoutubeURL();
        ArrayList<String> VideosFullURL = new ArrayList<>();

        for (int i = 0; i < Videos.size();i++){
            VideosFullURL.add("https://www.youtube.com/watch?v="+Videos.get(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, VideosFullURL);
        listViewVideos.setAdapter(adapter);
        return Videos;
    }
}