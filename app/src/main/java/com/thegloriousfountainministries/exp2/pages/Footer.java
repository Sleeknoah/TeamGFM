package com.thegloriousfountainministries.exp2.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thegloriousfountainministries.exp2.R;

/**
 * Created by My HP on 12/15/2017.
 */

public class Footer extends AppCompatActivity {
    Button checkout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.footer);
        checkout = (Button)findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
