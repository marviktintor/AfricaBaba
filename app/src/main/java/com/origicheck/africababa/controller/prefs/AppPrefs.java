package com.origicheck.africababa.controller.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by victor on 10/10/2015.
 */
public class AppPrefs {


    private static final String PREFS_BUYER_ID = "buyer_id";
    private static final String PREFS_USER_ID = "user_id";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private AppPrefs(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public static AppPrefs getInstance(Context context) {
        return new AppPrefs(context);
    }

    public int getBuyerId() {
        return sharedPreferences.getInt(AppPrefs.PREFS_BUYER_ID, getUserId());
    }

    public int getUserId() {
        return sharedPreferences.getInt(AppPrefs.PREFS_USER_ID, 1);
    }
}