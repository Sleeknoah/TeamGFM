package com.thegloriousfountainministries.exp2.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thegloriousfountainministries.exp2.R;

/**
 * Created by My HP on 10/25/2018.
 */

public class Booking extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return  inflater.inflate(R.layout.booking, container, false);

    }
}
