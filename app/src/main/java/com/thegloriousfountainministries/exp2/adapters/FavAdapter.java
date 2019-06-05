package com.thegloriousfountainministries.exp2.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.data.CartContract;
import com.thegloriousfountainministries.exp2.data.CartDb;
import com.thegloriousfountainministries.exp2.data.FavContract;
import com.thegloriousfountainministries.exp2.data.FavDb;
import com.thegloriousfountainministries.exp2.pages.Cart;
import com.thegloriousfountainministries.exp2.pages.Store;

/**
 * Created by My HP on 11/9/2017.
 */

public class FavAdapter extends CursorAdapter {
    FavDb hep;
    public FavAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.favmodel, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        TextView title3, price2, dis, naira3, id2;
        ImageView imagee = (ImageView) view.findViewById(R.id.item_image);
        ImageView del = (ImageView) view.findViewById(R.id.del2);
        ImageView add = (ImageView) view.findViewById(R.id.add_to_cart3);
        char naira = '\u20A6';
        hep = new FavDb(context);

        title3 = (TextView) view.findViewById(R.id.title4);
        price2 = (TextView) view.findViewById(R.id.mprice2);
        //dis = (TextView) view.findViewById(R.id.mdiscount);
        naira3 = (TextView) view.findViewById(R.id.naira3);
        id2 = (TextView) view.findViewById(R.id.id);

        // Extract properties from cursor
        final String title = cursor.getString(cursor.getColumnIndexOrThrow(FavContract.FavColumn.COLUMN_NAME));
        String price = cursor.getString(cursor.getColumnIndexOrThrow(FavContract.FavColumn.COLUMN_MULTIPLE2));
        String image = cursor.getString(cursor.getColumnIndexOrThrow(FavContract.FavColumn.COLUMN_IMAGE2));
        final String id = cursor.getString(cursor.getColumnIndexOrThrow(FavContract.FavColumn._ID));
        String qua = cursor.getString(cursor.getColumnIndexOrThrow(FavContract.FavColumn.COLUMN_QUANTITY2));
        String multi = cursor.getString(cursor.getColumnIndexOrThrow(FavContract.FavColumn.COLUMN_MULTIPLE2));
        // Populate fields with extracted properties
        title3.setText(title);
        price2.setText(price);
        Glide.with(context).load(image).into(imagee);


      add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this item?")
                        .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                                SQLiteDatabase db = hep.getWritableDatabase();
                                db.delete(FavContract.FavColumn.TABLE_NAME, FavContract.FavColumn._ID +"=" + id, null);
                            context.startActivity(new Intent(context, Store.class));
                                ((Activity)context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                ((Activity)context).finish();
                            }
                        }).setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

}