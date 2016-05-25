package com.learnandroid.appworld;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Contains Views and Handlers for Login Related Functionality
 */


public class LoginActivity extends AppCompatActivity {

    private EditText mUsername, mPassword;

    private TextView mSignTV;
    private Button mLoginBtn;

    private DatabaseHelper mDBHelper = new DatabaseHelper(this);

    private String mAuthenticateExistence, mUserNameString;


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
        mSignTV = (TextView) findViewById(R.id.tv_btn_signup);
        mSignTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });
    }

    private void loginEventHandler() {


        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUserNameString = mUsername.getText().toString();
                String passWordString = mPassword.getText().toString();
                mAuthenticateExistence = mDBHelper.searchPass(mUserNameString);

                if (passWordString.equals(mAuthenticateExistence)) {
                    finish();
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, UserActivity.class);
                    i.putExtra("Username", mUserNameString);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials. " +
                            "Please check username and password", Toast.LENGTH_SHORT)
                            .show();
                }


            }
        });


    }


}
