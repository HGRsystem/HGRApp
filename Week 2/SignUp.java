package com.example.hgrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText fullname, email, password, confirmpassword;
    TextView signup1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullname=findViewById(R.id.EditText_fullname);
        email=findViewById(R.id.EditText_email);
        password=findViewById(R.id.EditText_passowrd);
        confirmpassword=findViewById(R.id.EditText_confirmpassowrd);
        signup1=findViewById(R.id.TextView_signup1);



        signup1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        if (id==R.id.TextView_signup1)
        {
            Intent intent= new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent);

        }

    }
}