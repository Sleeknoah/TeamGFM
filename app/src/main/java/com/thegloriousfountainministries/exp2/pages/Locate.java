package com.thegloriousfountainministries.exp2.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thegloriousfountainministries.exp2.R;

/**
 * Created by My HP on 9/2/2017.
 */

public class Locate extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.locate, container, false);

        /*ImageView imageView = (ImageView)v.findViewById(R.id.home);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), Locate2.class);
                //startActivity(intent);
            }
        });*/
        return  v;
    }
}
