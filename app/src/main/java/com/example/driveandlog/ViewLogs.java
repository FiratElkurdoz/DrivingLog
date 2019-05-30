package com.example.driveandlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewLogs extends AppCompatActivity {


    ArrayList<String> loadlist = null;
    ArrayAdapter<String> adapter = null;

     ListView lv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_logs);


        loadlist = new ArrayList<>();
        loadData();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,loadlist );
        lv = (ListView) findViewById(R.id.lvload);
        lv.setAdapter(adapter);






    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("partiList", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        loadlist = gson.fromJson(json, type);

        if (loadlist == null){
            loadlist = new ArrayList<>();
        }
    }


}
