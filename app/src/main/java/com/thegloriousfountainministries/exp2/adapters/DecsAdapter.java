package com.thegloriousfountainministries.exp2.adapters;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.DescClass;

import java.util.ArrayList;

/**
 * Created by My HP on 11/9/2017.
 */

public class DecsAdapter extends ArrayAdapter<DescClass> {
    private Activity context;
    private int resource;
    ArrayList<DescClass> listImage;
    public DecsAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull ArrayList<DescClass> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listImage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DescClass descClass = getItem(position);

        LayoutInflater inflater = context.getLayoutInflater();
        View v  = inflater.inflate(resource, null);
        TextView title ,desc;
        FrameLayout layout;
        ImageView img;

        title = (TextView)v.findViewById(R.id.title);
        desc = (TextView)v.findViewById(R.id.desc);
        layout = (FrameLayout)v.findViewById(R.id.layout);
        img = (ImageView)v.findViewById(R.id.v1);

        title.setText(listImage.get(position).getTitle());
        desc.setText(listImage.get(position).getDesc());
        layout.setBackgroundColor(listImage.get(position).getBackgroundColor());
        Glide.with(context).load(listImage.get(position).getPics()).into(img);

        return v;
    }
}
