package com.thegloriousfountainministries.exp2.data;

import android.provider.BaseColumns;

/**
 * Created by My HP on 9/14/2017.
 */

public final class CartContract {
    private CartContract(){}

    public static abstract class CartColumn implements BaseColumns {
        public static final String TABLE_NAME = "cart";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ITEM_NAME = "name";
        public static final String COLUMN_price = "price";
        public static final String COLUMN_IMAGE ="image";
        public static final String COLUMN_QUANTITY ="quantity";
        public static final String COLUMN_MULTIPLE ="multi";
        public static final String COLUMN_FAVORITE ="multi";
    }
}
