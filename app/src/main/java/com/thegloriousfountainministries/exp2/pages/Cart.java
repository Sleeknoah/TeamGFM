package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.adapters.CartAdapter;
import com.thegloriousfountainministries.exp2.data.CartContract;
import com.thegloriousfountainministries.exp2.data.CartDb;

public class Cart extends AppCompatActivity {
    CartDb mHelper;
    ListView list;
    Button checkout;
    TextView ship, price_final;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        checkout = (Button)findViewById(R.id.checkout);
        ship = (TextView)findViewById(R.id.ship);
        price_final = (TextView)findViewById(R.id.price_final);
        mHelper = new CartDb(this);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });


       getDatabaseColunms();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + CartContract.CartColumn.COLUMN_MULTIPLE + ") as Total FROM " + CartContract.CartColumn.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            int total = cursor.getInt(cursor.getColumnIndex("Total"));
            int total2 = total + 1500;
            price_final.setText(String.valueOf(total2));

        }

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, Payment.class);
                intent.putExtra("price", price_final.getText().toString());
                startActivity(intent);
            }
        });

            // get final total

//        View footer = getLayoutInflater().inflate(R.layout.footer, null);
//        list.addFooterView(footer);
    }



    private void getDatabaseColunms() {
        SQLiteDatabase db =  mHelper.getReadableDatabase();

        String[] projection = {
                CartContract.CartColumn._ID,
                CartContract.CartColumn.COLUMN_ITEM_NAME,
                CartContract.CartColumn.COLUMN_IMAGE,
                CartContract.CartColumn.COLUMN_price,
                CartContract.CartColumn.COLUMN_QUANTITY,
                CartContract.CartColumn.COLUMN_MULTIPLE
        };
        c  = db.query(
                CartContract.CartColumn.TABLE_NAME,
                projection,
                 null, null, null, null, null);

         list = (ListView)findViewById(R.id.list_cart);

        CartAdapter adapter = new CartAdapter(this, c);

        list.setAdapter(adapter);

    }

}
