package com.thegloriousfountainministries.exp2.pages;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.bumptech.glide.Glide;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.adapters.BooksAdapter;
import com.thegloriousfountainministries.exp2.adapters.TrackAdapter;
import com.thegloriousfountainministries.exp2.custom.AddCart;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.BooksModel;
import com.thegloriousfountainministries.exp2.custom.TrackModel;
import com.thegloriousfountainministries.exp2.data.CartContract;
import com.thegloriousfountainministries.exp2.data.CartDb;
import com.thegloriousfountainministries.exp2.data.FavContract;
import com.thegloriousfountainministries.exp2.data.FavDb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.thegloriousfountainministries.exp2.pages.Home.BASE_URL;

/**
 * Created by My HP on 12/7/2017.
 */

public class Buy extends AppCompatActivity {
    FavDb helper2;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Animation boom, down;
    TextView tit, det, pri, dis, nai, item, text2, textv;
    LinearLayout lin;
    CounterFab fab, fab2;
    Intent intent;
    ImageView thumb, increase, reduce, add, fav;
    boolean wat2;
    RecyclerView recyclerView, recyclerViews;
    private TrackAdapter adapter;
    private List<TrackModel> albumList;
    int index = 1;
    CoordinatorLayout layout;
    CartDb helper;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy);
        char naira = '\u20A6';
        helper = new CartDb(this);
        lin = (LinearLayout)findViewById(R.id.options);
        recyclerView = (RecyclerView)findViewById(R.id.re);
        fav = (ImageView)findViewById(R.id.fav_store);
        tit = (TextView)findViewById(R.id.tit);
        det = (TextView)findViewById(R.id.det);
        layout = (CoordinatorLayout)findViewById(R.id.main_content);
        pri = (TextView)findViewById(R.id.price2);
        nai = (TextView)findViewById(R.id.naira2);
        item = (TextView)findViewById(R.id.item_number);
        text2 = (TextView)findViewById(R.id.textt);
        textv = (TextView)findViewById(R.id.textView2);
        albumList =new ArrayList<>();
        adapter = new TrackAdapter(getApplicationContext(), albumList);
        //tit = (TextView)findViewById(R.id.tit);
        fab = (CounterFab) findViewById(R.id.check_cart);
        fab2 = (CounterFab) findViewById(R.id.check_cart2);
        pref = getSharedPreferences("count", MODE_PRIVATE);

        getdatabasecount();
        thumb = (ImageView) findViewById(R.id.backdrop);
        increase = (ImageView) findViewById(R.id.increase);
        reduce = (ImageView) findViewById(R.id.reduce);
        add = (ImageView) findViewById(R.id.add_to_cart);
        boom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up3);
        down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down2);
        lin.setAnimation(boom);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        String text = Integer.toString(index);
        item.setText(text);
        //checkFav();

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fav();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Buy.this, Cart.class));
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Buy.this, Cart.class));
            }
        });
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index= index + 1;
                String text = Integer.toString(index);
                item.setText(text);
            }
        });
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index <= 1){

                }else {
                    index= index - 1;
                    String text = Integer.toString(index);
                    item.setText(text);
                }
            }
        });

        intent = this.getIntent();
        final String image = intent.getExtras().getString("Thumb");
        final String title2 = intent.getExtras().getString("Title");
        String edesc = intent.getExtras().getString("Details");
        final String price = intent.getExtras().getString("Price");
        final String desc = intent.getExtras().getString("desc");
