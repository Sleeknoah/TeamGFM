package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.data.BibleContract;
import com.thegloriousfountainministries.exp2.data.BibleDb;

/**
 * Created by My HP on 9/15/2017.
 */

public class BibleReader extends AppCompatActivity{
    TextView text,text2;

    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bible);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
        text = (TextView)findViewById(R.id.text);
        text2 = (TextView)findViewById(R.id.texxt);
        Intent intent = this.getIntent();
        String link = intent.getExtras().getString("book");
        String indexx = intent.getExtras().getString("index");
        text.setText(link);
        getSupportActionBar().setTitle(link);
        toolbar.setTitleTextColor(Color.WHITE);



        //text2.setText("abeg work");

        String[] projection = {
                BibleContract.BibleColumn.COLUMN_BOOKS,
                BibleContract.BibleColumn.COLUMN_CONTENT
        };
//String selection = BibleContract.BibleColumn._ID + Integer.toString(index);
        String[] selectionArgs = {"1"};

        if(indexx.equals(indexx) && !indexx.equals("0")){//try link.equals(
            //Toast.makeText(getApplicationContext(), "Okay na", Toast.LENGTH_SHORT).show();
            BibleDb helper = new BibleDb(this);

            SQLiteDatabase db = helper.getReadableDatabase();

            String selection = BibleContract.BibleColumn._ID + " = "+ indexx;

            Cursor c  = db.query(BibleContract.BibleColumn.TABLE_NAME,
                    projection,
                    selection,
                    null, null, null, null);
            c.moveToNext();

            try {
                int getColumnIndex = c.getColumnIndex(BibleContract.BibleColumn.COLUMN_BOOKS);
                String book  = c.getString(getColumnIndex);
                int getColumnIndex2 = c.getColumnIndex(BibleContract.BibleColumn.COLUMN_CONTENT);
                //int getbooks = c.getInt(getColumnIndex2);
                String content  = c.getString(getColumnIndex2);
                text2.setText(content);
            }finally {
                c.close();
            }

        }

//        if(link.equals("Exodus")){//try link.equals(book);
//            //Toast.makeText(getApplicationContext(), "Okay na", Toast.LENGTH_SHORT).show();
//            int index = 1;
//            index+=index;
//            String ind = Integer.toString(index);
//
//            BibleDb helper = new BibleDb(this);
//
//            SQLiteDatabase db = helper.getReadableDatabase();
//
//            String selection = BibleContract.BibleColumn._ID + " = " + ind;
//
//            Cursor c  = db.query(BibleContract.BibleColumn.TABLE_NAME,
//                    projection,
//                    selection,
//                    null, null, null, null);
//            c.moveToNext();
//
//            try {
//                int getColumnIndex = c.getColumnIndex(BibleContract.BibleColumn.COLUMN_BOOKS);
//                String book  = c.getString(getColumnIndex);
//                int getColumnIndex2 = c.getColumnIndex(BibleContract.BibleColumn.COLUMN_CONTENT);
//                //int getbooks = c.getInt(getColumnIndex2);
//                String content  = c.getString(getColumnIndex2);
//                text2.setText(content);
//            }finally {
//                c.close();
//            }

        //}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bible, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.change:
                //Write your code here
                break;
        }
        return true;
    }
}
