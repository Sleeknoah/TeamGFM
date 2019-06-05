package com.thegloriousfountainministries.exp2.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.ChatClas;
import com.thegloriousfountainministries.exp2.custom.ChatClass;
import com.thegloriousfountainministries.exp2.pages.Chat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My HP on 11/9/2017.
 */

public class ChatAdapter extends ArrayAdapter<ChatClass> {
    private Activity context;
    private int resource;
    String responses;
    List<ChatClass> listImage;
    public static ApiInterface apiInterface;
    public static final String BASE_URL2 = "https://chimdike.000webhostapp.com/";

    public ChatAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<ChatClass> objects, @NonNull String responses) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listImage = objects;
        this.responses= responses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ChatClass descClass = getItem(position);

        LayoutInflater inflater = context.getLayoutInflater();
        View v  = inflater.inflate(resource, null);
        TextView title ,desc, am;
        final ImageView stat;
        CardView card1, card2;


        title = (TextView)v.findViewById(R.id.mymessage);
        desc = (TextView)v.findViewById(R.id.message2);
        card1 =(CardView)v.findViewById(R.id.carde);
        card2 = (CardView)v.findViewById(R.id.card2);
        stat = (ImageView)v.findViewById(R.id.status) ;
        Drawable drawableCompat = ContextCompat.getDrawable(context, R.drawable.ic_check_black_24dp);

              if (listImage.get(position).messageType.equals("1")){
                  card1.setVisibility(View.VISIBLE);
                  card2.setVisibility(View.INVISIBLE);
                  title.setText(listImage.get(position).getMessage());
              }else if (listImage.get(position).messageType.equals("0")){
                  card1.setVisibility(View.INVISIBLE);
                  card2.setVisibility(View.VISIBLE);
                  desc.setText(listImage.get(position).getMessage());
              }



        if (responses.equals("Sent")){
            //display check icon
            stat.setImageResource(R.drawable.ic_check_black_24dp);
        }

//      title.setCompoundDrawablesRelative(null, null,drawableCompat, null);
//      desc= (TextView)v.findViewById(R.id.time2);
//      am = (TextView)v.findViewById(R.id.ampm);


        //desc.setText(listImage.get(position).getMessage());

        return v;
    }
    private void getRetrofit(String url1){
        //Build Retrofit and connect to interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url1)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        apiInterface = retrofit.create(ApiInterface.class);

    }

}
