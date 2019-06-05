package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thegloriousfountainministries.exp2.R;

public class Splash extends AppCompatActivity {
    ImageView logo;
    Animation animation;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = (ImageView)findViewById(R.id.logo);
        text = (TextView) findViewById(R.id.status);
        Picasso.with(this).load(R.drawable.gflogoss).fit().into(logo);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
        logo.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                text.setText("Checking network connection...");
                //overridePendingTransition();
            }
        }, 2000);
        ConnectivityManager connectivityManager;
        connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);

        NetworkInfo net = connectivityManager.getActiveNetworkInfo();
        if (net!=null && net.isConnected()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    text.setText("Connection OK...");
                    //overridePendingTransition();
                }
            }, 3000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    text.setText("#TeamGFM...");

                    //overridePendingTransition();
                }
            }, 4000);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(Splash.this, LoginClass.class);
                    startActivity(intent);
                    //overridePendingTransition();

                }
            }, 5000);
        }else{
            Intent intent = new Intent(Splash.this, Errorr.class);
            startActivity(intent);
        }




    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
