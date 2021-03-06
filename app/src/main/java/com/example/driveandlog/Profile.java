package com.example.driveandlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

ImageView imageView;
    Button button;
private static final int PICK_IMAGE = 100;
Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);

        // getXXX(key, default value)
        String name = prefs.getString("MY_NAME", "no name");
        String email = prefs.getString("MY_EMAIL", "no email");

        // Set values
        ((TextView)findViewById(R.id.nameProfile)).setText(name);
        ((TextView)findViewById(R.id.emailProfile)).setText(email);


        imageView = findViewById(R.id.imageView2);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(v -> openGallery());

        Button mapPageButton = findViewById(R.id.button2);

        mapPageButton.setOnClickListener(v -> startActivity(new Intent(Profile.this, List.class)));

        Button logsButton = findViewById(R.id.button3);

        logsButton.setOnClickListener(v -> {
            if (Profile.this != Profile.this) {
            startActivity(new Intent(Profile.this, Profile.class));
        }});

        Button profileButton = findViewById(R.id.button4);

        profileButton.setOnClickListener(v -> startActivity(new Intent(Profile.this, Map_page.class)));



    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }


}
