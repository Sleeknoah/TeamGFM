package com.thegloriousfountainministries.exp2.data;

import android.provider.BaseColumns;

/**
 * Created by My HP on 9/14/2017.
 */

public final class BibleContract {
    private BibleContract(){}

    public static abstract class BibleColumn implements BaseColumns {
        public static final String TABLE_NAME = "bible";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_BOOKS = "books";
        public static final String COLUMN_CONTENT = "content";
    }
}
