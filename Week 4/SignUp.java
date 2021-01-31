package com.sana.hgrapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_fullname , editText_email, editText_password, editText_confirmpassword;
    private TextView textView_signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        editText_fullname= findViewById(R.id.EditText_fullname);
        editText_email= findViewById(R.id.EditText_email);
        editText_password= findViewById(R.id.EditText_passowrd);
        editText_confirmpassword= findViewById(R.id.EditText_confirmpassowrd);
        textView_signup= findViewById(R.id.TextView_signup1);

        textView_signup.setOnClickListener(this);

        if (mAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), HomePage.class));
            finish();
        }

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.TextView_signup1:
                registerUser();
                break;
            
        }

    }

    private void registerUser() {

        String fullName= editText_fullname.getText().toString().trim();
        String email= editText_email.getText().toString().trim();
        String password= editText_password.getText().toString().trim();
        String confirmpassword= editText_confirmpassword.getText().toString().trim();

        if (fullName.isEmpty())
        {
            editText_fullname.setError("Enter full name");
            editText_fullname.requestFocus();
            return;
        }


       if (password.isEmpty())
        {
            editText_password.setError("Enter password");
            editText_password.requestFocus();
            return;
        }

        if (confirmpassword.isEmpty())
        {
            editText_confirmpassword.setError("Enter password again");
            editText_confirmpassword.requestFocus();
            return;
        }

        if (email.isEmpty())
        {
            editText_email.setError("Enter email");
            editText_email.requestFocus();
            return;
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editText_email.setError("Please enter valid email");
            editText_email.requestFocus();
            return;
        }

        if (password.length() < 8)
        {
            editText_password.setError("Passowrd must be 8 characteres long");
            editText_password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignUp.this, "User Sign Up Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HomePage.class));

                }
                else
                {
                    Toast.makeText(SignUp.this, "User Creation Failed" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}