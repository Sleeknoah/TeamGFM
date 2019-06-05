package com.thegloriousfountainministries.exp2.adapters;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
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
import com.thegloriousfountainministries.exp2.custom.BlogModel;
import com.thegloriousfountainministries.exp2.custom.BooksModel;
import com.thegloriousfountainministries.exp2.data.CartContract;
import com.thegloriousfountainministries.exp2.data.CartDb;
import com.thegloriousfountainministries.exp2.data.FavContract;
import com.thegloriousfountainministries.exp2.data.FavDb;
import com.thegloriousfountainministries.exp2.pages.Buy;
import com.thegloriousfountainministries.exp2.pages.Cart;
import com.thegloriousfountainministries.exp2.pages.Store;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My HP on 3/31/2017.
 */

  public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {

    private Context mContext;
    private  List<BooksModel> albumList;
    char naira = '\u20A6';
    CartDb helper;
    FavDb helper2;
    BooksModel album;
    RelativeLayout loader;

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView detials;
        public TextView title_book;
        public ImageView thumbnail, fav;
        public Button desc_store;
        public TextView price;
        public TextView descUrl, id;
        public TextView discount;
        public TextView naira;
        public TextView naira2;
        public RelativeLayout loadder;


        public MyViewHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            title_book =(TextView)itemView.findViewById(R.id.store_text);
            thumbnail = (ImageView)itemView.findViewById(R.id.product);
            detials =(TextView)itemView.findViewById(R.id.store_details);
            desc_store = (Button) itemView.findViewById(R.id.button);
            price =(TextView)itemView.findViewById(R.id.price);
            discount =(TextView)itemView.findViewById(R.id.discount);
            descUrl =(TextView)itemView.findViewById(R.id.status);
            id =(TextView)itemView.findViewById(R.id.id);
            naira =(TextView)itemView.findViewById(R.id.naira);
            naira2 =(TextView)itemView.findViewById(R.id.dis_naira);
            loadder = (RelativeLayout) itemView.findViewById(R.id.loadder);
        }
    }



    public BooksAdapter(Context mContext, List<BooksModel> albumList) {
        this.mContext = mContext;
        this.albumList =albumList;
    }
    public void setBookList(List<BooksModel> movieList) {
        this.albumList = movieList;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View viewItem = LayoutInflater.from(parent. getContext()).inflate(R.layout.store_card, parent, false);
        return new MyViewHolder(viewItem);
    }




    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        album = albumList.get(position);
        holder.title_book.setText(album.getBook_title());
        Glide.with(mContext).load("http://192.168.2.132/store/" + album.getThumbnail()).into(holder.thumbnail);
        holder.id.setText(album.getId());
        holder.detials.setText(album.getDetails());
        holder.descUrl.setText(album.getDesc_store());
        holder.price.setText(album.getPrice());
        holder.discount.setPaintFlags(holder.discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.naira.setText(""+ naira);
        holder.naira2.setText(""+naira);
        holder.naira2.setPaintFlags(holder.naira2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        String[] proj ={
                FavContract.FavColumn._ID,
                FavContract.FavColumn.COLUMN_NAME,
                FavContract.FavColumn.COLUMN_IMAGE2,
                FavContract.FavColumn.COLUMN_price2,
                FavContract.FavColumn.COLUMN_QUANTITY2,
                FavContract.FavColumn.COLUMN_MULTIPLE2
                                };


        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   /*add the intent to play online*/
                  String url = "";
                Intent intent = new Intent(mContext, Buy.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,
//                            holder.thumbnail,
//                            holder.thumbnail.getTransitionName()).toBundle();
//                    startActivity(intent, bundle);
                    intent.putExtra("Title", holder.title_book.getText().toString());
                    intent.putExtra("Details", holder.detials.getText().toString());
                    intent.putExtra("Thumb", "http://192.168.2.132/store/" + album.getThumbnail());
                    intent.putExtra("Price", holder.price.getText().toString());
                    intent.putExtra("desc", holder.descUrl.getText().toString());
                    mContext.startActivities(new Intent[]{intent});
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else{
                    mContext.startActivity(intent);
                    //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }

        });



    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }



}
