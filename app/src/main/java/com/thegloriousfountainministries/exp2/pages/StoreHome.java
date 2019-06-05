package com.thegloriousfountainministries.exp2.pages;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.adapters.BooksAdapter;
import com.thegloriousfountainministries.exp2.adapters.HlistAdapters;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.BlogModel;
import com.thegloriousfountainministries.exp2.custom.BooksModel;
import com.thegloriousfountainministries.exp2.custom.HlistModel;
import com.thegloriousfountainministries.exp2.data.MakeApiCall;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.thegloriousfountainministries.exp2.pages.Home.BASE_URL;


public class StoreHome extends Fragment {
    RecyclerView recyclerView, recyclerViews;
    private BooksAdapter adapter;
    private List<BooksModel> albumList;
    MakeApiCall makeApiCall;
    ApiInterface apiInterface;
    List<HlistModel> hlistModels;
    HlistAdapters adapters;
    public StoreHome() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_blank, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyc);
        makeApiCall = new MakeApiCall(getActivity());
        recyclerViews = (RecyclerView) v.findViewById(R.id.hlist);
        hlistModels = new ArrayList<>();
        adapters = new HlistAdapters(getActivity(), hlistModels);
        albumList =new ArrayList<>();
        adapter = new BooksAdapter(getActivity(), albumList);
        RecyclerView.LayoutManager man = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViews.setLayoutManager(man);
        recyclerViews.setAdapter(adapters);
        login();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(0));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        prepareHlist();


        return v;
    }

    private void prepareHlist() {
        hlistModels.add(new HlistModel("Kabod Encounter"));
        hlistModels.add(new HlistModel("Light of The World"));
        hlistModels.add(new HlistModel("Glory in the House"));
        hlistModels.add(new HlistModel("Chronicles of Glory"));
        hlistModels.add(new HlistModel("Glory Dawn"));

        adapters.notifyDataSetChanged();
    }
    private void login(){
        getRetrofit(BASE_URL);
        Call<List<BooksModel>> call = apiInterface.albums();
        call.enqueue(new Callback<List<BooksModel>>() {
            @Override
            public void onResponse(Call<List<BooksModel>> call, Response<List<BooksModel>> response) {
                albumList = response.body();
                adapter.setBookList(albumList);
                //loader2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<BooksModel>> call, Throwable t) {
                //Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                login();
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


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
