// Created by Matthew J. Martin in May/June 2015
//
// Thank you to Lawrence PC Dol for creating the MathEval package
// used to evaluate mathematical expressions as strings.
//
// Easy Calculator, by
// Easy Apps


package com.easyapps.easycalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
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
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import com.easyapps.easycalculator.CustomDialog;


public class MainActivity extends Activity implements View.OnClickListener {


    public int i = 0;
    public String operation = "";
    public EditText edittext;
    public int position = 0;
    private static final String TAG = MainActivity.class.getSimpleName();
    public String solution = "";
    public boolean lastopequals = false;
    public boolean validstatement = true;

    public int THEME = R.style.AppTheme_Blue;

    public CustomDialog customDialog;

    public ArrayAdapter<String> adapter;

    private ListView mDrawerList;


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

        Button sinbtn = (Button)findViewById(R.id.sinbtn);
        Button cosbtn = (Button)findViewById(R.id.cosbtn);
        Button tanbtn = (Button)findViewById(R.id.tanbtn);
        Button logbtn = (Button)findViewById(R.id.logbtn);

        Button sqrtbtn = (Button)findViewById(R.id.sqrtbtn);
        Button powerbtn = (Button)findViewById(R.id.powerbtn);
        Button inversebtn = (Button)findViewById(R.id.inversebtn);
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

