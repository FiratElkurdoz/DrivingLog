package com.example.driveandlog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;


public class List extends AppCompatActivity {
    //Arrays
    ArrayList<String> shoppingList = null;
    ArrayAdapter<String> adapter = null;
    ListView lv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Det her virker, hvis de nederste funktioner er sat til
       // shoppingList = getArrayVal(getApplicationContext());
        shoppingList = new ArrayList<>();
        //Collections.addAll(shoppingList, "Roadtrip", "aRoadtrip", "Roadtrip", "cRoadtrip", "Roadtrip", "DRoadtrip", "Roadtrip", "Roadtrip", "Roadtrip", "AARoadtrip", "BBRoadtrip", "KRoadtrip", "Roadtrip", "TRoadtrip", "Roadtrip", "Roadtrip", "Roadtrip");
        //shoppingList.addAll(Arrays.asList("en", "to"));
        //shoppingList.add("Monkey");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shoppingList);
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_sort) {
            Collections.sort(shoppingList);
            lv.setAdapter(adapter);
            return true;
        }

        if (id == R.id.action_add) {
            //Create alertdialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add item");
            //Allowing us to edit (type)
            final EditText input = new EditText(this);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    shoppingList.add(preferredCase(input.getText().toString()));
                    //Collections.sort(shoppingList);
                    //Sorting so the newest is the top one
                    Collections.reverse(shoppingList);
                    //storeArrayVal(shoppingList, getApplicationContext());
                    lv.setAdapter(adapter);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static String preferredCase(String original)
    {
        //If empty return nothing
        if (original.isEmpty())
            return original;

        //if not empty, make first letter to upper case and the rest to lower case
        return original.substring(0,1).toUpperCase() + original.substring(1).toLowerCase();
    }

    //Androids egen internal storage, sharedpreferrences, virker dog ikke, hvis loginsiden er til, men kan bruges med databasen
/*
    public static void storeArrayVal (ArrayList inArrayList, Context context){
        Set WhatToWrite = new HashSet(inArrayList);
        SharedPreferences WordSearchPutPrefs = context.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = WordSearchPutPrefs.edit();
        prefEditor.putString("myArray", String.valueOf(WhatToWrite));
        prefEditor.commit();
    }

    public static ArrayList getArrayVal (Context dan){
        SharedPreferences WordSearchGetPrefs = dan.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        Set tempSet = new HashSet();
        tempSet = WordSearchGetPrefs.getStringSet("myArray", tempSet);
        return new ArrayList<>(tempSet);

    }
*/
}
