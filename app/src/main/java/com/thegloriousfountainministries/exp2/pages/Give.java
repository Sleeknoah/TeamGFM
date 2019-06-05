package com.thegloriousfountainministries.exp2.pages;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.thegloriousfountainministries.exp2.R;

import java.util.ArrayList;

/**
 * Created by My HP on 9/1/2017.
 */

public class Give extends Fragment {
    Spinner spinner, spinner2;
    Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.partner, container, false);
        mContext = getActivity();
      spinner  = (Spinner)v.findViewById(R.id.spinner);
        spinner2  = (Spinner)v.findViewById(R.id.money);
        char naira = '\u20A6';
        char dollar = '\u0024';
        char pounds = '\u00A3';
        char euros = '\u20ac';
        char yens = '\u00A5';
        ArrayList<String> items = new ArrayList<>();
        items.add("Financial Partnership");
        items.add("Prayer Partnership");
        items.add("Professional Partnership");
        items.add("Partner with YouthAblaze");
        items.add("Missions Partnership");
        items.add("Retreat Center");
        items.add("Benevolence");
        items.add("Others");
        ArrayList<String> items2 = new ArrayList<>();
        items2.add("Naira " + naira);
        items2.add("US Dollars " + dollar);
        items2.add("Pounds " + pounds);
        items2.add("Euros " + euros);
        items2.add("Yens " + yens);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, items2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//        if (parent.getItemAtPosition(position)== 1){
//            Toast.makeText(mContext, "Okay na", Toast.LENGTH_SHORT).show();
//        }else if (parent.getItemAtPosition(position)== 2){
//            Toast.makeText(mContext, "Okay na2", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});



        return v;
    }

}
