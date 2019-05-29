package com.example.driveandlog;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Create_Log extends AppCompatActivity {


    private EditText addEmail;
    private String added;

    ArrayList<String> partiList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter = null;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__log);

        lv = (ListView) findViewById(R.id.create_Log_List);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                partiList );

        Button addBtn = (Button) findViewById(R.id.addPartiBtn);

        addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText addEmail = (EditText)findViewById(R.id.create_Email);
                added = addEmail.getText().toString();
                partiList.add(added);
                lv.setAdapter(arrayAdapter);

            }
        });



    }

}
