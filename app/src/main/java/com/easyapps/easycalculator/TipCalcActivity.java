package com.easyapps.easycalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matt on 6/2/2015.
 */

public class TipCalcActivity extends Activity implements View.OnClickListener {

    public int THEME = R.style.AppTheme_Blue;
    public EditText priceedittext;
    public EditText tipedittext;
    private ActionBarDrawerToggle mDrawerToggle;
    private int menubtn;
    private String[] draweritems;
    public ArrayAdapter<String> adapter;
    public CustomDialog customDialog;
    private String pricestring = "";
    private String tipstring = "";
    private int pricepos = 0;
    private int tippos = 0;
    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean tipentereddecimal,priceentereddecimal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (prefs == null) {
            THEME = R.style.AppTheme_Blue;
        } else {
            THEME = prefs.getInt("THEME",R.style.AppTheme_Blue);
        }
        setTheme(THEME);

        setContentView(R.layout.activity_tip_calc);

        ListView mDrawerList = (ListView)findViewById(R.id.left_drawer);
        priceedittext = (EditText)findViewById(R.id.priceEditText);
        tipedittext = (EditText)findViewById(R.id.tipEditText);

        priceedittext.setInputType(InputType.TYPE_NULL);
        priceedittext.setTextIsSelectable(true);
        priceedittext.setRawInputType(InputType.TYPE_CLASS_TEXT);
        tipedittext.setInputType(InputType.TYPE_NULL);
        tipedittext.setTextIsSelectable(true);
        tipedittext.setRawInputType(InputType.TYPE_CLASS_TEXT);

        //priceedittext.setOnFocusChangeListener(foc);

        switch (THEME) {
            case R.style.AppTheme_BlueGrey:
            case R.style.AppTheme_Indigo:
            case R.style.AppTheme_Purple:
            case R.style.AppTheme_Red:
            case R.style.AppTheme_Teal:
            case R.style.AppTheme_Blue:
                mDrawerList.setBackgroundColor(getResources().getColor(R.color.grey20));
                priceedittext.setTextColor(getResources().getColor(R.color.whitetext));
                tipedittext.setTextColor(getResources().getColor(R.color.whitetext));
                menubtn = R.drawable.menu_white;
                break;
            case R.style.AppThemeLight_Lime:
            case R.style.AppThemeLight_Orange:
            case R.style.AppThemeLight_Green:
            case R.style.AppThemeLight_Yellow:
            case R.style.AppThemeLight_Amber:
            case R.style.AppThemeLight_Cyan:
                mDrawerList.setBackgroundColor(getResources().getColor(R.color.whitetext));
                priceedittext.setTextColor(getResources().getColor(R.color.grey20));
                tipedittext.setTextColor(getResources().getColor(R.color.grey20));
                menubtn = R.drawable.menu_black;
                break;
        }

        Button sevenbtn = (Button)findViewById(R.id.tipsevenbtn);
        Button eightbtn = (Button)findViewById(R.id.tipeightbtn);
        Button ninebtn = (Button)findViewById(R.id.tipninebtn);
        Button clearbtn = (Button)findViewById(R.id.tipclearbtn);

        Button fourbtn = (Button)findViewById(R.id.tipfourbtn);
        Button fivebtn = (Button)findViewById(R.id.tipfivebtn);
        Button sixbtn = (Button)findViewById(R.id.tipsixbtn);
        Button deletebtn = (Button)findViewById(R.id.tipdeletebtn);

        Button onebtn = (Button)findViewById(R.id.tiponebtn);
        Button twobtn = (Button)findViewById(R.id.tiptwobtn);
        Button threebtn = (Button)findViewById(R.id.tipthreebtn);
        Button nextbtn = (Button)findViewById(R.id.tipnextfieldbtn);

        Button decimalbtn = (Button)findViewById(R.id.tipdecimalbtn);
        Button zerobtn = (Button)findViewById(R.id.tipzerobtn);
        Button doublezerobtn = (Button)findViewById(R.id.tipdoublezerobtn);
        Button equalsbtn = (Button)findViewById(R.id.tipequalsbtn);


        List<Button> buttons = Arrays.asList(sevenbtn, eightbtn, ninebtn, clearbtn,
                fourbtn, fivebtn, sixbtn, deletebtn, onebtn, twobtn, threebtn, nextbtn,
                decimalbtn, zerobtn, doublezerobtn, equalsbtn);

        for (Button btn : buttons) {
            btn.setOnClickListener(this);
        }

