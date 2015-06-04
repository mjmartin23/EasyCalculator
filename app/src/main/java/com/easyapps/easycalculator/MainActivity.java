// Created by Matthew J. Martin in May/June 2015
//
// Thank you to Lawrence PC Dol for creating the MathEval package
// used to evaluate mathematical expressions as strings.
//
// Easy Calculator, by
// Easy Apps


package com.easyapps.easycalculator;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {


    public int i = 0;
    public String operation = "";
    public EditText edittext;
    public TextView lastoptextview;
    public int position = 0;
    private static final String TAG = MainActivity.class.getSimpleName();
    public String solution = "";
    public boolean lastopequals = false;
    public boolean validstatement = true;
    public boolean anshit = false;

    public int THEME = R.style.AppTheme_Blue;

    public CustomDialog customDialog;

    public ArrayAdapter<String> adapter;

    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private int menubtn;
    private String[] draweritems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set theme
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (prefs == null) {
            THEME = R.style.AppTheme_Blue;
        } else {
            THEME = prefs.getInt("THEME",R.style.AppTheme_Blue);
        }
        setTheme(THEME);

        setContentView(R.layout.activity_main);

        mDrawerList = (ListView)findViewById(R.id.left_drawer);
        lastoptextview = (TextView)findViewById(R.id.lastOpEditText);
        edittext = (EditText)findViewById(R.id.editText);
        edittext.setInputType(InputType.TYPE_NULL);
        edittext.setRawInputType(InputType.TYPE_CLASS_TEXT);
        edittext.setTextIsSelectable(true);

        switch (THEME) {
            case R.style.AppTheme_BlueGrey:
            case R.style.AppTheme_Indigo:
            case R.style.AppTheme_Purple:
            case R.style.AppTheme_Red:
            case R.style.AppTheme_Teal:
            case R.style.AppTheme_Blue:
                mDrawerList.setBackgroundColor(getResources().getColor(R.color.grey20));
                edittext.setTextColor(getResources().getColor(R.color.whitetext));
                lastoptextview.setTextColor(getResources().getColor(R.color.whitetext));
                menubtn = R.drawable.menu_white;
                break;
            case R.style.AppThemeLight_Lime:
            case R.style.AppThemeLight_Orange:
            case R.style.AppThemeLight_Green:
            case R.style.AppThemeLight_Yellow:
            case R.style.AppThemeLight_Amber:
            case R.style.AppThemeLight_Cyan:
                mDrawerList.setBackgroundColor(getResources().getColor(R.color.whitetext));
                edittext.setTextColor(getResources().getColor(R.color.grey20));
                lastoptextview.setTextColor(getResources().getColor(R.color.grey20));
                menubtn = R.drawable.menu_black;
                break;
        }

        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_themes) {
                    customDialog = new CustomDialog(MainActivity.this);
                    customDialog.show();

                } else if (id == R.id.action_feedback) {
                    ShareActionProvider shareAction = (ShareActionProvider) item.getActionProvider();

                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "easyflashlightdev@gmail.com", null));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Easy Calculator Bug");
                    intent.putExtra(Intent.EXTRA_TEXT, "Please include your phone model and Android version, along with " +
                            "a description of the issue.  Thank you for the feedback.");

                    shareAction.setShareIntent(intent);
                    return true;
                }
                return true;
            }
        });
        */

        Button sinbtn = (Button)findViewById(R.id.sinbtn);
        Button cosbtn = (Button)findViewById(R.id.cosbtn);
        Button tanbtn = (Button)findViewById(R.id.tanbtn);
        Button logbtn = (Button)findViewById(R.id.logbtn);

        Button sqrtbtn = (Button)findViewById(R.id.sqrtbtn);
        Button powerbtn = (Button)findViewById(R.id.powerbtn);
        Button ansbtn = (Button)findViewById(R.id.ansbtn);
        Button lnbtn = (Button)findViewById(R.id.lnbtn);

        Button clearbtn = (Button)findViewById(R.id.clearbtn);
        Button leftbtn = (Button)findViewById(R.id.leftbtn);
        Button rightbtn = (Button)findViewById(R.id.rightbtn);
        Button deletebtn = (Button)findViewById(R.id.deletebtn);

        Button squaredbtn = (Button)findViewById(R.id.squaredbtn);
        Button leftparanthesesbtn = (Button)findViewById(R.id.leftparenthesesbtn);
        Button rightparenthesesbtn = (Button)findViewById(R.id.rightparenthesesbtn);
        Button dividebtn = (Button)findViewById(R.id.dividebtn);

        Button sevenbtn = (Button)findViewById(R.id.sevenbtn);
        Button eightbtn = (Button)findViewById(R.id.eightbtn);
        Button ninebtn = (Button)findViewById(R.id.ninebtn);
        Button multbtn = (Button)findViewById(R.id.multbtn);

        Button fourbtn = (Button)findViewById(R.id.fourbtn);
        Button fivebtn = (Button)findViewById(R.id.fivebtn);
        Button sixbtn = (Button)findViewById(R.id.sixbtn);
        Button subbtn = (Button)findViewById(R.id.subbtn);

        Button onebtn = (Button)findViewById(R.id.onebtn);
        Button twobtn = (Button)findViewById(R.id.twobtn);
        Button threebtn = (Button)findViewById(R.id.threebtn);
        Button addbtn = (Button)findViewById(R.id.addbtn);

        Button decimalbtn = (Button)findViewById(R.id.decimalbtn);
        Button zerobtn = (Button)findViewById(R.id.zerobtn);
        Button negativebtn = (Button)findViewById(R.id.negativebtn);
        Button equalsbtn = (Button)findViewById(R.id.equalsbtn);

        List<Button> buttons = Arrays.asList(sinbtn,cosbtn,tanbtn,logbtn,sqrtbtn,
                powerbtn,ansbtn,lnbtn,clearbtn,leftbtn,rightbtn,deletebtn,
                squaredbtn,leftparanthesesbtn,rightparenthesesbtn,dividebtn,
                sevenbtn,eightbtn,ninebtn,multbtn,fourbtn,fivebtn,sixbtn,subbtn,
                onebtn,twobtn,threebtn,addbtn,decimalbtn,zerobtn,negativebtn,equalsbtn);

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
            } else if (btn == addbtn | btn == subbtn | btn == dividebtn | btn == multbtn) {
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

        position = edittext.getSelectionEnd();

        switch (v.getId()) {
            case R.id.clearbtn:
                operation = "";
                position = 0;
                solution = "";
                break;
            case R.id.leftbtn:
                // move cursor left
                if (position > 0) {
                    position--;
                }
                break;
            case R.id.rightbtn:
                // Move cursor right
                if (position < operation.length()) {
                    position++;
                }
                break;
            case R.id.deletebtn:
                // clear last entered button
                if (position > 0) {
                    if (operation.length() >= 5 && position >= operation.length()-4) {
                        if (operation.substring(position-4,position).equals("sin(") | operation.substring(position-4,position).equals("cos(") |
                                operation.substring(position-4,position).equals("tan(") | operation.substring(position-4,position).equals("log(")) {
                            operation = operation.substring(0,position-4) + operation.substring(position,operation.length());
                            position-=3;
                        } else if (operation.substring(position-5,position).equals("sqrt(")) {
                            operation = operation.substring(0,position-5) + operation.substring(position,operation.length());
                            position-=4;
                        } else if (operation.substring(position-3,position).equals("ln(")) {
                            operation = operation.substring(0,position-3) + operation.substring(position,operation.length());
                            position-=2;
                        } else {
                            operation = operation.substring(0, position-1) + operation.substring(position, operation.length());
                        }
                    } else if (operation.length() == 4 && position >= operation.length()-3) {
                        if (operation.substring(position-4,position).equals("sin(") | operation.substring(position-4,position).equals("cos(") |
                                operation.substring(position-4,position).equals("tan(") | operation.substring(position-4,position).equals("log(")) {
                            operation = operation.substring(0,position-4) + operation.substring(position,operation.length());
                            position-=3;
                        } else if (operation.substring(position-3,position).equals("ln(")) {
                            operation = operation.substring(0,position-3) + operation.substring(position,operation.length());
                            position-=2;
                        } else {
                            operation = operation.substring(0, position-1) + operation.substring(position, operation.length());
                        }
                    } else if (operation.length() == 3 && position >= operation.length()-2) {
                        if (operation.substring(position-3,position).equals("ln(")) {
                            operation = operation.substring(0,position-3) + operation.substring(position,operation.length());
                            position-=2;
                        } else {
                            operation = operation.substring(0, position-1) + operation.substring(position, operation.length());
                        }
                    } else if (operation.length() > 0) {
                        operation = operation.substring(0,position-1) + operation.substring(position,operation.length());
                    }

                    position--;
                }
                break;
            case R.id.sinbtn:
                addCharToOp("sin()",true);
                break;
            case R.id.cosbtn:
                addCharToOp("cos()",true);
                break;
            case R.id.tanbtn:
                addCharToOp("tan()",true);
                break;
            case R.id.logbtn:
                addCharToOp("log()",true);
                break;
            case R.id.sqrtbtn:
                addCharToOp("sqrt()",true);
                break;
            case R.id.powerbtn:
                addCharToOp("^()",false);
                break;
            case R.id.ansbtn:
                if ((lastoptextview.getText().charAt(lastoptextview.getText().length()-1) == '=')) {
                    anshit = true;
                    break;
                } else {
                    operation = operation.substring(0,position) + "("+lastoptextview.getText()+")" + operation.substring(position,operation.length());
                    position += lastoptextview.length() + 2;
                    break;
                }
            case R.id.lnbtn:
                addCharToOp("ln()",true);
                break;
            case R.id.squaredbtn:
                addCharToOp("^(2)",false);
                break;
            case R.id.leftparenthesesbtn:
                addCharToOp("(",true);
                break;
            case R.id.rightparenthesesbtn:
                addCharToOp(")",true);
                break;
            case R.id.dividebtn:
                addCharToOp("/",false);
                break;
            case R.id.sevenbtn:
                addCharToOp("7",true);
                break;
            case R.id.eightbtn:
                addCharToOp("8",true);
                break;
            case R.id.ninebtn:
                addCharToOp("9",true);
                break;
            case R.id.multbtn:
                addCharToOp("*",false);
                break;
            case R.id.fourbtn:
                addCharToOp("4",true);
                break;
            case R.id.fivebtn:
                addCharToOp("5",true);
                break;
            case R.id.sixbtn:
                addCharToOp("6",true);
                break;
            case R.id.subbtn:
                addCharToOp("-",false);
                break;
            case R.id.onebtn:
                addCharToOp("1",true);
                break;
            case R.id.twobtn:
                addCharToOp("2",true);
                break;
            case R.id.threebtn:
                addCharToOp("3",true);
                break;
            case R.id.addbtn:
                addCharToOp("+",false);
                break;
            case R.id.decimalbtn:
                addCharToOp(".",true);
                break;
            case R.id.zerobtn:
                addCharToOp("0",true);
                break;
            case R.id.negativebtn:
                addCharToOp("-",true);
                break;
            case R.id.equalsbtn:

                /*
                //Handle coefficients
                String trueoperation = operation;
                int len = operation.length();
                for (int i=0; i < len; i++) {
                    char c = trueoperation.charAt(i);

                    if ((c == 's' && (trueoperation.charAt(i+1) == 'i'|trueoperation.charAt(i+1) == 'q')) |
                            c == 'c'|(c == 't' && trueoperation.charAt(i+1) == 'a')|c == 'l') {
                        trueoperation = trueoperation.substring(0,i) +"(1)" + trueoperation.substring(i,trueoperation.length());
                        i+=3;
                        len +=3;
                        Toast.makeText(this,trueoperation,Toast.LENGTH_LONG).show();
                    }
                }
                */
                MathEval math = new MathEval();
                try {
                    double solutiondub = math.evaluate(operation);
                    double finalD = Math.round(solutiondub * 100000000.0) / 100000000.0;
                    if (solutiondub % 1 == 0) {
                        int solutionint = (int)solutiondub;
                        solution = String.valueOf(solutionint);
                    } else {
                        solution = String.valueOf(finalD);
                    }

                    //Check overflow
                    if (solutiondub >= 2147483647 | solutiondub <= -2147483648) {
                        Toast.makeText(this,"Error: Overflow",Toast.LENGTH_LONG).show();
                        break;
                    }

                    lastoptextview.setText(operation + " =");
                    position = solution.length();
                    operation = solution;
                    validstatement = true;
                    break;
                } catch (Exception e) {
                    validstatement = false;
                    Toast.makeText(MainActivity.this,"Exception: "+e.getMessage() +", the expression is invalid.",Toast.LENGTH_LONG).show();
                    Log.d(TAG,e.getMessage());
                    break;
                }


            }

        //Flag whether that last button press was an equals
        //to be able to handle the next button
        lastopequals = ((v.getId() == R.id.equalsbtn | anshit) && validstatement);
        anshit = false;

        Log.d(TAG, "The operation is " + operation);
        Log.d(TAG, "the position is " + position);

        edittext.setText(operation);
        edittext.setSelection(position);
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
            customDialog = new CustomDialog(MainActivity.this);
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

    private void addCharToOp(String buttonPushed, boolean lastopequalsmatters) {
        //Handles all button presses by appending to operation string
        //the appropriate string based on the button pressed

        if (lastopequalsmatters) {
            if (lastopequals) {
                lastoptextview.setText(operation);
                operation = "";
                position = 0;
                solution = "";
                lastopequals = false;
            }
        }
        //Update op
        operation = operation.substring(0,position) + buttonPushed + operation.substring(position,operation.length());

        //Update pos
        if (buttonPushed.equals("ln()")|buttonPushed.equals("log()")|buttonPushed.equals("sin()")|
                buttonPushed.equals("cos()")|buttonPushed.equals("tan()")|buttonPushed.equals("sqrt()")|
                buttonPushed.equals("^()")) {
            position+=(buttonPushed.length()-1);
        } else {
            position+=buttonPushed.length();
        }

    }
}

//TODO fix coefficient for functions
//TODO animate eddittext
//TODO wear support

//Changes since last published update:
// removed red underline from edittext
// fixed portrait orientation