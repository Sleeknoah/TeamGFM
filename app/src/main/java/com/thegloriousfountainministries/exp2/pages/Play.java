package com.thegloriousfountainministries.exp2.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.adapters.ExploreAdapter;
import com.thegloriousfountainministries.exp2.adapters.ImageListAdapter;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.ImageUploader;
import com.thegloriousfountainministries.exp2.custom.Imagess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.thegloriousfountainministries.exp2.pages.Home.BASE_URL;

/**
 * Created by My HP on 9/1/2017.
 */

public class Play extends Fragment {
    View view;
    private DatabaseReference mDatabaseref;
    private  List<Imagess> imgList;
    private ApiInterface apiInterface;
    RecyclerView list;
    ProgressBar progressBar;


    private ListView lv;
    private ExploreAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getActivity().setTheme(R.style.OurTheme);
//        Intent intent = new Intent(getActivity(),MainActivity.class );
//        startActivity(intent);
        View v = inflater.inflate(R.layout.play, container, false);

        list = (RecyclerView) v.findViewById(R.id.listex);


        progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        imgList = new ArrayList<>();
        getImmages();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
        list.setAdapter(adapter);

//        mDatabaseref = FirebaseDatabase.getInstance().getReference("image");
//
//        mDatabaseref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    imgList.add(ds.getValue(ImageUploader.class));
//                }
//                adapter = new ImageListAdapter(getActivity(), R.layout.explores_card, imgList);
//                list.setAdapter(adapter);
////                sortList(1);
//                adapter.notifyDataSetChanged();
//                progressBar.setVisibility(View.GONE);
//            }
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        return v;
    }

    private void getImmages(){
        getRetrofit(BASE_URL);
        Call<List<Imagess>> call = apiInterface.getImages();
        call.enqueue(new Callback<List<Imagess>>() {
            @Override
            public void onResponse(Call<List<Imagess>> call, Response<List<Imagess>> response) {
                imgList = response.body();
                adapter = new ExploreAdapter(getActivity(), imgList);
                adapter.setBookList(imgList);
                list.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Imagess>> call, Throwable t) {

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


