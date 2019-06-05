package com.thegloriousfountainministries.exp2.custom;

import co.paystack.android.PaystackSdk;

/**
 * Created by My HP on 12/19/2018.
 */

public class Bootstrap {
    public static void setPaystackKey(String publicKey) {
        PaystackSdk.setPublicKey("pk_test_7a0e4f234c4e396fdc6145e80a646be12193bad1");
    }
}
