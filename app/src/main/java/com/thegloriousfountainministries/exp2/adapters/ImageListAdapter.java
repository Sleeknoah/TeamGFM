package com.thegloriousfountainministries.exp2.adapters;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.Imagess;

import java.util.List;

/**
 * Created by My HP on 12/22/2017.
 */

public class ImageListAdapter extends ArrayAdapter<Imagess> {
    private Activity context;
    Bitmap bm;
    private int resource;
    //List<ImageUploader> objects;
    private List<Imagess> listImage;
    private DatabaseReference mDatabaseref;
    SharedPreferences pref;
    ImageView p1, p2, p3, p4, p5, p6;

    ClipboardManager clipboardManager;
    public ImageListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Imagess> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listImage = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = context.getLayoutInflater();
        View v  = inflater.inflate(resource, null);
        p1 = (ImageView) v.findViewById(R.id.p1);
        p2 = (ImageView) v.findViewById(R.id.p2);
        p3 = (ImageView) v.findViewById(R.id.p3);
        p4 = (ImageView) v.findViewById(R.id.p4);
        p5 = (ImageView) v.findViewById(R.id.p5);
        p6 = (ImageView) v.findViewById(R.id.p6);
//        final EmojiconTextView txtTitle = (EmojiconTextView) v.findViewById(R.id.explore_text);
//        final TextView filter = (TextView) v.findViewById(R.id.filter);
//        final ImageView imageView = (ImageView) v.findViewById(R.id.explore_image);
//        //final ImageView imageView2 = (ImageView) v.findViewById(R.id.upl_image_filter);
//        final Button image = (Button) v.findViewById(R.id.explore_share);
//        final Button learn = (Button) v.findViewById(R.id.explore_learn);
//        //final ImageView star = (ImageView) v.findViewById(R.id.under_feed);
////        ImageView dots = (ImageView)v.findViewById(R.id.dots_menu);
//        EmojiconTextView text = (EmojiconTextView)v.findViewById(R.id.link);


//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, Share.class);
//                intent.putExtra("Link", listImage.get(position).getUrl());
//                intent.putExtra("Filter", listImage.get(position).getFilter());
//                intent.putExtra("Text", listImage.get(position).getName());
//                context.startActivity(intent);
//                /*intent.setAction(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_TEXT, listImage.get(position).getUrl());
//                intent.setType("text/plain");
//                context.startActivity(Intent.createChooser(intent, "Share with"));*/
//            }
//        });
//        imageView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                return false;
//            }
//        });

//        final String link = listImage.get(position).getText();
//        //text.setText(link);
//        learn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, Web.class);
//                intent.putExtra("link", link);
//            }
//        });

//        txtTitle.setText(listImage.get(position).getName());
//        filter.setText(listImage.get(position).getFilter());
//        Glide.with(context).load(listImage.get(position).getUrl()).into(imageView);
//        //star.setImageResource(isButtonClicked? R.drawable.heart_2:R.drawable.heart_1);

        Glide.with(context).load(listImage.get(position).getP1()).into(p1);
        Glide.with(context).load(listImage.get(position).getP2()).into(p2);
        Glide.with(context).load(listImage.get(position).getP3()).into(p3);
        Glide.with(context).load(listImage.get(position).getP4()).into(p4);
        Glide.with(context).load(listImage.get(position).getP5()).into(p5);
        Glide.with(context).load(listImage.get(position).getP6()).into(p6);




        return  v;
    }


    @Nullable
    @Override
    public Imagess getItem(int position) {

        return getItem(getCount() - position - 1);
    }
}
