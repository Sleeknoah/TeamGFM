package com.thegloriousfountainministries.exp2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by My HP on 9/14/2017.
 */

public class FavDb extends SQLiteOpenHelper {
    //create srings for name and version of database
    private static final String DATABASE_NAME = "fav.db";
    private static final int DATABASE_VERSION = 1;

    public FavDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create string to add columns

        String CREATE_CART_TABLE = "CREATE TABLE " + FavContract.FavColumn.TABLE_NAME + " ("
                + FavContract.FavColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FavContract.FavColumn.COLUMN_NAME + " TEXT NOT NULL, "
                + FavContract.FavColumn.COLUMN_IMAGE2 + " TEXT NOT NULL, "
                + FavContract.FavColumn.COLUMN_price2 + " INTEGER NOT NULL, "
                + FavContract.FavColumn.COLUMN_QUANTITY2 + " INTEGER NOT NULL, "
                + FavContract.FavColumn.COLUMN_MULTIPLE2 + " INTEGER NOT NULL)";
        //create database
        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
