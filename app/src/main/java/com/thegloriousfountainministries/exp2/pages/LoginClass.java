package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
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

public class LoginClass extends AppCompatActivity{
    EditText name, password;
    Button send;
    RelativeLayout bar;
    Preference pref;
    String getUser, getPass;
    ApiInterface apiInterface;
    TextView go;
    private static final String BASE_URL = "https://chimdike.000webhostapp.com/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        name = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.pass);
        send = (Button)findViewById(R.id.login);
        bar = (RelativeLayout) findViewById(R.id.prog);
        pref = new Preference(this);
        go = (TextView)findViewById(R.id.go2);
        if (!pref.isFirstTimeLaunch()){
            //Homestraight
            homeStraight();
        }


        //Get Strings
        getUser = name.getText().toString().trim();
        getPass = password.getText().toString().trim();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeStraight2();
            }
        });


    }

    private void setEditEmpty() {
        name.setText("");
        password.setText("");
    }

    private void homeStraight() {
        startActivity(new Intent(LoginClass.this, MainActivity.class));
        finish();
    }
    private void homeStraight2() {
        startActivity(new Intent(LoginClass.this, Register.class));
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
        else if (!TextUtils.isEmpty(getUser) && !TextUtils.isEmpty(getPass)){
            bar.setVisibility(View.VISIBLE);
            login(getUser, getPass);
        }
    }
    private void login(String pr1, String pr2){
        getRetrofit(BASE_URL);
        Call<Login> call =  apiInterface.authenticate3(pr1, pr2);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                String res = response.body().getRes();
                if (res.equals("Logged In")){
                    //login
                    bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Successfully logged in!!!", Toast.LENGTH_SHORT).show();
                    pref.setSession(false);
                    homeStraight();
                }else if (res.equals("dont exist")){
                    bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Username or Password does not exist", Toast.LENGTH_SHORT).show();
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
