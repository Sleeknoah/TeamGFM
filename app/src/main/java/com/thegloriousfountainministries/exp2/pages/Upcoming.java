package com.thegloriousfountainministries.exp2.pages;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.adapters.UpAdapter;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.EventModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My HP on 10/25/2018.
 */

public class Upcoming extends Fragment{
    private LinearLayout dotsLayout;
    private TextView[] dots;
    RecyclerView recyclerView;
    int dotlength, current;
    UpAdapter adapter;
    ApiInterface apiInterface;
    private static final String BASE_URL = "https://chimdike.000webhostapp.com/";
    List<EventModel> eventList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.upcoming, container, false);

        //Declare all views to ids
        dotsLayout = (LinearLayout) v.findViewById(R.id.indicators);
        recyclerView = (RecyclerView) v.findViewById(R.id.upc);
        current = 0;




        eventList =new ArrayList<>();
        eventList.add(new EventModel("Passion and Glory", "25th to 27th of March, 2018", "3", "2000", "https://pbs.twimg.com/media/DeCmmsNW0AEEOPu.jpg"));
        eventList.add(new EventModel("Worship Conference", "25th to 27th of July, 2018", "3", "2500", "https://pbs.twimg.com/media/DeCmmsNW0AEEOPu.jpg"));
        eventList.add(new EventModel("Moment of Glory", "30th to 1st of October, 2018", "8", "1557", "https://pbs.twimg.com/media/DeCmmsNW0AEEOPu.jpg"));
        eventList.add(new EventModel("The Encounter", "25th to 27th of December, 2018", "3", "4000", "https://pbs.twimg.com/media/DeCmmsNW0AEEOPu.jpg"));

        adapter = new UpAdapter(getActivity(), eventList);
        RecyclerView.LayoutManager man = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(man);
        recyclerView.setAdapter(adapter);
        // Assign length of dots to be the number of items in the list
        dotlength = adapter.getItemCount();


        //add dots
        //addBottomDots(0);


        //use the listener


        return  v;

    }
    //add dots to view
    private void addBottomDots(int currentPage) {
        // New instance of Textview array and specify the length of the array to be length of dots
        dots = new TextView[dotlength];


        dotsLayout.removeAllViews();
        for (int i = 0; i < dotlength; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);
            dots[i].setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            dotsLayout.addView(dots[i]);
        }

        if (dotlength > 0)
            dots[currentPage].setText(Html.fromHtml("&#8226;"));
        dots[currentPage].setTextSize(40);
        dots[currentPage].setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

    }

    // Perform call
    private void performCall(){
        getRetrofit(BASE_URL);
        Call<List<EventModel>> call = apiInterface.event();
        call.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {
                eventList = response.body();
                adapter.setBlogList(eventList);
            }

            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t) {
                performCall();
            }
        });
    }
    //Initialize Retrofit
    private void getRetrofit(String url1){
        //Build Retrofit and connect to interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url1)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }


}
