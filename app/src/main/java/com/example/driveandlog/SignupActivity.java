package com.example.driveandlog;


import android.app.LoaderManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.driveandlog.MainActivity.NEW_WORD_ACTIVITY_REQUEST_CODE;


public class SignupActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditNameView;
    private EditText mEditMailView;
    private EditText mEditPasswordView;

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.activity_register);
        mEditNameView = findViewById(R.id.input_name);
        mEditMailView = findViewById(R.id.input_email);
        mEditPasswordView = findViewById(R.id.input_password);

        final Button button = findViewById(R.id.btn_signup);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditMailView.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else if (TextUtils.isEmpty(mEditNameView.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else if (TextUtils.isEmpty(mEditPasswordView.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String userName = mEditNameView.getText().toString();
                    String userMail = mEditMailView.getText().toString();
                    String userPassword = mEditPasswordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, userName);
                    replyIntent.putExtra(EXTRA_REPLY, userMail);
                    replyIntent.putExtra(EXTRA_REPLY, userPassword);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();


            }
        });
        
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
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }
}
