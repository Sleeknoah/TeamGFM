package com.thegloriousfountainministries.exp2.pages;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.adapters.BlogAdapters;
import com.thegloriousfountainministries.exp2.adapters.BooksAdapter;
import com.thegloriousfountainministries.exp2.adapters.DecsAdapter;
import com.thegloriousfountainministries.exp2.adapters.HlistAdapters;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.BlogModel;
import com.thegloriousfountainministries.exp2.custom.BooksModel;
import com.thegloriousfountainministries.exp2.custom.DescClass;
import com.thegloriousfountainministries.exp2.custom.HlistModel;
import com.thegloriousfountainministries.exp2.custom.Login;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My HP on 9/1/2017.
 */

public class Home extends Fragment implements YouTubePlayer.OnInitializedListener,
                                   YouTubePlayer.PlayerStateChangeListener{

    private Context mContext;
    public static final String API_KEY = "AIzaSyBYuc0nYWBwsZ0zx59bRMuzW4xkMTcMHXI";

    Animation boom, rotate, boom2, side, down, side2;
    private float initialX;
    ImageView fb, twitter, whatsapp, gmail;
    ImageView image, image2, ima, ima2, ima3;

    boolean touched;
    LinearLayout loader2, loader3, share;
    RelativeLayout rel;
    ViewFlipper vf, vf2;
    TextView test;
    int currentImage = 1;
    DecsAdapter decsAdapter;
    RecyclerView  recyclerView;
    ApiInterface apiInterface;
    public static final String BASE_URL = "http://192.168.2.104/";


    List<BlogModel> hlistModels;
    BlogAdapters adapters;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.home, container, false);
        share = (LinearLayout) v.findViewById(R.id.share);
        image = (ImageView)v.findViewById(R.id.image);

        YouTubePlayerSupportFragment youTubePlayerView = new YouTubePlayerSupportFragment();
            youTubePlayerView.initialize(API_KEY, this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ya, youTubePlayerView);
        fragmentTransaction.commit();




        //setup recycler views
        recyclerView = (RecyclerView)v.findViewById(R.id.blogs);
        loader2 = (LinearLayout)v.findViewById(R.id.loader2);
//        loader3 = (LinearLayout)v.findViewById(R.id.loader3);

//        recyclerViews = (RecyclerView) v.findViewById(R.id.hlist);
        hlistModels = new ArrayList<>();
        hlistModels.add(new BlogModel("Interview session with Vicki Yohe", "www.thegloriousfountainministries.com", "http://freshangleng.com/articleimg/b47d558a-da90-4f82-9241-14c5fe34579b.jpg"));
        hlistModels.add(new BlogModel("Worship Conference '18", "www.thegloriousfountainministries.com", "https://pbs.twimg.com/media/DeCmmsNW0AEEOPu.jpg"));
        hlistModels.add(new BlogModel("The Worship Revolution", "www.thegloriousfountainministries.com", "https://jscotthusted.files.wordpress.com/2012/06/passion_05_worship_edit.jpg"));
        adapters = new BlogAdapters(getActivity(), hlistModels);
        RecyclerView.LayoutManager man = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(man);
        recyclerView.setAdapter(adapters);

        login();



        rel = (RelativeLayout) v.findViewById(R.id.rel);
        mContext = getActivity();

        test = (TextView)v.findViewById(R.id.test);
        Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(), "fonts/chopinscript.otf");
//        test.setTypeface(custom_font);



        //initialize viewflipper
        vf = (ViewFlipper)v.findViewById(R.id.vf);
        vf.setInAnimation(mContext, R.anim.in_left);
        vf.setOutAnimation(mContext, R.anim.out_left);
        vf.setFlipInterval(5000);
        vf.setAutoStart(true);
        vf.startFlipping();




//         t.schedule(tier, 10000);



        rotate = AnimationUtils.loadAnimation(mContext, R.anim.rotation2);
        boom = AnimationUtils.loadAnimation(mContext, R.anim.boom);
        boom2 = AnimationUtils.loadAnimation(mContext, R.anim.up2);
        side = AnimationUtils.loadAnimation(mContext, R.anim.side);
        side2 = AnimationUtils.loadAnimation(mContext, R.anim.side2);
        down = AnimationUtils.loadAnimation(mContext, R.anim.down);
        share.startAnimation(boom);
        share.setVisibility(View.VISIBLE);
        fb = (ImageView) v.findViewById(R.id.fb);
        twitter = (ImageView) v.findViewById(R.id.twitter);
        whatsapp = (ImageView) v.findViewById(R.id.whatsapp);
        gmail = (ImageView) v.findViewById(R.id.gmail);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touched == false){

                    image.startAnimation(rotate);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            image.setImageResource(R.drawable.add);

                        }
                    }, 500);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            whatsapp.startAnimation(side);
                            twitter.startAnimation(boom2);
                            twitter.setVisibility(View.VISIBLE);
                            whatsapp.setVisibility(View.VISIBLE);
                        }
                    }, 550);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gmail.startAnimation(side);
                            fb.startAnimation(boom2);
                            fb.setVisibility(View.VISIBLE);
                            gmail.setVisibility(View.VISIBLE);

                        }
                    }, 600);
                    touched = true;
                }else {

                    image.startAnimation(rotate);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            image.setImageResource(R.drawable.ic_sharee_white2_48dp);

                        }
                    }, 500);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            twitter.setAnimation(down);
                            fb.setAnimation(down);
                            whatsapp.setAnimation(side2);
                            gmail.setAnimation(side2);
                            fb.setVisibility(View.INVISIBLE);
                            twitter.setVisibility(View.INVISIBLE);
                            whatsapp.setVisibility(View.INVISIBLE);
                            gmail.setVisibility(View.INVISIBLE);
                        }
                    }, 550);


                    touched = false;
                }
            }
        });



        return v;
    }


    @Override
    public void onPause() {
        super.onPause();
        vf.stopFlipping();
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);
        vf.startFlipping();

    }
    private void login(){
        getRetrofit(BASE_URL);
        Call<List<BlogModel>> call = apiInterface.blog();
        call.enqueue(new Callback<List<BlogModel>>() {
            @Override
            public void onResponse(Call<List<BlogModel>> call, Response<List<BlogModel>> response) {
                hlistModels = response.body();
                adapters.setBlogList(hlistModels);
                loader2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<BlogModel>> call, Throwable t) {
                //Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                login();
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


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo("bphMW6FxHp0");

        }

    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }
}


