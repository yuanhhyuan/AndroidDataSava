package com.hy.base.androidbase.androidbasesave;

import android.app.Activity;
import android.content.SharedPreferences;

import com.hy.app.base.AppConstant;
import com.hy.app.config.MyApplication;

public class SharedPreferencesUtils {

    static SharedPreferencesUtils instance;
    private SharedPreferences mSharedPreferences;

    public SharedPreferencesUtils() {
        mSharedPreferences = MyApplication.getContext().getSharedPreferences(AppConstant.SP_NAME, Activity.MODE_PRIVATE);
    }

    // 单例模式
    public static synchronized SharedPreferencesUtils getInstance() {
        if (instance == null) {
            instance = new SharedPreferencesUtils();
        }
        return instance;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public Boolean getTuisong(String key) {
        return mSharedPreferences.getBoolean(key, true);
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }
}
