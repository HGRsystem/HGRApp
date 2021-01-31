package com.example.hgrapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView_camera, imageView_account, imageView_tutorials, imageView_videocall, imageView_settings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        imageView_camera=findViewById(R.id.ImageView_camera);
        imageView_account=findViewById(R.id.ImageView_account);
        imageView_tutorials=findViewById(R.id.ImageView_tutorials);
        imageView_videocall=findViewById(R.id.ImageView_videocall);
        imageView_settings=findViewById(R.id.ImageView_settings);

        imageView_camera.setOnClickListener(this);
        imageView_account.setOnClickListener(this);
        imageView_tutorials.setOnClickListener(this);
        imageView_videocall.setOnClickListener(this);
        imageView_settings.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id==R.id.ImageView_camera)
        {
            Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
            startActivity(intent);
        }

        else  if (id==R.id.ImageView_account)
        {
            Intent intent = new Intent(getApplicationContext(), AccountDetails.class);
            startActivity(intent);
        }

        else  if (id==R.id.ImageView_videocall)
        {
            Intent intent = new Intent(getApplicationContext(), VideoCall.class);
            startActivity(intent);
        }

        else  if (id==R.id.ImageView_tutorials)
        {
            Intent intent = new Intent(getApplicationContext(), TutorialVideos.class);
            startActivity(intent);
        }

        else  if (id==R.id.ImageView_settings)
        {
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
        }

    }
}
