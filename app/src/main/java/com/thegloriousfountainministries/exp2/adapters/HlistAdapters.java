package com.thegloriousfountainministries.exp2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.HlistModel;

import java.util.List;

/**
 * Created by My HP on 3/31/2017.
 */

  public class HlistAdapters extends RecyclerView.Adapter<HlistAdapters.MyViewHolder> {

    private Context mContext;
    private  List<HlistModel> albumList;


    public  class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title_book;

        public MyViewHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            title_book =(TextView)itemView.findViewById(R.id.authors);

        }
    }



    public HlistAdapters(Context mContext, List<HlistModel> albumList) {
        this.mContext = mContext;
        this.albumList =albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View viewItem = LayoutInflater.from(parent. getContext()).inflate(R.layout.hlist, parent, false);
        return new MyViewHolder(viewItem);
    }




    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final HlistModel album = albumList.get(position);
        holder.title_book.setText(album.getAuthor());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

}
