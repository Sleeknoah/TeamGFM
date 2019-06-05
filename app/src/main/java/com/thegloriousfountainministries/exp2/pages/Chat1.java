package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.adapters.ChatAdapter1;
import com.thegloriousfountainministries.exp2.custom.ChatClass1;

import java.util.ArrayList;
import java.util.Calendar;

public class Chat1 extends AppCompatActivity {
      Toolbar toolz;
    ListView listView;
    ChatAdapter1 chatAdapter;
    EditText text;

    ArrayList<ChatClass1> chat;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat1);


//        toolz = (Toolbar)findViewById(R.id.toolz);
        listView = (ListView)findViewById(R.id.online);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat with us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//         cal = Calendar.getInstance();
        chat = new ArrayList<>();
        sendMessage();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int no = chat.indexOf(chat.get(position));
                String noo = Integer.toString(no) ;;
                Intent intent = new Intent(Chat1.this, Chat.class);
                intent.putExtra("index", noo);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
    private void sendMessage() {
        chat.add(new ChatClass1("Chimdike", "Chimdike:", "Hello there!!!", "2", "1", "day"));
        chat.add(new ChatClass1("Richard", "Richard:", "Hello there!!!", "4", "3", "hrs"));
        chatAdapter = new ChatAdapter1(this, R.layout.model_rooms, chat);
        listView.setAdapter(chatAdapter);
    }





}
