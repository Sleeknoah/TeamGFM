package com.thegloriousfountainministries.exp2.adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.BlogModel;
import com.thegloriousfountainministries.exp2.custom.EventModel;

import java.util.List;

/**
 * Created by My HP on 3/31/2017.
 */

  public class UpAdapter extends RecyclerView.Adapter<UpAdapter.MyViewHolder> {

    private Context mContext;
    private  List<EventModel> blogList;


    public  class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView eventTitle, eventDate, eventTime, going;
        public FloatingActionButton viewEvent;
        public ImageView eventImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            eventTitle =(TextView)itemView.findViewById(R.id.eventTitle);
            eventDate =(TextView)itemView.findViewById(R.id.eventDate);
            eventTime =(TextView)itemView.findViewById(R.id.eventTime);
            going =(TextView)itemView.findViewById(R.id.going);
            eventImage = (ImageView) itemView.findViewById(R.id.placeholder);
        }
    }



    public UpAdapter(Context mContext, List<EventModel> blogList) {
        this.mContext = mContext;
        this.blogList =blogList;
    }
    public void setBlogList(List<EventModel> movieList) {
        this.blogList = movieList;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View viewItem = LayoutInflater.from(parent. getContext()).inflate(R.layout.upcard, parent, false);
        return new MyViewHolder(viewItem);
    }




    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final EventModel album = blogList.get(position);
        holder.eventTitle.setText(album.getTitle());
        holder.eventDate.setText(album.getDate());
        holder.eventTime.setText(album.getTime());
        holder.going.setText(album.getGoing());
        Picasso.with(mContext).load(album.getEvent_pic()).fit().into(holder.eventImage);
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

}
