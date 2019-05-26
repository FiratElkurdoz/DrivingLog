package com.example.driveandlog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import android.widget.Toast;
import android.widget.TextView;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class List extends AppCompatActivity {

    private static final String TAG = "List";

    //Arrays
    ArrayList<String> shoppingList = null;
    ArrayAdapter<String> adapter = null;
    ListView lv = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

/*
        Runnable r = new Runnable() {
            @Override
            public void run() {
                shoppingList = new ArrayList<>();
            }
        };

        Thread FiratsThread = new Thread(r);
        FiratsThread.start();

*/
        //shoppingList = new ArrayList<>();
        loadData();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shoppingList);
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        //DELETE INDIVIDUAL THINGS
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if ( selectedItem.trim().equals(shoppingList.get(position).trim())) {
                    removeElement(selectedItem, position);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error removing element", Toast.LENGTH_SHORT).show();
                }
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
                    Collections.reverse(shoppingList);
                    saveData();
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

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //gson: et google libary der gør det let at konvertere java objekter til json
        Gson gson = new Gson();
        String json = gson.toJson(shoppingList);
        editor.putString("task list", json);
        editor.apply();
        }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        shoppingList = gson.fromJson(json, type);

        if (shoppingList == null){
            shoppingList = new ArrayList<>();
        }
    }

    public static String preferredCase(String original)
    {
        //If empty return nothing
        if (original.isEmpty())
            return original;

        //if not empty, make first letter to upper case and the rest to lower case
        return original.substring(0,1).toUpperCase() + original.substring(1).toLowerCase();
    }


    public void removeElement(String selectedItem, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove " + selectedItem + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shoppingList.remove(position);
                Collections.reverse(shoppingList);
                saveData();
                lv.setAdapter(adapter);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

}
