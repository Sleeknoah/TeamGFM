package com.thegloriousfountainministries.exp2.data;

import android.provider.BaseColumns;

/**
 * Created by My HP on 9/14/2017.
 */

public final class FavContract {
    private FavContract(){}

    public static abstract class FavColumn implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_price2 = "price";
        public static final String COLUMN_IMAGE2 ="image";
        public static final String COLUMN_QUANTITY2 ="quantity";
        public static final String COLUMN_MULTIPLE2 ="multi";
    }
}
