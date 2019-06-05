package com.thegloriousfountainministries.exp2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by My HP on 9/14/2017.
 */

public class CartDb extends SQLiteOpenHelper {
    //create srings for name and version of database
    private static final String DATABASE_NAME = "cart.db";
    private static final int DATABASE_VERSION = 1;

    public CartDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create string to add columns

         String CREATE_CART_TABLE = "CREATE TABLE " + CartContract.CartColumn.TABLE_NAME + " ("
                 + CartContract.CartColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                 + CartContract.CartColumn.COLUMN_ITEM_NAME + " TEXT NOT NULL, "
                 + CartContract.CartColumn.COLUMN_IMAGE + " TEXT NOT NULL, "
                 + CartContract.CartColumn.COLUMN_price + " INTEGER NOT NULL, "
                 + CartContract.CartColumn.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                 + CartContract.CartColumn.COLUMN_MULTIPLE + " INTEGER NOT NULL)";
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
