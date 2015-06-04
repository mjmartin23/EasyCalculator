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

public class SaleCalcActivity extends Activity implements View.OnClickListener {

    public int THEME = R.style.AppTheme_Blue;
    public EditText priceedittext;
    public EditText saleedittext;
    private ActionBarDrawerToggle mDrawerToggle;
    private int menubtn;
    private String[] draweritems;
    public ArrayAdapter<String> adapter;
    public CustomDialog customDialog;
    private String pricestring = "";
    private String salestring = "";
    private int pricepos = 0;
    private int salepos = 0;
    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean saleentereddecimal,priceentereddecimal = false;

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

        setContentView(R.layout.activity_sale_calc);

        ListView mDrawerList = (ListView)findViewById(R.id.left_drawer);
        priceedittext = (EditText)findViewById(R.id.priceEditText);
        saleedittext = (EditText)findViewById(R.id.saleEditText);

        priceedittext.setInputType(InputType.TYPE_NULL);
        priceedittext.setTextIsSelectable(true);
        priceedittext.setRawInputType(InputType.TYPE_CLASS_TEXT);
        saleedittext.setInputType(InputType.TYPE_NULL);
        saleedittext.setTextIsSelectable(true);
        saleedittext.setRawInputType(InputType.TYPE_CLASS_TEXT);

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
                saleedittext.setTextColor(getResources().getColor(R.color.whitetext));
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
                saleedittext.setTextColor(getResources().getColor(R.color.grey20));
                menubtn = R.drawable.menu_black;
                break;
        }

        Button sevenbtn = (Button)findViewById(R.id.salesevenbtn);
        Button eightbtn = (Button)findViewById(R.id.saleeightbtn);
        Button ninebtn = (Button)findViewById(R.id.saleninebtn);
        Button clearbtn = (Button)findViewById(R.id.saleclearbtn);

        Button fourbtn = (Button)findViewById(R.id.salefourbtn);
        Button fivebtn = (Button)findViewById(R.id.salefivebtn);
        Button sixbtn = (Button)findViewById(R.id.salesixbtn);
        Button deletebtn = (Button)findViewById(R.id.saledeletebtn);

        Button onebtn = (Button)findViewById(R.id.saleonebtn);
        Button twobtn = (Button)findViewById(R.id.saletwobtn);
        Button threebtn = (Button)findViewById(R.id.salethreebtn);
        Button nextbtn = (Button)findViewById(R.id.salenextfieldbtn);

        Button decimalbtn = (Button)findViewById(R.id.saledecimalbtn);
        Button zerobtn = (Button)findViewById(R.id.salezerobtn);
        Button doublezerobtn = (Button)findViewById(R.id.saledoublezerobtn);
        Button equalsbtn = (Button)findViewById(R.id.saleequalsbtn);


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
        salepos = saleedittext.getSelectionEnd();

        switch (v.getId()) {
            case R.id.salesevenbtn:
                addCharToField("7");
                break;
            case R.id.saleeightbtn:
                addCharToField("8");
                break;
            case R.id.saleninebtn:
                addCharToField("9");
                break;
            case R.id.saleclearbtn:
                //clear current field
                if (getCurrentFocus() != null) {
                    if (getCurrentFocus().getId() == R.id.priceEditText) {
                        //Update price
                        pricestring = "";
                        pricepos = 0;
                        priceentereddecimal = false;
                    } else if (getCurrentFocus().getId() == R.id.saleEditText) {
                        //Update sale
                        salestring = "";
                        salepos = 0;
                        saleentereddecimal = false;
                    };

                }
                break;
            case R.id.salefourbtn:
                addCharToField("4");
                break;
            case R.id.salefivebtn:
                addCharToField("5");
                break;
            case R.id.salesixbtn:
                addCharToField("6");
                break;
            case R.id.saledeletebtn:
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

                    } else if (getCurrentFocus().getId() == R.id.saleEditText) {
                        //Update sale
                        if (salestring.length() > 0) {
                            if (salestring.charAt(salepos) == '.') {
                                saleentereddecimal = false;
                            }
                            salestring = salestring.substring(0, salepos-1) + salestring.substring(salepos, salestring.length());
                            salepos--;
                        }

                    }
                }
                break;
            case R.id.saleonebtn:
                addCharToField("1");
                break;
            case R.id.saletwobtn:
                addCharToField("2");
                break;
            case R.id.salethreebtn:
                addCharToField("3");
                break;
            case R.id.salenextfieldbtn:
                //Go to other field
                if (getCurrentFocus() != null) {
                    if (getCurrentFocus().getId() == R.id.priceEditText) {
                        //change focus to saleedittext
                        saleedittext.requestFocus();
                    } else if (getCurrentFocus().getId() == R.id.saleEditText) {
                        //change focus to priceedittext
                        priceedittext.requestFocus();
                    }
                }
                break;
            case R.id.saledecimalbtn:
                if (getCurrentFocus() != null) {
                    if (getCurrentFocus().getId() == R.id.priceEditText) {
                        if (!priceentereddecimal) {
                            addCharToField(".");
                            priceentereddecimal = true;
                        } else {
                            Toast.makeText(this,"You already entered a decimal, you cannot have two.",Toast.LENGTH_LONG).show();
                        }
                    } else if (getCurrentFocus().getId() == R.id.saleEditText) {
                        if (!saleentereddecimal) {
                            addCharToField(".");
                            saleentereddecimal = true;
                        } else  {
                            Toast.makeText(this,"You already entered a decimal, you cannot have two.",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            case R.id.salezerobtn:
                addCharToField("0");
                break;
            case R.id.saledoublezerobtn:
                if (getCurrentFocus() != null) {
                    if (getCurrentFocus().getId() == R.id.priceEditText) {
                        if (!priceentereddecimal) {
                            addCharToField(".00");
                            priceentereddecimal = true;
                        } else  {
                            Toast.makeText(this,"You already entered a decimal, you cannot have two.",Toast.LENGTH_LONG).show();
                        }
                    } else if (getCurrentFocus().getId() == R.id.saleEditText) {
                        if (!saleentereddecimal) {
                            addCharToField(".00");
                            saleentereddecimal = true;
                        } else  {
                            Toast.makeText(this,"You already entered a decimal, you cannot have two.",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            case R.id.saleequalsbtn:
                calculateSale();
                break;
        }

        priceedittext.setText(pricestring);
        priceedittext.setSelection(pricepos);
        saleedittext.setText(salestring);
        saleedittext.setSelection(salepos);
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
            } else if (getCurrentFocus().getId() == R.id.saleEditText) {
                //Update sale
                salestring = salestring.substring(0,salepos) + ch + salestring.substring(salepos,salestring.length());
                salepos+= ch.length();
            }

        } else {
            Log.d(TAG,"No field is currently focused");
            Toast.makeText(this,"Please select a field before entering information.",Toast.LENGTH_LONG).show();
        }
    }

    private void calculateSale() {
        if (!(pricestring.equals("") | salestring.equals(""))) {
            float price = Float.parseFloat(pricestring);
            float salepercent = Float.parseFloat(salestring);

            float savings = price*salepercent/100;
            String roundedsale = roundToTwoPlaces(savings);
            String totalbill = roundToTwoPlaces(price - savings);

            TextView saleview = (TextView)findViewById(R.id.saleTotalTextView);
            TextView finalpriceview = (TextView)findViewById(R.id.finalPriceTextView);

            saleview.setText("$ " + roundedsale);
            finalpriceview.setText("$ " + totalbill);

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
            customDialog = new CustomDialog(SaleCalcActivity.this);
            customDialog.show();

        } else if (id == R.id.action_feedback) {

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
