package com.learnandroid.appworld;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextView mBtnBack;
    private Button mSignupBtn;
    private EditText mFirstName, mUserName, mPassword, mConPassword;
    private String mfistNameString, muserNameString, mpasswordString, mconPassString;
    private Intent newIntent;

    private DatabaseHelper mDBHelper = new DatabaseHelper(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }


    private void init() {

        setTitle(getString(R.string.empty_string));

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


                          if (mfistNameString.isEmpty() && muserNameString.isEmpty() &&
                                  mpasswordString.isEmpty() && mconPassString.isEmpty()) {
                              mFirstName.setError("A value must be entered");
                              mUserName.setError("A value must be entered");
                              mPassword.setError("A value must be entered");
                              mConPassword.setError("A value must be entered");
                          } else if (muserNameString.isEmpty() && mpasswordString.isEmpty() &&
                                  mconPassString.isEmpty()) {
                              mFirstName.setError("null");
                              mFirstName.getText().clear();
                              mFirstName.setText(mfistNameString);

                              mUserName.setError("A value must be entered");

                              mPassword.setError("A value must be entered");

                              mConPassword.setError("A value must be entered");

                          } else if (mfistNameString.isEmpty() && muserNameString.isEmpty()
                                  && mconPassString.isEmpty()) {
                              mUserName.setError("A value must be entered");

                              mFirstName.setError("A value must be entered");

                              mConPassword.setError("password's do not match");

                              mPassword.setError("null");
                              mPassword.getText().clear();
                              mPassword.setText(mpasswordString);
                          } else if (mfistNameString.isEmpty() && muserNameString.isEmpty()
                                  && mpasswordString.isEmpty()) {
                              mUserName.setError("A value must be entered");

                              mFirstName.setError("A value must be entered");

                              mPassword.setError("password's do not match");

                              mConPassword.setError("null");
                              mConPassword.getText().clear();
                              mConPassword.setText(mconPassString);
                          } else if (mfistNameString.isEmpty() && mconPassString.isEmpty()
                                  && mpasswordString.isEmpty()) {
                              mConPassword.setError("password's do not match");

                              mFirstName.setError("A value must be entered");

                              mPassword.setError("password's do not match");

                              mConPassword.setError("null");
                              mConPassword.getText().clear();
                              mConPassword.setText(mconPassString);
                          }else if (mpasswordString.isEmpty() && mconPassString.isEmpty()) {

                              mFirstName.setError(null);
                              mFirstName.getText().clear();
                              mFirstName.setText(mfistNameString);

                              mUserName.setError(null);
                              mUserName.getText().clear();
                              mUserName.setText(muserNameString);

                              mPassword.setError("Enter value, limit 15 characters");

                              mConPassword.setError("Enter value, limit 15 characters");

                          } else if (mfistNameString.isEmpty()) {

                              mFirstName.setError("A value must be entered");

                              mUserName.setError(null);
                              mUserName.getText().clear();
                              mUserName.setText(muserNameString);

                              mPassword.setError(null);
                              mPassword.getText().clear();
                              mPassword.setText(mpasswordString);

                              mConPassword.setError(null);
                              mConPassword.getText().clear();
                              mConPassword.setText(mconPassString);


                          } else if (muserNameString.isEmpty()) {

                              mUserName.setError("A value must be entered");

                              mFirstName.setError(null);
                              mFirstName.getText().clear();
                              mFirstName.setText(mfistNameString);

                              mPassword.setError(null);
                              mPassword.getText().clear();
                              mPassword.setText(mpasswordString);

                              mConPassword.setError(null);
                              mConPassword.getText().clear();
                              mConPassword.setText(mconPassString);
                          } else if (mpasswordString.isEmpty()) {

                              mPassword.setError("password's do not match");
                              mFirstName.setError(null);
                              mFirstName.getText().clear();
                              mFirstName.setText(mfistNameString);

                              mUserName.setError(null);
                              mUserName.getText().clear();
                              mUserName.setText(muserNameString);

                              mConPassword.setError(null);
                              mConPassword.getText().clear();
                              mConPassword.setText(mconPassString);
                          } else if (mconPassString.isEmpty()) {

                              mConPassword.setError("password's do not match");
                              mFirstName.setError(null);
                              mFirstName.getText().clear();
                              mFirstName.setText(mfistNameString);

                              mUserName.setError(null);
                              mUserName.getText().clear();
                              mUserName.setText(muserNameString);

                              mPassword.setError(null);
                              mPassword.getText().clear();
                              mPassword.setText(mpasswordString);
                          } else if (mpasswordString.equals(muserNameString)) {

                              mPassword.setError("Identical values");
                              mUserName.setError("Identical values");

                              mFirstName.setError(null);
                              mFirstName.getText().clear();
                              mFirstName.setText(mfistNameString);

                              mConPassword.setError(null);
                              mConPassword.getText().clear();
                              mConPassword.setText(mconPassString);

                          } else if (!mpasswordString.equals(mconPassString)) {
                              //popup msg

                              mPassword.setError("password's do not match");
                              mConPassword.setError("password's do not match");

                              mFirstName.setError(null);
                              mFirstName.getText().clear();
                              mFirstName.setText(mfistNameString);

                              mUserName.setError(null);
                              mUserName.getText().clear();
                              mUserName.setText(muserNameString);

                          } else {
                              UserInfoModel uInfo = new UserInfoModel();
                              uInfo.setName(mfistNameString);
                              uInfo.setUsername(muserNameString);
                              uInfo.setPass(mpasswordString);
                              if (mDBHelper != null && !mfistNameString.isEmpty()
                                      && !muserNameString.isEmpty()
                                      && !mpasswordString.isEmpty() && !mconPassString.isEmpty()) {
                                  mDBHelper.insertContactInfo(uInfo);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                navigateToLogin();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
