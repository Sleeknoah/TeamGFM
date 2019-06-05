package com.thegloriousfountainministries.exp2.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.bumptech.glide.Glide;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.AddCart;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.custom.DeleteCart;
import com.thegloriousfountainministries.exp2.custom.TrackModel;
import com.thegloriousfountainministries.exp2.data.CartContract;
import com.thegloriousfountainministries.exp2.data.CartDb;
import com.thegloriousfountainministries.exp2.pages.Cart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My HP on 11/9/2017.
 */

public class CartAdapter extends CursorAdapter {
    CartDb hep;
    ApiInterface apiInterface;
    Context context;
    CounterFab fab, fab2;
    String idd;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public CartAdapter(Context context, Cursor c) {
        super(context, c, 0);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartmodel, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView title3, price2, qua, naira3, id2;
        ImageView imagee = (ImageView) view.findViewById(R.id.item_image);
        ImageView del = (ImageView) view.findViewById(R.id.del);
        char naira = '\u20A6';
        hep = new CartDb(context);

        pref = context.getSharedPreferences("count", context.MODE_PRIVATE);
        title3 = (TextView) view.findViewById(R.id.title3);
        price2 = (TextView) view.findViewById(R.id.mprice);
        qua = (TextView) view.findViewById(R.id.qua);
        naira3 = (TextView) view.findViewById(R.id.naira3);
        id2 = (TextView) view.findViewById(R.id.id);

        // Extract properties from cursor
        final String title = cursor.getString(cursor.getColumnIndexOrThrow(CartContract.CartColumn.COLUMN_ITEM_NAME));
        String price = cursor.getString(cursor.getColumnIndexOrThrow(CartContract.CartColumn.COLUMN_MULTIPLE));
        String image = cursor.getString(cursor.getColumnIndexOrThrow(CartContract.CartColumn.COLUMN_IMAGE));
        String qua2 = cursor.getString(cursor.getColumnIndexOrThrow(CartContract.CartColumn.COLUMN_QUANTITY));
        idd = cursor.getString(cursor.getColumnIndexOrThrow(CartContract.CartColumn._ID));
        // Populate fields with extracted properties
        title3.setText(title);
        price2.setText(price);
        qua.setText(qua2);
        Glide.with(context).load(image).placeholder(R.drawable.ic_music_note_black_24dp).into(imagee);
        naira3.setText(""+naira);
        id2.setText(idd);



        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this item?")
                        .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                login("2", title);
                            }
                        }).setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }


    private void login(String id, String title){
        getRetrofit("http://192.168.2.132/");
        Call<DeleteCart> call = apiInterface.add(id,title);
        call.enqueue(new Callback<DeleteCart>() {
            @Override
            public void onResponse(Call<DeleteCart> call, Response<DeleteCart> response) {
                String res = response.body().getRes();
                if (res.equals("deleted")){
                    //loadder.setVisibility(View.INVISIBLE);
                    Toast.makeText(context, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                    SQLiteDatabase db = hep.getWritableDatabase();
                    db.delete(CartContract.CartColumn.TABLE_NAME, CartContract.CartColumn._ID +"=" + idd, null);
                    context.startActivity(new Intent(context, Cart.class));
                    ((Activity)context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    ((Activity)context).finish();
                    getdatabasecount();
                }
                if (res.equals("error")){
                    Toast.makeText(context, "Error adding to cart. Try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteCart> call, Throwable t) {
                Toast.makeText(context, "An unknown error has occurred. Please try again later", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
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
        SQLiteDatabase db = hep.getReadableDatabase();
//query the database columns
        Cursor cursor = db.rawQuery("SELECT * FROM " + CartContract.CartColumn.TABLE_NAME, null);
        //try/finally block
        try {
            //set the text to getCount of db query
            editor = pref.edit();
            editor.putInt("ccount", cursor.getCount());
            editor.commit();


        } finally {
            //always close cursor after reading from it
            cursor.close();
        }
    }

}