package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.Login;
import com.thegloriousfountainministries.exp2.custom.Preference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My HP on 7/16/2018.
 */

public class Register extends AppCompatActivity{
    EditText name, password, fname, email;
    Button send;
    TextView go;
    RelativeLayout bar;
    Preference pref;
    SharedPreferences prefs;
    String token;
    String getUser, getPass, getEmail, getFname, getFFname;
    ApiInterface apiInterface;
    private static final String BASE_URL = "https://chimdike.000webhostapp.com/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name = (EditText)findViewById(R.id.reguser);
        password = (EditText)findViewById(R.id.regpass);
        fname = (EditText)findViewById(R.id.fname);
        email = (EditText)findViewById(R.id.email);
        go = (TextView)findViewById(R.id.go);
        send = (Button)findViewById(R.id.reg);
        bar = (RelativeLayout) findViewById(R.id.prog2);
        pref = new Preference(this);
        token = pref.getToken();



        //Get Strings


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeStraight();
            }
        });


    }

    private void setEditEmpty() {
        name.setText("");
        password.setText("");
        email.setText("");
        fname.setText("");
    }

    private void homeStraight() {
        startActivity(new Intent(Register.this, LoginClass.class));
        finish();
    }

    private void getRetrofit(String url1){
        //Build Retrofit and connect to interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url1)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public void loginNow(View view) {

        checkDetails();
    }



    private void checkDetails(){
        //check inputs

        getUser = name.getText().toString().trim();
        getPass = password.getText().toString().trim();
        if (TextUtils.isEmpty(getUser) && TextUtils.isEmpty(getPass)){
            setEditEmpty();
            Toast.makeText(this, "Fields empty fill correctly", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(getUser)){
            setEditEmpty();
            Toast.makeText(this, "Username empty fill correctly", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(getPass)){
            setEditEmpty();
            Toast.makeText(this, "Password empty fill correctly", Toast.LENGTH_SHORT).show();
        }
        else {
            getUser = name.getText().toString().trim();
            getPass = password.getText().toString().trim();
            getEmail = email.getText().toString().trim();
            getFname = fname.getText().toString().trim();
            getFFname = getFname.replace(" ", " ");
            bar.setVisibility(View.VISIBLE);
            login(getFFname, getEmail, getUser, getPass, token);
        }
    }
    private void login(String pr1, String pr2, String pr3, String pr4, String token){
        getRetrofit(BASE_URL);
        Call<Login> call =  apiInterface.authenticate2(pr1, pr2, pr3, pr4, token);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                String res = response.body().getRes();
                if (res.equals("successfully")){
                    //login
                    bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    homeStraight();
                }else if (res.equals("user exists")){
                    bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                }else if (res.equals("Email exists")){
                    bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Email already taken", Toast.LENGTH_SHORT).show();
                }else if (res.equals("Not Added")){
                    bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Oops something went wrong!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                //Display error
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                bar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
