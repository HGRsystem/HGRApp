package com.example.hgrapp;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import static com.google.firebase.auth.FacebookAuthProvider.getCredential;
import static com.google.firebase.auth.FirebaseAuthProvider.*;

public class SignIn extends AppCompatActivity {

    EditText editText_username, editText_password;
    TextView textView_forgetpassword, textView_signin;
    LoginButton loginButton;
    CallbackManager mcallbackManager;
    FirebaseAuth mFirebaseAuth;
    private static final String TAG = "FacebookAuthentication";
    public  FirebaseAuth.AuthStateListener authStateListener;
    AccessTokenTracker accessTokenTracker;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mFirebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());

        editText_username= findViewById(R.id.EditText_email);
        editText_password= findViewById(R.id.EditText_passowrd);
        loginButton= findViewById(R.id.login_button);
        loginButton.setReadPermissions("email" , "public_profile");
        mcallbackManager= CallbackManager.Factory.create();
        loginButton.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

                Log.d(TAG, "onCancel");

            }

            @Override
            public void onError(FacebookException error) {

                Log.d(TAG, "onError" + error);
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!= null)
                {
                    updateUI(user);
                }
                else
                {
                    updateUI(null);
                }
            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken==null)
                {
                    mFirebaseAuth.signOut();
                }
            }
        };



    }

    private void handleFacebookToken(AccessToken accessToken) {
        Log.d(TAG, "handleFacebookToken" + accessToken);

        AuthCredential credential= FacebookAuthProvider.getCredential(accessToken.getToken());
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Log.d(TAG, "sign in with credential: successful");
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    updateUI(user);
                }
                else
                {
                    Log.d(TAG, "sign in with credential: failure" , task.getException());
                    Toast.makeText(SignIn.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                    updateUI(null);

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        mcallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(FirebaseUser user) {
        if ((user != null))
        {
            editText_username.setText(user.getDisplayName());
        }

        else
        {
            editText_username.setText("");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (authStateListener !=null)
        {
            mFirebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}
