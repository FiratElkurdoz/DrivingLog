package com.example.driveandlog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Region;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

import android.widget.Toast;
import android.widget.TextView;
import android.widget.AdapterView;


public class List extends AppCompatActivity {

    private static final String TAG = "List";

    //Arrays
    ArrayList<String> shoppingList = null;
    ArrayAdapter<String> adapter = null;
    ListView lv = null;

    //Thread Stuff
    Thread workerThread;
    //Semaphore for keeping track of thread
    volatile boolean running = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        //Create a thread
        workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Make sure the thread is still supposed to run.
                while (running) {

                    // shoppingList = new ArrayList<>();

                    // shoppinglist = get value from db

                    //Have thread sleep for 10 seconds (10.000 ms)
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //start the thread
        workerThread.start();



    /*
    @Override
    protected void onDestroy() {
        // Stop running the thread
        running = false;
        super.onDestroy();
        try {
            workerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

        shoppingList = new ArrayList<>();
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


    public void removeElement(String selectedItem, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove " + selectedItem + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shoppingList.remove(position);
                Collections.reverse(shoppingList);
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
