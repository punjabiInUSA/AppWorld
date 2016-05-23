package com.learnandroid.appworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView mEmailLabel;

    private EditText mUsername;

    private EditText mPassword;

    private Button mLoginBtn;

    private Button mSignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewOnLoad();
    }

    private void viewOnLoad() {

        mEmailLabel = (TextView) findViewById(R.id.tv_email_label_login);

        mUsername = (EditText) findViewById(R.id.et_username_login);

        mPassword = (EditText) findViewById(R.id.et_password_login);

        mLoginBtn = (Button) findViewById(R.id.btn_login);

        mSignupBtn = (Button) findViewById(R.id.btn_signup);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str =  mUsername.getText().toString();

                Intent i = new Intent(LoginActivity.this, UserActivity.class);
                i.putExtra("Username", str);

                startActivity(i);

            }
        });

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);

            }
        });

    }



}