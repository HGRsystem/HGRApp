package com.sana.hgrapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainScreen extends AppCompatActivity implements View.OnClickListener {

    Button btn_signin, btn_signup;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        btn_signin=findViewById(R.id.Button_signin);
        btn_signup=findViewById(R.id.Button_signup);

        btn_signin.setOnClickListener(this);
        btn_signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        if (id==R.id.Button_signin) {
            Intent intent = new Intent(getApplicationContext(), SignIn.class);
            startActivity(intent);
        }
        else if (id==R.id.Button_signup){
            Intent intent = new Intent(getApplicationContext(), SignUp.class);
            startActivity(intent);

        }
        

    }
}
