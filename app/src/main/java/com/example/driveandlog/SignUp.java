package com.example.driveandlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    private EditText nameInput;
    private EditText emailInput;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        String name = prefs.getString("MY_NAME", "");
        String email = prefs.getString("MY_EMAIL", "");

        nameInput = findViewById(R.id.input_name);
        emailInput = findViewById(R.id.input_email);


        nameInput.setText(name);
        emailInput.setText(email);

        Button AlreadyMember = findViewById(R.id.btn_createacc);

        AlreadyMember.setOnClickListener(v -> startActivity(new Intent(SignUp.this, LogIn.class)));

        Button SignUp = findViewById(R.id.btn_login);

        SignUp.setOnClickListener(v -> {
            startActivity(new Intent(SignUp.this, LogIn.class));
            saveData();
        });

    }

    public void saveData() {
        // Get input text.
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();

        // Save data.
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("MY_NAME", name);
        editor.putString("MY_EMAIL", email);
        editor.apply();
    }
}