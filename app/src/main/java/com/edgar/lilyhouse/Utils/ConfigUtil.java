package com.edgar.lilyhouse.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class ConfigUtil {

    private Context context;

    @SuppressLint("StaticFieldLeak")
    private static ConfigUtil instance = null;
    private static final Object LOCK = new Object();

    public static ConfigUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new ConfigUtil(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private ConfigUtil(Context context) {
        this.context = context;
    }

    private int regionCodeIndex;
    private int statusCodeIndex;
    private int orderCodeIndex;

    public int getRegionCodeIndex() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
        regionCodeIndex = sharedPreferences.getInt("regionCodeIndex", Context.MODE_PRIVATE);
        return regionCodeIndex;
    }

    public void setRegionCodeIndex(int regionCodeIndex) {
        this.regionCodeIndex = regionCodeIndex;
        SharedPreferences sharedPreferences = context.getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("regionCodeIndex", regionCodeIndex);
        editor.apply();
    }

    public int getStatusCodeIndex() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
        statusCodeIndex = sharedPreferences.getInt("statusCodeIndex", Context.MODE_PRIVATE);
        return statusCodeIndex;
    }

    public void setStatusCodeIndex(int statusCodeIndex) {
        this.statusCodeIndex = statusCodeIndex;
        SharedPreferences sharedPreferences = context.getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("statusCodeIndex", statusCodeIndex);
        editor.apply();
    }

    public int getOrderCodeIndex() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
        orderCodeIndex = sharedPreferences.getInt("orderCodeIndex", Context.MODE_PRIVATE);
        return orderCodeIndex;
    }

    public void setOrderCodeIndex(int orderCodeIndex) {
        this.orderCodeIndex = orderCodeIndex;
        SharedPreferences sharedPreferences = context.getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("orderCodeIndex", orderCodeIndex);
        editor.apply();
    }
}
