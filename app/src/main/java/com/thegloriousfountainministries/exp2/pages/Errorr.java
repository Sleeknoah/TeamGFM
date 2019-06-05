package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.thegloriousfountainministries.exp2.R;

/**
 * Created by My HP on 9/9/2017.
 */

public  class Errorr extends AppCompatActivity{
    ImageView err;
    Animation animation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Errorr.this, Splash.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
