package com.thegloriousfountainministries.exp2.pages;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.thegloriousfountainministries.exp2.R;

/**
 * Created by My HP on 9/12/2017.
 */

public class Search extends AppCompatActivity{
    CardView cardView;
    Button search;
    ImageView speaker;
    EditText edit;
    TextView text;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        cardView = (CardView)findViewById(R.id.search);
        search = (Button)findViewById(R.id.icon);
        speaker = (ImageView)findViewById(R.id.speaker);
        edit = (EditText)findViewById(R.id.edit);

    }

}
