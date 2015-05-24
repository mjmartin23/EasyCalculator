package com.easyapps.easycalculator;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Matt on 5/24/2015.
 */
public class MyApplication extends Application {

    public static int themeId;
    public static String themeSetting = "0";
    public static Context context;

    public static int getThemeId()
    {
        return themeId;
    }
    public static void reloadTheme()
    {
        themeSetting = PreferenceManager.getDefaultSharedPreferences(context).getString("defaultTheme", "0");
        if(themeSetting.equals("0"))
            themeId = R.style.AppTheme_Blue;
        else
            themeId = R.style.AppTheme_Green;
    }
}
