package com.thegloriousfountainministries.exp2.pages;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.broadcast.AlarmReciever;
import com.thegloriousfountainministries.exp2.data.BibleContract;
import com.thegloriousfountainministries.exp2.data.BibleDb;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by My HP on 9/1/2017.
 */

public class Ready extends Fragment {
    boolean wat;
    Button bible, dev, quotes;
    ImageView n3;
    TimePicker n2;
    Button ima;
    TextView text, text2;
    Animation fade;
    private Context mContext;
    ListView list;
    PendingIntent pend;
    Calendar cal;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v= inflater.inflate(R.layout.read, container, false);
        bible = (Button)v.findViewById(R.id.bible);
        dev =(Button)v.findViewById(R.id.dev);
        quotes =(Button)v.findViewById(R.id.quotes);
        list = (ListView) v.findViewById(R.id.list);
        mContext = getActivity();
        n2 = (TimePicker) v.findViewById(R.id.n2);
        ima = (Button)v.findViewById(R.id.ima);
        n3 = (ImageView)v.findViewById(R.id.n3);
        fade = AnimationUtils.loadAnimation(mContext, R.anim.faded);
        text = (TextView)v.findViewById(R.id.txt);
        text2 = (TextView)v.findViewById(R.id.txxt);
        cal = Calendar.getInstance();
      //addBible();
        //getdatabasecount();