//        String dis = intent.getExtras().getString("Discount");
        Glide.with(getApplicationContext()).load(image).into(thumb);
        tit.setText(title2);
        det.setText(edesc);
        pri.setText(price);
        nai.setText(""+naira);

        getTrack(tit.getText().toString());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("2", title2, "album", price, image, desc);
            }
        });
        initCollapsingToolBar();
    }
    private void initCollapsingToolBar() {
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapse);
        collapsingToolbarLayout.setTitle("");
        final AppBarLayout appBar = (AppBarLayout)findViewById(R.id.appbar2);
        appBar.setExpanded(true);

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange==-1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle("");
                    isShow=true;
                }else if(isShow){
                    collapsingToolbarLayout.setTitle("");
                    appBar.setVisibility(View.VISIBLE);
                    lin.setAnimation(boom);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        Window w = getWindow(); // in Activity's onCreate() for instance
                        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                    }
                    isShow= false;
                }


            }
        });
    }
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
    private void addBible(){
        intent = this.getIntent();
        String image2 = intent.getExtras().getString("Thumb");
        String title2 = intent.getExtras().getString("Title");
        String desc = intent.getExtras().getString("desc");
        String price = intent.getExtras().getString("Price").replaceAll("\\.0*$", "");
        String qua = item.getText().toString();
        int qua3 = Integer.parseInt(qua);
        int pri = Integer.parseInt(price);
        int multi = qua3 * pri ;
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(CartContract.CartColumn.COLUMN_ITEM_NAME, title2);
        value.put(CartContract.CartColumn.COLUMN_IMAGE, image2);
        value.put(CartContract.CartColumn.COLUMN_price, pri);
        value.put(CartContract.CartColumn.COLUMN_QUANTITY, qua3);
        value.put(CartContract.CartColumn.COLUMN_MULTIPLE, multi);

        long idRow = db.insert(CartContract.CartColumn.TABLE_NAME, null, value);
        Log.v("IdRow", "Id Count" + idRow);
        Snackbar snackbar = Snackbar.make(layout, "Successfully Added to Cart", Snackbar.LENGTH_LONG);
        View sb = snackbar.getView();
        TextView textView = (TextView)sb.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }
    private void login(String id, String title, String type, String price, String image, String dataurl){
        getRetrofit(BASE_URL);
        Call<AddCart> call = apiInterface.add(id,title, type, price, image, dataurl);
        call.enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                String res = response.body().getRes();
                if (res.equals("Added")){
                    //loadder.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Item added successfully", Toast.LENGTH_SHORT).show();
                    addBible();
                    getdatabasecount();
                }
                if (res.equals("already")){
                    Toast.makeText(getApplicationContext(), "Item already in cart.", Toast.LENGTH_SHORT).show();
                }
                if (res.equals("error")){
                    Toast.makeText(getApplicationContext(), "Error adding to cart. Try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An unknown error has occurred. Please try again later", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//GET ALL TRACKS OF ALBUM
    private void getTrack(String title){
        getRetrofit(BASE_URL);
        Call<List<TrackModel>> call = apiInterface.track(title);
        call.enqueue(new Callback<List<TrackModel>>() {
            @Override
            public void onResponse(Call<List<TrackModel>> call, Response<List<TrackModel>> response) {
                albumList = response.body();
                adapter.setBookList(albumList);
            }

            @Override
            public void onFailure(Call<List<TrackModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An unknown error has occurred. Please try again later", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void getdatabasecount() {
        //get helper class and pass context

//create the database
        SQLiteDatabase db = helper.getReadableDatabase();
//query the database columns
        Cursor cursor = db.rawQuery("SELECT * FROM " + CartContract.CartColumn.TABLE_NAME, null);
        //try/finally block
        try {
            //set the text to getCount of db query
            fab.setCount(cursor.getCount());
            fab2.setCount(cursor.getCount());
            editor = pref.edit();
            editor.putInt("ccount", cursor.getCount());
            editor.commit();


        } finally {
            //always close cursor after reading from it
            cursor.close();
        }
    }
    private void fav() {
            intent = this.getIntent();
            String image2 = intent.getExtras().getString("Thumb");
            String title2 = intent.getExtras().getString("Title");
            String price = intent.getExtras().getString("Price");
            String qua = item.getText().toString();
            int qua3 = Integer.parseInt(qua);

            int pri = Integer.parseInt(price.replaceAll("\\.0*$", ""));
            int multi = qua3 * pri;
        fav.setImageResource(R.drawable.fav);
        helper2 = new FavDb(this);
        SQLiteDatabase db = helper2.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(FavContract.FavColumn.COLUMN_NAME, title2);
        value.put(FavContract.FavColumn.COLUMN_IMAGE2, image2);
        value.put(FavContract.FavColumn.COLUMN_price2, price);
        value.put(FavContract.FavColumn.COLUMN_QUANTITY2, qua3);
        value.put(FavContract.FavColumn.COLUMN_MULTIPLE2, multi);
        long idRow = db.insert(FavContract.FavColumn.TABLE_NAME, null, value);
        Log.v("IdRow", "Id Count" + idRow);

    }



    private void checkFav(){
        helper2 = new FavDb(this);
        SQLiteDatabase db2 = helper2.getReadableDatabase();
        intent = this.getIntent();
        String title2 = intent.getExtras().getString("Title");
        String image2 = intent.getExtras().getString("Thumb");
//        String[] projection = {
//                FavContract.FavColumn.COLUMN_NAME
//        };
//        String whereClause = FavContract.FavColumn.COLUMN_NAME + " = ?";
//        String[] whereArgs = new String[] {
//              title2
//        };
        String diff = title2;
        String query = "SELECT image FROM favorite" ;
        Cursor c = db2.rawQuery(query , null);

        int inde = c.getColumnIndex(FavContract.FavColumn.COLUMN_NAME);
        String check = c.getColumnName(inde);
        Toast.makeText(this, check, Toast.LENGTH_SHORT).show();
        if (title2.equals(check)){
           fav.setImageResource(R.drawable.fav);
       }
    }
}
