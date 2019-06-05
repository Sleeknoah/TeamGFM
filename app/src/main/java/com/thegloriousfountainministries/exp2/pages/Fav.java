package com.thegloriousfountainministries.exp2.pages;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.adapters.CartAdapter;
import com.thegloriousfountainministries.exp2.adapters.FavAdapter;
import com.thegloriousfountainministries.exp2.data.CartContract;
import com.thegloriousfountainministries.exp2.data.FavContract;
import com.thegloriousfountainministries.exp2.data.FavDb;

/**
 * Created by My HP on 12/20/2017.
 */

public class Fav extends Fragment {
    FavDb mHelper;
    ListView list;
    Cursor c;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fav, container, false);

        list = (ListView)v.findViewById(R.id.fav_list);

        getDatabaseColunms();

        return v;
    }
    private void getDatabaseColunms() {
        mHelper = new FavDb(getActivity());
        SQLiteDatabase db =  mHelper.getReadableDatabase();

        String[] projection = {
                FavContract.FavColumn._ID,
                FavContract.FavColumn.COLUMN_NAME,
                FavContract.FavColumn.COLUMN_IMAGE2,
                FavContract.FavColumn.COLUMN_price2,
                FavContract.FavColumn.COLUMN_QUANTITY2,
                FavContract.FavColumn.COLUMN_MULTIPLE2
        };
        c  = db.query(
                FavContract.FavColumn.TABLE_NAME,
                projection,
                null, null, null, null, null);



        FavAdapter adapter = new FavAdapter(getActivity(), c);

        list.setAdapter(adapter);

    }
}
