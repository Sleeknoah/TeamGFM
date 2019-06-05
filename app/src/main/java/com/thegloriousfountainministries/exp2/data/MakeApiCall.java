package com.thegloriousfountainministries.exp2.data;

import android.content.Context;

import com.thegloriousfountainministries.exp2.custom.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My HP on 11/19/2018.
 */

public class MakeApiCall {
    ApiInterface apiInterface;
    Context context;
    private static final String BASE_URL = "http://192.168.2.109/";

    public MakeApiCall(Context context) {
        this.context = context;
    }
    public void getRetrofit(){
        //Build Retrofit and connect to interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }
}