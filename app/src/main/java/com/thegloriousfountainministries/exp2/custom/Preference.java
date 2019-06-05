package com.thegloriousfountainministries.exp2.custom;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by My HP on 7/17/2018.
 */

public class Preference {
    private static final String PREF_NAME = "LoginPref";
    private static final String PREF_NAME2 = "LoginPref2";
//    private static final String PREF_NAME3 = "LoginPref";
    private static final int PREF_TYPE = 0;
    private static final String IS_SESSION = "session";
    Context _context;

    SharedPreferences pref, pref2, pref3;
    SharedPreferences.Editor  editor;

    public Preference(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PREF_TYPE);
        editor = pref.edit();
    }

    public void setSession(boolean isFirstTime) {
        editor.putBoolean(IS_SESSION, isFirstTime);
        editor.commit();
    }
    public void saveDetails(String token){
        editor.putString("token", token);
        editor.commit();
    }
    public String getToken(){
        return pref.getString("token", "");
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_SESSION, true);
    }
}
