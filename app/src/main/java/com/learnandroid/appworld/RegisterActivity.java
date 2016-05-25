package com.learnandroid.appworld;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextView mFirstNameLabel, mLastNameLabel, mUserNameLabel, mPassLabel, mConfirmPassLabel, mBtnBack;
    private Button mSignupBtn;
    private EditText mFirstName, mUserName, mPassword, mConPassword;
    private String mfistNameString, muserNameString, mpasswordString, mconPassString;
    private View baseView;
    private Intent newIntent;

    private DatabaseHelper mDBHelper = new DatabaseHelper(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
    }


    private void init() {


        mFirstName = (EditText) findViewById(R.id.et_name_signup);

        mPassword = (EditText) findViewById(R.id.et_pass_signup);

        mConPassword = (EditText) findViewById(R.id.et_confirm_pass_signup);

        mUserName = (EditText) findViewById(R.id.et_username_signup);

        backEventHandler();

        registerEventHandler();

    }

    private void backEventHandler() {
        mBtnBack = (TextView) findViewById(R.id.tv_btn_goback);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();

            }
        });
    }

    private void registerEventHandler() {
        mSignupBtn = (Button) findViewById(R.id.btn_signup);

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              mfistNameString = mFirstName.getText().toString();
                                              muserNameString = mUserName.getText().toString();
                                              mpasswordString = mPassword.getText().toString();
                                              mconPassString = mConPassword.getText().toString();


                                              if (mfistNameString.isEmpty() && muserNameString.isEmpty() ||
                                                      mpasswordString.isEmpty() && mconPassString.isEmpty()) {
                                                  Toast.makeText(RegisterActivity.this, "Invalid registration details",
                                                          Toast.LENGTH_SHORT).show();
                                                  mFirstName.setError("A value must be entered");
                                                  mUserName.setError("A value must be entered");
                                                  mPassword.setError("A value must be entered");
                                                  mConPassword.setError("A value must be entered");
                                              } else if (mfistNameString.isEmpty()) {
                                                  Toast.makeText(RegisterActivity.this, "Invalid registration details",
                                                          Toast.LENGTH_SHORT).show();
                                                  mFirstName.setError("A value must be entered");
                                              } else if (muserNameString.isEmpty()) {
                                                  Toast.makeText(RegisterActivity.this, "Invalid registration details",
                                                          Toast.LENGTH_SHORT).show();
                                                  mUserName.setError("A value must be entered");
                                              } else if (mpasswordString.isEmpty()) {
                                                  Toast.makeText(RegisterActivity.this, "Invalid registration details",
                                                          Toast.LENGTH_SHORT).show();
                                                  mPassword.setError("A value must be entered");
                                              } else if (mconPassString.isEmpty()) {
                                                  Toast.makeText(RegisterActivity.this, "Invalid registration details",
                                                          Toast.LENGTH_SHORT).show();
                                                  mConPassword.setError("A value must be entered");
                                              } else if (mpasswordString.equals(muserNameString)) {
                                                  Toast.makeText(RegisterActivity.this, "Username and password cannot have identical values",
                                                          Toast.LENGTH_SHORT).show();
                                                  mPassword.setError("Identical values");
                                                  mUserName.setError("Identical values");

                                              } else if (!mpasswordString.equals(mconPassString)) {
                                                  //popup msg
                                                  Toast.makeText(RegisterActivity.this, "Both password field must be identical",
                                                          Toast.LENGTH_SHORT).show();
                                                  mPassword.setError("password's do not match");
                                                  mConPassword.setError("password's do not match");
                                              } else {
                                                  userInfo con = new userInfo();
                                                  con.setName(mfistNameString);
                                                  con.setUsername(muserNameString);
                                                  con.setPass(mpasswordString);
                                                  if (mDBHelper != null && !mfistNameString.isEmpty() && !muserNameString.isEmpty()
                                                          && !mpasswordString.isEmpty() && !mconPassString.isEmpty()) {
                                                      mDBHelper.insertContactInfo(con);
                                                      mFirstName.getText().clear();
                                                      mUserName.getText().clear();
                                                      mPassword.getText().clear();
                                                      mConPassword.getText().clear();
                                                      mFirstName.setError(null);
                                                      mUserName.setError(null);
                                                      mPassword.setError(null);
                                                      mConPassword.setError(null);
                                                      navigateToLogin();
                                                      Toast.makeText(RegisterActivity.this, "Registration Successful",
                                                              Toast.LENGTH_SHORT).show();
                                                  }

                                              }
                                          }

                                      }

        );
    }

    private void navigateToLogin() {
        newIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(newIntent);
        finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateToLogin();
    }
}
