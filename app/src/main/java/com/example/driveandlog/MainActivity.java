package com.example.driveandlog;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private UserViewModel mUserViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UserListAdapter adapter = new UserListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        mUserViewModel.getAllUsers().observe((LifecycleOwner) this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> users){
                // Updater cache
                adapter.setUsers(users);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Random rand = new Random();
        int randomNumber = rand.nextInt(10 - 1 + 1) + 1;
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            // Hvis vi får problemer med at få flere af brugerens indtastede felter, så er det her det går galt.
            User user =  new User(randomNumber, data.getStringExtra(SignupActivity.EXTRA_REPLY), data.getStringExtra(SignupActivity.EXTRA_REPLY));
        }
    }




}
