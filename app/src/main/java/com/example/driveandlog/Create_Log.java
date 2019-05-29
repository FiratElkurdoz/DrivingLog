package com.example.driveandlog;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Create_Log extends AppCompatActivity {


    private Text addEmail;

    ArrayList<String> partiList = null;
    ArrayAdapter<String> adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__log);




        Button addBtn = (Button) findViewById(R.id.addPartiBtn);

        addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                addEmail = findViewById(R.id.create_Email);
            }
        });



    }

}