        //listview starts here
        listString();
        bible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable1 = ContextCompat.getDrawable(mContext, R.drawable.button);
                Drawable drawable2 = ContextCompat.getDrawable(mContext, R.drawable.button5);
                Drawable drawable3 = ContextCompat.getDrawable(mContext, R.drawable.button6);
                dev.setBackground(drawable2);
                bible.setBackground(drawable1);
                quotes.setBackground(drawable3);
                bible.setTextColor(Color.WHITE);
                dev.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                quotes.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                text.setVisibility(View.VISIBLE);
                list.startAnimation(fade);
                list.setVisibility(View.VISIBLE);
                n2.setVisibility(View.INVISIBLE);
                n3.setVisibility(View.INVISIBLE);
                ima.setVisibility(View.INVISIBLE);
                //addBible(getResources().openRawResource(R.raw.gen), "Genesis");
                //addBible();
                //getdatabasecount();
                //alarmNow();

            }
        });
        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable1 = ContextCompat.getDrawable(mContext, R.drawable.button2);
                Drawable drawable2 = ContextCompat.getDrawable(mContext, R.drawable.button4);
                Drawable drawable3 = ContextCompat.getDrawable(mContext, R.drawable.button6);
                dev.setBackground(drawable2);
                bible.setBackground(drawable1);
                quotes.setBackground(drawable3);
                dev.setTextColor(Color.WHITE);
                bible.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                quotes.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                n2.startAnimation(fade);
                n2.setVisibility(View.VISIBLE);
                text.setVisibility(View.INVISIBLE);
                list.setVisibility(View.INVISIBLE);
                n3.setVisibility(View.INVISIBLE);
                ima.setVisibility(View.VISIBLE);
            }
        });
        quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable4 = ContextCompat.getDrawable(mContext, R.drawable.button2);
                Drawable drawable3 = ContextCompat.getDrawable(mContext, R.drawable.button5);
                Drawable drawable2 = ContextCompat.getDrawable(mContext, R.drawable.button3);
                quotes.setBackground(drawable2);
                dev.setBackground(drawable3);
                bible.setBackground(drawable4);
                quotes.setTextColor(Color.WHITE);
                bible.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                dev.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                n3.startAnimation(fade);
                n3.setVisibility(View.VISIBLE);
                text.setVisibility(View.INVISIBLE);
                list.setVisibility(View.INVISIBLE);
                n2.setVisibility(View.INVISIBLE);
                ima.setVisibility(View.INVISIBLE);
            }
        });
        ima.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlarmManager alarm = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pend);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cal.set(Calendar.HOUR_OF_DAY, n2.getHour());
            cal.set(Calendar.MINUTE, n2.getMinute());
        }else {
            cal.set(Calendar.HOUR_OF_DAY, n2.getCurrentHour());
            cal.set(Calendar.MINUTE, n2.getCurrentMinute());
        }

        Intent intent = new Intent(mContext, AlarmReciever.class);


        pend = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarm.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), pend);
    }
});
        return v;
    }

    private void getdatabasecount() {
        //get helper class and pass context
        BibleDb helper = new BibleDb(mContext);
//create the database
        SQLiteDatabase db  = helper.getReadableDatabase();
//query the database columns
        Cursor cursor = db.rawQuery("SELECT * FROM " + BibleContract.BibleColumn.TABLE_NAME, null);
        //try/finally block
        try {
            //set the text to getCount of db query
            text.setText("Number of rows in database: "+ cursor.getCount());

        }finally {
            //always close cursor after reading from it
            cursor.close();
        }
        if (text.getText().length() > 7){
            final String ntest = text.getText().toString();
            String see = "...see more";
            text2.setText(see);
            Linkify.addLinks(text2, Linkify.ALL);
            text.setText(ntest.substring(0, 7));


            text2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (wat == false){
                        text.setText(ntest);
                        text2.setText(" see less");
                        Linkify.addLinks(text2, Linkify.ALL);
                        text2.setLinkTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                        wat = true;
                    }else {
                        String see = "...see more";
                        text2.setText(see);
                        Linkify.addLinks(text2, Linkify.ALL);
                        text.setText(ntest.substring(0, 7));
                        wat = false;
                    }
                }
            });
        }

    }
    private void addBible(InputStream im, String book){
        String gen;
        InputStream inputStream = im;



        BibleDb helper = new BibleDb(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(BibleContract.BibleColumn.COLUMN_BOOKS, book);
        //String line = getResources().getString(R.string.genesis);
        try {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            gen = new String(buffer);
            value.put(BibleContract.BibleColumn.COLUMN_CONTENT, gen);
        } catch (IOException e) {
            e.printStackTrace();
        }


        long idRow = db.insert(BibleContract.BibleColumn.TABLE_NAME, null, value);
        Log.v("IdRow", "Id Count" + idRow);
    }


    private void listString(){
        final ArrayList<String> books = new ArrayList<>();
        books.add("TeamGFM BUILT-IN STUDY BIBLE");
        books.add("Genesis");
        books.add("Exodus");
        books.add("Leviticus");
        books.add("Numbers");
        books.add("Deuteronomy");
        books.add("Joshua");
        books.add("Judges");
        books.add("Ruth");
        books.add("1 Samuel");
        books.add("2 Samuel");
        books.add("1 Kings");
        books.add("2 Kings");
        books.add("1 Chronicles");
        books.add("2 Chronicles");
        books.add("Ezra");
        books.add("Nehemiah");
        books.add("Esther");
        books.add("Job");
        books.add("Psalms");
        books.add("Proverbs");
        books.add("Eccesiastes");
        books.add("Song of Songs");
        books.add("Isaiah");
        books.add("Jeremiah");
        books.add("Lamentations");
        books.add("Ezekiel");
        books.add("Daniel");
        books.add("Hosea");
        books.add("Joel");
        books.add("Amos");
        books.add("Obadiah");
        books.add("Jonah");
        books.add("Micah");
        books.add("Nahum");
        books.add("Habakkuk");
        books.add("Zephniah");
        books.add("Haggai");
        books.add("Zechariah");
        books.add("Malachi");
        books.add("Matthew");
        books.add("Mark");
        books.add("Luke");
        books.add("John");
        books.add("Acts");
        books.add("Romans");
        books.add("1 Corinthians");
        books.add("2 Corinthians");
        books.add("Galatians");
        books.add("Ephesians");
        books.add("Philippians");
        books.add("Colossians");
        books.add("1 Thessalonians");
        books.add("2 Thessalonians");
        books.add("1 Timothy");
        books.add("2 Timothy");
        books.add("Titus");
        books.add("Philemon");
        books.add("Hebrews");
        books.add("James");
        books.add("1 Peter");
        books.add("2 Peter");
        books.add("1 John");
        books.add("2 John");
        books.add("3 John");
        books.add("Jude");
        books.add("Revelations");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, books);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int no = books.indexOf(books.get(position));
                String noo = Integer.toString(no) ;
                Toast.makeText(mContext, "Opening the book: "+ books.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, BibleReader.class);
                intent.putExtra("book", books.get(position));
                intent.putExtra("index", noo);
                startActivity(intent);


            }
        });

    }
}