        for (Button btn : buttons) {
            if (btn == equalsbtn) {
                switch (THEME) {
                    case R.style.AppTheme_BlueGrey:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_bluegrey));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Indigo:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_indigo));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Purple:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_purple));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Red:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_red));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Teal:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_teal));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Blue:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_blue));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppThemeLight_Green:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_green));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Lime:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_lime));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Orange:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_orange));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Yellow:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_yellow));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Amber:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_amber));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Cyan:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_cyan));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                }
            } else if (btn == clearbtn | btn == deletebtn | btn == nextbtn) {
                switch (THEME) {
                    case R.style.AppTheme_BlueGrey:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_bluegrey));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Indigo:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_indigo));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Purple:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_purple));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Red:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_red));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Teal:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_teal));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Blue:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_blue));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppThemeLight_Green:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_green));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Lime:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_lime));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Orange:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_orange));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Yellow:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_yellow));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Amber:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_amber));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Cyan:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_cyan));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                }
            } else {
                switch (THEME) {
                    case R.style.AppTheme_BlueGrey:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_bluegrey));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Indigo:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_indigo));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Purple:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_purple));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Red:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_red));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Teal:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_teal));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppTheme_Blue:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_blue));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.whitetext));
                        break;
                    case R.style.AppThemeLight_Lime:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_lime));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Orange:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_orange));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Yellow:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_yellow));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Green:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_green));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Amber:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_amber));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                    case R.style.AppThemeLight_Cyan:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_cyan));
                        btn.setTextColor(getApplicationContext().getResources().getColor(R.color.grey20));
                        break;
                }
            }
        }

        final DrawerLayout mDrawerLayout = (DrawerLayout)findViewById(R.id.activity_main);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                menubtn,                /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_closed  /* "close drawer" description */
        );

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        /*
        LayoutInflater infl = getLayoutInflater();
        ViewGroup header = (ViewGroup)infl.inflate(R.layout.nav_header, mDrawerList, false);
        mDrawerList.addHeaderView(header,null,false);
        */

        // Set the adapter for the list view


        draweritems = getResources().getStringArray(R.array.drawer_items);
        adapter = new ArrayAdapter<String>(this,R.layout.drawer_list_item,draweritems);

        mDrawerList.setAdapter(adapter);

        /*
        TextView draweritem = (TextView)findViewById(R.id.drawer_list_item);
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/roboto_med.ttf");
        draweritem.setTypeface(font);
        */

        mDrawerList.setClickable(true);
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                switch (pos) {
                    case 0:
                        //Start main calculator activity
                        Intent i0 = new Intent(getApplicationContext(),MainActivity.class);
                        i0.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(i0);
                        break;
                    case 1:
                        //Start tip calc activity
                        Intent i1 = new Intent(getApplicationContext(),TipCalcActivity.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(i1);
                        break;
                    case 2:
                        //Start sale calc activity
                        Intent i2 = new Intent(getApplicationContext(),SaleCalcActivity.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(i2);
                        break;
                }

                mDrawerLayout.closeDrawers();
            }
        });

    }

    @Override
    public void onClick(View v) {

        pricepos = priceedittext.getSelectionEnd();
        tippos = tipedittext.getSelectionEnd();

        switch (v.getId()) {
            case R.id.tipsevenbtn:
                addCharToField("7");
                break;
            case R.id.tipeightbtn:
                addCharToField("8");
                break;
            case R.id.tipninebtn:
                addCharToField("9");
                break;
            case R.id.tipclearbtn:
                //clear current field
                if (getCurrentFocus() != null) {
                    if (getCurrentFocus().getId() == R.id.priceEditText) {
                        //Update price
                        pricestring = "";
                        pricepos = 0;
                        priceentereddecimal = false;
                    } else if (getCurrentFocus().getId() == R.id.tipEditText) {
                        //Update tip
                        tipstring = "";
                        tippos = 0;
                        tipentereddecimal = false;
                    };

                }
                break;
            case R.id.tipfourbtn:
                addCharToField("4");
                break;
            case R.id.tipfivebtn:
                addCharToField("5");
                break;
            case R.id.tipsixbtn:
                addCharToField("6");
                break;
            case R.id.tipdeletebtn:
                //delete character in current field
                if (getCurrentFocus() != null) {
                    if (getCurrentFocus().getId() == R.id.priceEditText) {
                        //Update price
                        if (pricestring.length() > 0) {
                            if (pricestring.charAt(pricepos-1) == '.') {
                                priceentereddecimal = false;
                            }
                            pricestring = pricestring.substring(0, pricepos-1) + pricestring.substring(pricepos, pricestring.length());
                            pricepos--;

                        }

                    } else if (getCurrentFocus().getId() == R.id.tipEditText) {
                        //Update tip
                        if (tipstring.length() > 0) {
                            if (tipstring.charAt(tippos) == '.') {
                                tipentereddecimal = false;
                            }
                            tipstring = tipstring.substring(0, tippos-1) + tipstring.substring(tippos, tipstring.length());
                            tippos--;
                        }

                    }
                }
                break;
            case R.id.tiponebtn:
                addCharToField("1");
                break;
            case R.id.tiptwobtn:
                addCharToField("2");
                break;
            case R.id.tipthreebtn:
                addCharToField("3");
                break;
            case R.id.tipnextfieldbtn:
                //Go to other field
                if (getCurrentFocus() != null) {
                    if (getCurrentFocus().getId() == R.id.priceEditText) {
                        //change focus to tipedittext
                        tipedittext.requestFocus();
                    } else if (getCurrentFocus().getId() == R.id.tipEditText) {
                        //change focus to priceedittext
                        priceedittext.requestFocus();
                    }
                }
                break;
            case R.id.tipdecimalbtn:
                if (getCurrentFocus() != null) {
                    if (getCurrentFocus().getId() == R.id.priceEditText) {
                        if (!priceentereddecimal) {
                            addCharToField(".");
                            priceentereddecimal = true;
                        } else {
                            Toast.makeText(this,"You already entered a decimal, you cannot have two.",Toast.LENGTH_LONG).show();
                        }
                    } else if (getCurrentFocus().getId() == R.id.tipEditText) {
                        if (!tipentereddecimal) {
                            addCharToField(".");
                            tipentereddecimal = true;
                        } else  {
                            Toast.makeText(this,"You already entered a decimal, you cannot have two.",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            case R.id.tipzerobtn:
                addCharToField("0");
                break;
            case R.id.tipdoublezerobtn:
                if (getCurrentFocus() != null) {
                    if (getCurrentFocus().getId() == R.id.priceEditText) {
                        if (!priceentereddecimal) {
                            addCharToField(".00");
                            priceentereddecimal = true;
                        } else  {
                            Toast.makeText(this,"You already entered a decimal, you cannot have two.",Toast.LENGTH_LONG).show();
                        }
                    } else if (getCurrentFocus().getId() == R.id.tipEditText) {
                        if (!tipentereddecimal) {
                            addCharToField(".00");
                            tipentereddecimal = true;
                        } else  {
                            Toast.makeText(this,"You already entered a decimal, you cannot have two.",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            case R.id.tipequalsbtn:
                calculateTip();
                break;
        }

        priceedittext.setText(pricestring);
        priceedittext.setSelection(pricepos);
        tipedittext.setText(tipstring);
        tipedittext.setSelection(tippos);
    }

    private void addCharToField(String ch) {
        if (getCurrentFocus() != null) {
            if (getCurrentFocus().getId() == R.id.priceEditText) {
                //Update price
                if (pricestring.contains(".")) {
                    if (pricepos <= pricestring.indexOf('.') + 2) {
                        pricestring = pricestring.substring(0, pricepos) + ch + pricestring.substring(pricepos, pricestring.length());
                        pricepos += ch.length();
                    } else {
                        Log.d(TAG, "User attempted to enter digit past valid location");
                        Toast.makeText(this, "Please do not enter digits past two decimal places.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    pricestring = pricestring.substring(0, pricepos) + ch + pricestring.substring(pricepos, pricestring.length());
                    pricepos += ch.length();
                }
            } else if (getCurrentFocus().getId() == R.id.tipEditText) {
                //Update tip
                tipstring = tipstring.substring(0,tippos) + ch + tipstring.substring(tippos,tipstring.length());
                tippos+= ch.length();
            }

        } else {
            Log.d(TAG,"No field is currently focused");
            Toast.makeText(this,"Please select a field before entering information.",Toast.LENGTH_LONG).show();
        }
    }

    private void calculateTip() {
        if (!(pricestring.equals("") | tipstring.equals(""))) {
            float bill = Float.parseFloat(pricestring);
            float tippercent = Float.parseFloat(tipstring);

            float tip = bill*tippercent/100;
            String roundedtip = roundToTwoPlaces(tip);
            String totalbill = roundToTwoPlaces(tip + bill);

            TextView tipview = (TextView)findViewById(R.id.tipTotalTextView);
            TextView billview = (TextView)findViewById(R.id.billTotalTextView);

            tipview.setText("$ " + roundedtip);
            billview.setText("$ " + totalbill);
        } else {
            Log.d(TAG,"Not all fields have information.");
            Toast.makeText(this,"Please enter information in both fields",Toast.LENGTH_LONG).show();
        }
    }

    public static String roundToTwoPlaces(float d) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == R.id.action_themes) {
            customDialog = new CustomDialog(TipCalcActivity.this);
            customDialog.show();

        } else if (id == R.id.action_feedback) {
            //ShareActionProvider shareAction = (ShareActionProvider) item.getActionProvider();

            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","easyflashlightdev@gmail.com", null));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Easy Calculator Bug");
            intent.putExtra(Intent.EXTRA_TEXT, "Please include your phone model and Android version, along with " +
                    "a description of the issue.  Thank you for the feedback.");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this,"You do not have an app capable of sending emails. If the problem persists, " +
                        "please email your bug report to: easyflashlightdev@gmail.com. Thank you.", Toast.LENGTH_LONG).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
