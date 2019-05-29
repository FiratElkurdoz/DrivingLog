package com.example.driveandlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button AlreadyMember = (Button) findViewById(R.id.btn_createacc);

        AlreadyMember.setOnClickListener(v -> startActivity(new Intent(SignUp.this, LogIn.class)));

        Button SignUp = (Button) findViewById(R.id.btn_login);

        SignUp.setOnClickListener(v -> startActivity(new Intent(SignUp.this, LogIn.class)));

    }
}
