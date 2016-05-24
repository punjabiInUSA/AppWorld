package com.learnandroid.appworld;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Contains Views and Handlers for Login Related Functionality
 */


public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;

    private EditText mPassword;

    private Button mLoginBtn;

    private Button mSignupBtn;

    private DatabaseHelper mDBHelper = new DatabaseHelper(this);

    private String mAuthenticateExistence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onViewLoad();
    }

    private void onViewLoad() {

        mUsername = (EditText) findViewById(R.id.et_username_login);

        mPassword = (EditText) findViewById(R.id.et_password_login);

        loginEventHandler();

        signupEventHandler();

    }

    private void signupEventHandler() {
        mSignupBtn = (Button) findViewById(R.id.btn_signup);
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void loginEventHandler() {

        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userNameString =  mUsername.getText().toString();

                String passWordString = mPassword.getText().toString();

                mAuthenticateExistence = mDBHelper.searchPass(userNameString);

                if(passWordString.equals(mAuthenticateExistence)) {
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, UserActivity.class);
                    i.putExtra("Username", userNameString);
                    startActivity(i);

                }
                    else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials. Please check username and password", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });



    }



}
