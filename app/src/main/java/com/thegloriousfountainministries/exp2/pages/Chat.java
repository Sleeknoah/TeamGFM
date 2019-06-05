package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.adapters.ChatAdapter;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.ChatClas;
import com.thegloriousfountainministries.exp2.custom.ChatClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Chat extends AppCompatActivity {
    Toolbar toolz;
    ListView listView;
    ChatAdapter chatAdapter;
    EditText text;
    Button send, back;
    List<ChatClass> chat;
    LinearLayout loader;
    Calendar cal;
    String user1;
    public static ApiInterface apiInterface;
    Intent intent;
    public static final String BASE_URL2 = "https://chimdike.000webhostapp.com/";
    int firstTry = 1;
    int firstTry2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        text = (EditText) findViewById(R.id.eee);
        send = (Button) findViewById(R.id.send);
        //back = (ImageView)findViewById(R.id.back);
//        toolz = (Toolbar)findViewById(R.id.toolz);
        listView = (ListView) findViewById(R.id.lists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        setSupportActionBar(toolz);
//        getSupportActionBar().setIcon(R.drawable.gflogoss);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cal = Calendar.getInstance();
        loader = (LinearLayout)findViewById(R.id.loader);
        intent = this.getIntent();
        String use = intent.getExtras().getString("index");
        int user = Integer.parseInt(use);
        int user11 = user+1;
        user1 = Integer.toString(user11);
        chat = new ArrayList<>();
        loginNow2(user1, "3");
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Chat.this, MainActivity.class));
//            }
//        });

        text.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                enableSubmitIfReady();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText().toString().matches("")) {

                    return;
                } else {
                    sendMessage();
                    listView.setAdapter(chatAdapter);
                    text.setText("");
                }
            }
        });

    }

    private void sendMessage() {
        intent = this.getIntent();
        String use = intent.getExtras().getString("index");
        int user = Integer.parseInt(use);
        int user11 = user+1;
        String user1 = Integer.toString(user11);
        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();
        chat.add(new ChatClass(text.getText().toString(), "1", " now()"));
        Call<ChatClas> call =  apiInterface.authenticate(user1, "3", text.getText().toString());
        call.enqueue(new Callback<ChatClas>() {
            @Override
            public void onResponse(@NonNull Call<ChatClas> call, @NonNull Response<ChatClas> response) {
                String res = response.body().getResponse();
                if (res.equals("Sent")){
                    chatAdapter = new ChatAdapter(Chat.this, R.layout.chatbub1, chat, res);

                    //display check icon

                }else if (res.equals("Error")){
                    //display error icon
                    Toast.makeText(Chat.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChatClas> call, Throwable t) {
                Toast.makeText(Chat.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void enableSubmitIfReady() {

        boolean isReady = text.getText().toString().length() > 0;
        send.setEnabled(isReady);
    }

    private void getRetrofit(String url1) {
        //Build Retrofit and connect to interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url1)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        apiInterface = retrofit.create(ApiInterface.class);

    }

    public void loginNow2(String param1, String param2) {
        getRetrofit(BASE_URL2);
        Call<List<ChatClass>> call = apiInterface.authenticate(param1, param2);
        call.enqueue(new Callback<List<ChatClass>>() {
            @Override
            public void onResponse(Call<List<ChatClass>> call, Response<List<ChatClass>> response) {
                chat = response.body();
                chatAdapter = new ChatAdapter(Chat.this, R.layout.chatbub1, chat, "");
                listView.setAdapter(chatAdapter);
                loader.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<ChatClass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                if (firstTry < 3){
                    retry();
                    firstTry = firstTry2 +1;
                    firstTry2 = firstTry;
                }

            }
        });


    }

    private void retry() {
        loginNow2(user1, "3");
    }
}
