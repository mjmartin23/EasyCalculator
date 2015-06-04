package com.easyapps.easycalculator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.easyapps.easycalculator.R;

/**
Created by Matt on 12/31/2014.

 Creates a custom dialog for the theme choice.
 */

public class CustomDialog extends Dialog {

    public Class c;
    public Button blue,red,green,purple,orange,yellow,teal,lime,bluegrey,indigo,cyan,amber;
    public int THEME;
    public String PREFS_NAME = "MyPrefsFile";


    public CustomDialog(Activity a) {
        super(a);
        c = a.getClass();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        blue = (Button)findViewById(R.id.blue_btn);
        red = (Button)findViewById(R.id.red_btn);
        green = (Button)findViewById(R.id.green_btn);
        purple = (Button)findViewById(R.id.purple_btn);
        orange = (Button)findViewById(R.id.orange_btn);
        yellow = (Button)findViewById(R.id.yellow_btn);
        teal = (Button)findViewById(R.id.teal_btn);
        lime = (Button)findViewById(R.id.lime_btn);
        bluegrey = (Button)findViewById(R.id.bluegrey_btn);
        indigo = (Button)findViewById(R.id.indigo_btn);
        cyan = (Button)findViewById(R.id.cyan_btn);
        amber = (Button)findViewById(R.id.amber_btn);

        blue.setOnClickListener(listener);
        red.setOnClickListener(listener);
        green.setOnClickListener(listener);
        purple.setOnClickListener(listener);
        orange.setOnClickListener(listener);
        yellow.setOnClickListener(listener);
        teal.setOnClickListener(listener);
        lime.setOnClickListener(listener);
        bluegrey.setOnClickListener(listener);
        indigo.setOnClickListener(listener);
        cyan.setOnClickListener(listener);
        amber.setOnClickListener(listener);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.red_btn:
                    THEME = R.style.AppTheme_Red;
                    break;
                case R.id.purple_btn:
                    THEME = R.style.AppTheme_Purple;
                    break;
                case R.id.indigo_btn:
                    THEME = R.style.AppTheme_Indigo;
                    break;
                case R.id.blue_btn:
                    THEME = R.style.AppTheme_Blue;
                    break;
                case R.id.teal_btn:
                    THEME = R.style.AppTheme_Teal;
                    break;
                case R.id.green_btn:
                    THEME = R.style.AppThemeLight_Green;
                    break;
                case R.id.lime_btn:
                    THEME = R.style.AppThemeLight_Lime;
                    break;
                case R.id.orange_btn:
                    THEME = R.style.AppThemeLight_Orange;
                    break;
                case R.id.yellow_btn:
                    THEME = R.style.AppThemeLight_Yellow;
                    break;
                case R.id.bluegrey_btn:
                    THEME = R.style.AppTheme_BlueGrey;
                    break;
                case R.id.cyan_btn:
                    THEME = R.style.AppThemeLight_Cyan;
                    break;
                case R.id.amber_btn:
                    THEME = R.style.AppThemeLight_Amber;
                    break;
            }

            //Put theme changes into shared preferences
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("THEME",THEME);

            editor.apply();

            dismiss();

            //Restart activity to apply changes
            Context context = getContext();
            Intent i = new Intent(getContext(), c);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

    };


}
