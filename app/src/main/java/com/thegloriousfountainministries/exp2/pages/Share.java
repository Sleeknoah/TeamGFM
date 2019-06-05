package com.thegloriousfountainministries.exp2.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ListViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.adapters.DownloadAdapter;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.PaidModel;
import com.thegloriousfountainministries.exp2.custom.TrackModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.thegloriousfountainministries.exp2.pages.Home.BASE_URL;

/**
 * Created by My HP on 12/22/2017.
 */

public class Share extends AppCompatActivity{

    RecyclerView listView;
    List<PaidModel> paidModelList;
    DownloadAdapter adapter;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share);

        listView = findViewById(R.id.downloadlist);
        paidModelList = new ArrayList<>();
        adapter = new DownloadAdapter(getApplicationContext(), paidModelList);

        getList("2");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);
    }
    private void getList(String id){
        getRetrofit(BASE_URL);
        Call<List<PaidModel>> call = apiInterface.paid(id);
        call.enqueue(new Callback<List<PaidModel>>() {
            @Override
            public void onResponse(Call<List<PaidModel>> call, Response<List<PaidModel>> response) {
                paidModelList = response.body();
                adapter.setBookList(paidModelList);
            }

            @Override
            public void onFailure(Call<List<PaidModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An unknown error has occurred. Please try again later", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRetrofit(String url1){
        //Build Retrofit and connect to interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url1)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        apiInterface = retrofit.create(ApiInterface.class);

    }

}
