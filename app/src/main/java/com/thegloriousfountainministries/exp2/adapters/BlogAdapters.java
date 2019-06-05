package com.thegloriousfountainministries.exp2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.BlogModel;
import com.thegloriousfountainministries.exp2.custom.HlistModel;

import java.util.List;

/**
 * Created by My HP on 3/31/2017.
 */

  public class BlogAdapters extends RecyclerView.Adapter<BlogAdapters.MyViewHolder> {

    private Context mContext;
    private  List<BlogModel> blogList;


    public  class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title_blog;
        public TextView link_blog;
        public ImageView blog;

        public MyViewHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            title_blog =(TextView)itemView.findViewById(R.id.blog_tite);
            link_blog =(TextView)itemView.findViewById(R.id.blog_link);
            blog = (ImageView) itemView.findViewById(R.id.blog);
        }
    }



    public BlogAdapters(Context mContext, List<BlogModel> blogList) {
        this.mContext = mContext;
        this.blogList =blogList;
    }
    public void setBlogList(List<BlogModel> movieList) {
        this.blogList = movieList;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View viewItem = LayoutInflater.from(parent. getContext()).inflate(R.layout.blog_card, parent, false);
        return new MyViewHolder(viewItem);
    }




    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final BlogModel album = blogList.get(position);
        holder.title_blog.setText(album.getBlogTopics());
        holder.link_blog.setText(album.getBlogLink());
        Glide.with(mContext).load(album.getBlog_pic()).into(holder.blog);
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

}
