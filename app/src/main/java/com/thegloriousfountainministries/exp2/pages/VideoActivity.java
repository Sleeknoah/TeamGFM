package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thegloriousfountainministries.exp2.R;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    public static final String API_KEY = "AIzaSyBYuc0nYWBwsZ0zx59bRMuzW4xkMTcMHXI";
    //TextView textView;
    Button button;
    Toolbar toolbar;
    View view;
    Intent intent;
    DatabaseReference database, mdatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAwesomeTheme3);
        setContentView(R.layout.activity_video);
        //textView = (TextView)findViewById(R.id.text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

         intent = this.getIntent();
        database = FirebaseDatabase.getInstance().getReference().child("youtube");
        mdatabase = FirebaseDatabase.getInstance().getReference().child("youtube_desc");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String urlw = dataSnapshot.getValue(String.class);
//                textView.setText(urlw);
//                Linkify.addLinks(textView, Linkify.ALL);
//                textView.setLinkTextColor(Color.BLUE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);

    }




    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getApplication(), "Opps! Initialization failed download Youtube app", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer player, boolean wasRestored) {

        if (!wasRestored){
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String link = dataSnapshot.getValue(String.class);
                    player.cueVideo(link);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
    }
    YouTubePlayer.PlayerStateChangeListener playerState = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
    YouTubePlayer.PlaybackEventListener playBack = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            playBack.onPlaying();
        }

        @Override
        public void onPaused() {
            playBack.onPaused();
        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };
}

