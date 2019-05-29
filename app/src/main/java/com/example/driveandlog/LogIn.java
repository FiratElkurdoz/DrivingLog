package com.example.driveandlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        Button AlreadyMember = (Button) findViewById(R.id.btn_login);

        AlreadyMember.setOnClickListener(v -> startActivity(new Intent(LogIn.this, Map_page.class)));

        Button SignUp = (Button) findViewById(R.id.btn_createacc);

        SignUp.setOnClickListener(v -> startActivity(new Intent(LogIn.this, SignUp.class)));

    }
}
