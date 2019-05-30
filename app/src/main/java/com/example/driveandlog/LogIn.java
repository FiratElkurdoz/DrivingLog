package com.example.driveandlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

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

    @Override
    protected void onStart(){
        super.onStart();

        Toast.makeText(getApplicationContext(), "Lifecycle: Started", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause(){
        super.onPause();

        Toast.makeText(getApplicationContext(), "Lifecycle: Paused", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume(){
        super.onResume();

        Toast.makeText(getApplicationContext(), "Lifecycle: Resumed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop(){
        super.onStop();

        Toast.makeText(getApplicationContext(), "Lifecycle: Stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        Toast.makeText(getApplicationContext(), "Lifecycle: Destroyed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        Toast.makeText(getApplicationContext(), "Lifecycle: Restarted", Toast.LENGTH_LONG).show();
    }
}
