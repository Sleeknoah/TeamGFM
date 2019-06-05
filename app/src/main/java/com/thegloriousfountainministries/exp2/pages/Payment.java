package com.thegloriousfountainministries.exp2.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.custom.AddCart;
import com.thegloriousfountainministries.exp2.custom.ApiInterface;
import com.thegloriousfountainministries.exp2.data.CartContract;
import com.thegloriousfountainministries.exp2.data.CartDb;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.thegloriousfountainministries.exp2.pages.Home.BASE_URL;

/**
 * Created by My HP on 12/19/2018.
 */

public class Payment extends AppCompatActivity {
    //variables
    private Card card;
    private Charge charge;
    SharedPreferences pref, prefs;
    SharedPreferences.Editor edit, editor;

    private EditText emailField;
    private EditText cardNumberField;
    private EditText expiryMonthField;
    private EditText expiryYearField;
    private EditText cvvField;
    private ProgressBar progressBar;
    ApiInterface apiInterface;

    private String email, cardNumber, cvv;
    private int expiryMonth, expiryYear;
    String result;
    int dest = 0;
    CartDb hep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init paystack sdk
        PaystackSdk.initialize(getApplicationContext());
        setContentView(R.layout.payment);
        //init view
        Button payBtn = (Button) findViewById(R.id.payy);

        pref = getSharedPreferences("ref", MODE_PRIVATE);
        edit = pref.edit();

        hep = new CartDb(this);

        pref = getSharedPreferences("count", MODE_PRIVATE);

//      emailField = (EditText) findViewById(R.id.editText);
        cardNumberField = (EditText) findViewById(R.id.editText);
        expiryMonthField = (EditText) findViewById(R.id.editText2);
        expiryYearField = (EditText) findViewById(R.id.editText3);
        cvvField = (EditText) findViewById(R.id.editText4);
        progressBar = findViewById(R.id.progressBar2);


        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateForm()) {
                    return;
                }
                try {
                    //email = emailField.getText().toString().trim();
                    cardNumber = cardNumberField.getText().toString().trim();
                    expiryMonth = Integer.parseInt(expiryMonthField.getText().toString().trim());
                    expiryYear = Integer.parseInt(expiryYearField.getText().toString().trim());
                    cvv = cvvField.getText().toString().trim();

                    //String cardNumber = "4084084084084081";
                    //int expiryMonth = 11; //any month in the future
                    //int expiryYear = 18; // any year in the future
                    //String cvv = "408";
                    card = new Card(cardNumber, expiryMonth, expiryYear, cvv);

                    if (card.isValid()) {
                        Toast.makeText(Payment.this, "Card is Valid", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.VISIBLE);
                        performCharge();
                    } else {
                        Toast.makeText(Payment.this, "Card not Valid", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Method to perform the charging of the card
     */
    private void performCharge() {
        //create a Charge object
        charge = new Charge();

        //set the card to charge
        charge.setCard(card);

        //call this method if you set a plan
        //charge.setPlan("PLN_yourplan");

        charge.setEmail("yungdyke@gmail.com"); //dummy email address

        String amount = getIntent().getExtras().getString("price");
        int initamount = Integer.parseInt(amount);
        int final_amount = initamount * 100;

        charge.setAmount(final_amount); //test amount

        PaystackSdk.chargeCard(Payment.this, charge, new Paystack.TransactionCallback() {
            @Override
            public void onSuccess(Transaction transaction) {
                // This is called only after transaction is deemed successful.
                // Retrieve the transaction, and send its reference to your server
                // for verification.

                String paymentReference = transaction.getReference();
                edit.putString("tran", paymentReference);
                edit.putString("stats", "successful");
                edit.commit();
                edit = pref.edit();
                placeOrder();
//                Toast.makeText(Payment.this, "Transaction Successful! payment reference: "
//                        + paymentReference, Toast.LENGTH_LONG).show();
            }

            @Override
            public void beforeValidate(Transaction transaction) {
                // This is called only before requesting OTP.
                // Save reference so you may send to server. If
                // error occurs with OTP, you should still verify on server.

            }

            @Override
            public void onError(Throwable error, Transaction transaction) {
                //handle error here
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;

//        String email = emailField.getText().toString();
//        if (TextUtils.isEmpty(email)) {
//            emailField.setError("Required.");
//            valid = false;
//        } else {
//            emailField.setError(null);
//        }

        String cardNumber = cardNumberField.getText().toString();
        if (TextUtils.isEmpty(cardNumber)) {
            cardNumberField.setError("Required.");
            valid = false;
        } else {
            cardNumberField.setError(null);
        }


        String expiryMonth = expiryMonthField.getText().toString();
        if (TextUtils.isEmpty(expiryMonth)) {
            expiryMonthField.setError("Required.");
            valid = false;
        } else {
            expiryMonthField.setError(null);
        }

        String expiryYear = expiryYearField.getText().toString();
        if (TextUtils.isEmpty(expiryYear)) {
            expiryYearField.setError("Required.");
            valid = false;
        } else {
            expiryYearField.setError(null);
        }

        String cvv = cvvField.getText().toString();
        if (TextUtils.isEmpty(cvv)) {
            cvvField.setError("Required.");
            valid = false;
        } else {
            cvvField.setError(null);
        }

        return valid;
    }
     private void placeOrder(){
           getRetrofit(BASE_URL);
         Call<AddCart> order =apiInterface.order("2", pref.getString("tran", "0"), pref.getString("stats", "0"));
          order.enqueue(new Callback<AddCart>() {
              @Override
              public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                  result = response.body().getRes();
                  int res = Integer.parseInt(result);
                  if (res > dest){
                      placeOrder();
                  }else if (result.equals("0")){

                      progressBar.setVisibility(View.INVISIBLE);
                      Toast.makeText(Payment.this, "Payment successfull. You can now proceed to download", Toast.LENGTH_SHORT).show();
                      SQLiteDatabase db = hep.getWritableDatabase();
                      db.delete(CartContract.CartColumn.TABLE_NAME, "1", null);
                      getdatabasecount();
                      startActivity(new Intent(Payment.this, Share.class));
                  }
              }

              @Override
              public void onFailure(Call<AddCart> call, Throwable t) {

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