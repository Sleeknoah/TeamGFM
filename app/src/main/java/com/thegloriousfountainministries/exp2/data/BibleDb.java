package com.thegloriousfountainministries.exp2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by My HP on 9/14/2017.
 */

public class BibleDb extends SQLiteOpenHelper {
    //create srings for name and version of database
    private static final String DATABASE_NAME = "bible.db";
    private static final int DATABASE_VERSION = 1;

    public BibleDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create string to add columns

         String CREATE_BIBLE_TABLE = "CREATE TABLE " + BibleContract.BibleColumn.TABLE_NAME + " ("
                 + BibleContract.BibleColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                 + BibleContract.BibleColumn.COLUMN_BOOKS + " TEXT NOT NULL, "
                 + BibleContract.BibleColumn.COLUMN_CONTENT + " TEXT NOT NULL);";
        //create database



        db.execSQL(CREATE_BIBLE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
