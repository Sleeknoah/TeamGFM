package com.thegloriousfountainministries.exp2.adapters;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.ChatClass1;

import java.util.ArrayList;

/**
 * Created by My HP on 11/9/2017.
 */

public class ChatAdapter1 extends ArrayAdapter<ChatClass1> {
    private Activity context;
    private int resource;
    ArrayList<ChatClass1> listImage;

    public ChatAdapter1(@NonNull Activity context, @LayoutRes int resource, @NonNull ArrayList<ChatClass1> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listImage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ChatClass1 descClass = getItem(position);

        LayoutInflater inflater = context.getLayoutInflater();
        View v  = inflater.inflate(resource, null);
        TextView name ,first, number, message, time, am;


        name = (TextView)v.findViewById(R.id.user1);
        first= (TextView)v.findViewById(R.id.user_firstname);
        number = (TextView)v.findViewById(R.id.counter);
        message = (TextView)v.findViewById(R.id.message_preview);
        time = (TextView)v.findViewById(R.id.timeStamp);
        am = (TextView)v.findViewById(R.id.timeperiod);

        name.setText(listImage.get(position).getName());
        first.setText(listImage.get(position).getFirst_name());
        number.setText(listImage.get(position).getNumber());
        message.setText(listImage.get(position).getMessage2());
        time.setText(listImage.get(position).getTime());
        am.setText(listImage.get(position).getTimePeriod());

        //desc.setText(listImage.get(position).getMessage());



        return v;
    }
}