        edittext = (EditText)findViewById(R.id.editText);
        edittext.setInputType(InputType.TYPE_NULL);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            edittext.setRawInputType(InputType.TYPE_CLASS_TEXT);
            edittext.setTextIsSelectable(true);
        }

        List<Button> buttons = Arrays.asList(sinbtn,cosbtn,tanbtn,logbtn,sqrtbtn,
                powerbtn,inversebtn,lnbtn,clearbtn,leftbtn,rightbtn,deletebtn,
                squaredbtn,leftparanthesesbtn,rightparenthesesbtn,dividebtn,
                sevenbtn,eightbtn,ninebtn,multbtn,fourbtn,fivebtn,sixbtn,subbtn,
                onebtn,twobtn,threebtn,addbtn,decimalbtn,zerobtn,negativebtn,equalsbtn);

        for (Button btn : buttons) {
            if (btn == equalsbtn) {
                switch (THEME) {
                    case R.style.AppTheme_Green:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_green));
                        break;
                    case R.style.AppTheme_BlueGrey:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_bluegrey));
                        break;
                    case R.style.AppTheme_Indigo:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_indigo));
                        break;
                    case R.style.AppTheme_Lime:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_lime));
                        break;
                    case R.style.AppTheme_Orange:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_orange));
                        break;
                    case R.style.AppTheme_Purple:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_purple));
                        break;
                    case R.style.AppTheme_Red:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_red));
                        break;
                    case R.style.AppTheme_Teal:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_teal));
                        break;
                    case R.style.AppTheme_Yellow:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_yellow));
                        break;
                    case R.style.AppTheme_Blue:
                        btn.setBackground(getResources().getDrawable(R.drawable.equalsbtn_blue));
                        break;
                }
            } else if (btn == addbtn | btn == subbtn | btn == dividebtn | btn == multbtn) {
                switch (THEME) {
                    case R.style.AppTheme_Green:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_green));
                        break;
                    case R.style.AppTheme_BlueGrey:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_bluegrey));
                        break;
                    case R.style.AppTheme_Indigo:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_indigo));
                        break;
                    case R.style.AppTheme_Lime:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_lime));
                        break;
                    case R.style.AppTheme_Orange:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_orange));
                        break;
                    case R.style.AppTheme_Purple:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_purple));
                        break;
                    case R.style.AppTheme_Red:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_red));
                        break;
                    case R.style.AppTheme_Teal:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_teal));
                        break;
                    case R.style.AppTheme_Yellow:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_yellow));
                        break;
                    case R.style.AppTheme_Blue:
                        btn.setBackground(getResources().getDrawable(R.drawable.opbtns_blue));
                        break;
                }
            } else {
                switch (THEME) {
                    case R.style.AppTheme_Green:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_green));
                        break;
                    case R.style.AppTheme_BlueGrey:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_bluegrey));
                        break;
                    case R.style.AppTheme_Indigo:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_indigo));
                        break;
                    case R.style.AppTheme_Lime:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_lime));
                        break;
                    case R.style.AppTheme_Orange:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_orange));
                        break;
                    case R.style.AppTheme_Purple:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_purple));
                        break;
                    case R.style.AppTheme_Red:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_red));
                        break;
                    case R.style.AppTheme_Teal:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_teal));
                        break;
                    case R.style.AppTheme_Yellow:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_yellow));
                        break;
                    case R.style.AppTheme_Blue:
                        btn.setBackground(getResources().getDrawable(R.drawable.numberbtns_blue));
                        break;
                }
            }
        }

        for (Button btn : buttons) {
            btn.setOnClickListener(this);
        }

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        LayoutInflater infl = getLayoutInflater();
        ViewGroup header = (ViewGroup)infl.inflate(R.layout.nav_header, mDrawerList, false);
        mDrawerList.addHeaderView(header,null,false);

        // Set the adapter for the list view
        adapter = new ArrayAdapter<String>(this,R.layout.drawer_list_item);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setClickable(true);
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                String selectedval = (String)mDrawerList.getItemAtPosition(pos);
                int eqidx = selectedval.indexOf("=");
                String historyvalue = selectedval.substring(eqidx+2,selectedval.length());
                operation = operation.substring(0,position) + historyvalue + operation.substring(position,operation.length());
                lastopequals = false;
                position += historyvalue.length();
                edittext.setText(operation);
                edittext.setSelection(position);
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
                // clear last entered character
                break;
            case R.id.sinbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "sin(" + operation.substring(position,operation.length());
                position+=4;
                break;
            case R.id.cosbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "cos(" + operation.substring(position,operation.length());
                position+=4;
                break;
            case R.id.tanbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "tan(" + operation.substring(position,operation.length());
                position+=4;
                break;
            case R.id.logbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "log(" + operation.substring(position,operation.length());
                position+=4;
                break;
            case R.id.sqrtbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "sqrt(" + operation.substring(position,operation.length());
                position+=5;
                break;
            case R.id.powerbtn:
                operation = operation.substring(0,position) + "^(" + operation.substring(position,operation.length());
                position+=2;
                break;
            case R.id.inversebtn:
                operation = operation.substring(0,position) + "^(-1)" + operation.substring(position,operation.length());
                position+=5;
                break;
            case R.id.lnbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "ln(" + operation.substring(position,operation.length());
                position+=3;
                break;
            case R.id.squaredbtn:
                operation = operation.substring(0,position) + "^(2)" + operation.substring(position,operation.length());
                position += 4;
                break;
            case R.id.leftparenthesesbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "(" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.rightparenthesesbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + ")" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.dividebtn:
                operation = operation.substring(0,position) + "/" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.sevenbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "7" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.eightbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "8" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.ninebtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "9" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.multbtn:
                operation = operation.substring(0,position) + "*" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.fourbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "4" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.fivebtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "5" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.sixbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "6" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.subbtn:
                operation = operation.substring(0,position) + "-" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.onebtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "1" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.twobtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "2" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.threebtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "3" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.addbtn:
                operation = operation.substring(0,position) + "+" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.decimalbtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "." + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.zerobtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                    lastopequals = false;
                }
                operation = operation.substring(0,position) + "0" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.negativebtn:
                if (lastopequals) {
                    operation = "";
                    position = 0;
                    solution = "";
                }
                operation = operation.substring(0,position) + "-" + operation.substring(position,operation.length());
                position++;
                break;
            case R.id.equalsbtn:
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

                    adapter.insert(operation + " = " + solution, 0);
                    if (adapter.getCount() > 50) {
                        String item = adapter.getItem(50);
                        adapter.remove(item);
                    }
                    mDrawerList.setAdapter(adapter);
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
        lastopequals = (v.getId() == R.id.equalsbtn && validstatement);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

        return super.onOptionsItemSelected(item);
    }
}

//TODO make icon