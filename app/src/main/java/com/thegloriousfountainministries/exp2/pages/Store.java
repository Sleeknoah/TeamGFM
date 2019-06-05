package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.andremion.counterfab.CounterFab;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.data.CartContract;
import com.thegloriousfountainministries.exp2.data.CartDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My HP on 12/7/2017.
 */

public class Store extends AppCompatActivity{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    CounterFab fab;
    CartDb helper;

    private  static final int LOADER_URI = 0;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        helper = new CartDb(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        SharedPreferences prefs = getSharedPreferences("count", MODE_PRIVATE);;
        SharedPreferences.Editor editor = prefs.edit();
        i = prefs.getInt("ccount", 0);


        fab = (CounterFab)findViewById(R.id.cart);
        getdatabasecount();
        fab.setCount(i);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Store.this, Cart.class));
            }
        });

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containers);
        setupViewPager(mViewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StoreHome(), "ALBUMS");
        adapter.addFragment(new Media(), "SONGS");
        adapter.addFragment(new Fashion(), "SALE");
        adapter.addFragment(new Fav(), "FAVORITE");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    private void getdatabasecount() {
        //get helper class and pass context

//create the database
        SQLiteDatabase db = helper.getReadableDatabase();
//query the database columns
        Cursor cursor = db.rawQuery("SELECT * FROM " + CartContract.CartColumn.TABLE_NAME, null);
        //try/finally block
        try {
            //set the text to getCount of db query
            fab.setCount(cursor.getCount());


        } finally {
            //always close cursor after reading from it
            cursor.close();
        }
    }
}
