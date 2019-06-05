package com.thegloriousfountainministries.exp2.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.BooksModel;
import com.thegloriousfountainministries.exp2.custom.Imagess;
import com.thegloriousfountainministries.exp2.data.CartDb;
import com.thegloriousfountainministries.exp2.data.FavContract;
import com.thegloriousfountainministries.exp2.data.FavDb;
import com.thegloriousfountainministries.exp2.pages.Buy;

import java.util.List;

/**
 * Created by My HP on 3/31/2017.
 */

  public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.MyViewHolder> {

    private Context mContext;
    private  List<Imagess> albumList;
    Imagess album;
    RelativeLayout loader;

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView p1, p2, p3, p4, p5, p6;


        public MyViewHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            p1 = (ImageView) itemView.findViewById(R.id.p1);
            p2 = (ImageView) itemView.findViewById(R.id.p2);
            p3 = (ImageView) itemView.findViewById(R.id.p3);
            p4 = (ImageView) itemView.findViewById(R.id.p4);
            p5 = (ImageView) itemView.findViewById(R.id.p5);
            p6 = (ImageView) itemView.findViewById(R.id.p6);
        }
    }



    public ExploreAdapter(Context mContext, List<Imagess> albumList) {
        this.mContext = mContext;
        this.albumList =albumList;
    }
    public void setBookList(List<Imagess> movieList) {
        this.albumList = movieList;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View viewItem = LayoutInflater.from(parent. getContext()).inflate(R.layout.media, parent, false);
        return new MyViewHolder(viewItem);
    }




    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        album = albumList.get(position);
        Glide.with(mContext).load(album.getP1()).into(holder.p1);
        Glide.with(mContext).load(album.getP2()).into(holder.p2);
        Glide.with(mContext).load(album.getP3()).into(holder.p3);
        Glide.with(mContext).load(album.getP4()).into(holder.p4);
        Glide.with(mContext).load(album.getP5()).into(holder.p5);
        Glide.with(mContext).load(album.getP6()).into(holder.p6);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }



}
