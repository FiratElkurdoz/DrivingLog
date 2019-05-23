package com.example.driveandlog;


import android.app.LoaderManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.activity_register);
        
        logInButton();
    }

    private void logInButton(){
        Button logInButton = findViewById(R.id.button_login);
        logInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }
    
    private void signUpButton(){
        Button signUpButton = findViewById(R.id.btn_signup);
        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }
}
