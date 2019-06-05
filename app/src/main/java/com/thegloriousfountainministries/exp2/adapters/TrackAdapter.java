package com.thegloriousfountainministries.exp2.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.AddCart;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.BooksModel;
import com.thegloriousfountainministries.exp2.custom.TrackModel;
import com.thegloriousfountainministries.exp2.data.CartContract;
import com.thegloriousfountainministries.exp2.data.CartDb;
import com.thegloriousfountainministries.exp2.data.FavContract;
import com.thegloriousfountainministries.exp2.data.FavDb;
import com.thegloriousfountainministries.exp2.pages.Buy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My HP on 3/31/2017.
 */

  public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.MyViewHolder> {
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    ApiInterface apiInterface;
    private Context mContext;
    private  List<TrackModel> albumList;

    CartDb helper;
    TrackModel album;
    char naira = '\u20A6';
    RelativeLayout loader;

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView price;
        //public TextView timee;
        public ImageView buy;



        public MyViewHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            title =(TextView)itemView.findViewById(R.id.tracktitle);
            //timee =(TextView)itemView.findViewById(R.id.tracktime);
            price =(TextView)itemView.findViewById(R.id.trackprice);
            buy = (ImageView) itemView.findViewById(R.id.trackbuy);

        }
    }



    public TrackAdapter(Context mContext, List<TrackModel> albumList) {
        this.mContext = mContext;
        this.albumList =albumList;
    }
    public void setBookList(List<TrackModel> movieList) {
        this.albumList = movieList;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View viewItem = LayoutInflater.from(parent. getContext()).inflate(R.layout.tracks, parent, false);
        return new MyViewHolder(viewItem);
    }




    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        album = albumList.get(position);
        holder.title.setText(album.getTitlee());
        //holder.timee.setText(album.getTimee());
        holder.price.setText(""+ naira + album.getPrice().replaceAll("\\.0*$", ""));


        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login("2", holder.title.getText().toString(), "single", holder.price.getText().toString().replace(naira, '0'), "null",  album.getUrl());
            }
        });
    }



    @Override
    public int getItemCount() {
        return albumList.size();
    }


    private void login(String id, final String title, String type, final String price, final String image, String dataurl){
        getRetrofit("http://192.168.2.132/");
        Call<AddCart> call = apiInterface.add(id,title, type, price, image, dataurl);
        call.enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                String res = response.body().getRes();
                if (res.equals("Added")){
                    //loadder.setVisibility(View.INVISIBLE);
                    Toast.makeText(mContext, "Item added successfully", Toast.LENGTH_SHORT).show();
                    helper = new CartDb(mContext);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues value = new ContentValues();
                    value.put(CartContract.CartColumn.COLUMN_ITEM_NAME, title);
                    value.put(CartContract.CartColumn.COLUMN_IMAGE, image);
                    value.put(CartContract.CartColumn.COLUMN_price, Integer.parseInt(price));
                    value.put(CartContract.CartColumn.COLUMN_QUANTITY, 1);
                    value.put(CartContract.CartColumn.COLUMN_MULTIPLE, Integer.parseInt(price));

                    long idRow = db.insert(CartContract.CartColumn.TABLE_NAME, null, value);
                    Log.v("IdRow", "Id Count" + idRow);
                    getdatabasecount();
                }
                if (res.equals("already")){
                    Toast.makeText(mContext, "Item already in cart.", Toast.LENGTH_SHORT).show();
                }
                if (res.equals("error")){
                    Toast.makeText(mContext, "Error adding to cart. Try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {
                Toast.makeText(mContext, "An unknown error has occurred. Please try again later", Toast.LENGTH_SHORT).show();
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void getdatabasecount() {
        //get helper class and pass context
        helper = new CartDb(mContext);
//create the database
        SQLiteDatabase db = helper.getReadableDatabase();
//query the database columns
        Cursor cursor = db.rawQuery("SELECT * FROM " + CartContract.CartColumn.TABLE_NAME, null);
        //try/finally block
        try {
            //set the text to getCount of db query
            pref = mContext.getSharedPreferences("count", Context.MODE_PRIVATE);
            editor = pref.edit();
            editor.putInt("ccount", cursor.getCount());
            editor.commit();


        } finally {
            //always close cursor after reading from it
            cursor.close();
        }
    }

}
