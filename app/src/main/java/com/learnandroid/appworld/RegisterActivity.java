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

public class RegisterActivity extends AppCompatActivity{

    private TextView mFirstNameLabel;

    private TextView mLastNameLabel;
    private TextView mUserNameLabel;

    private TextView mPassLabel;
    private TextView mConfirmPassLabel;
    private Button mSignupBtn;

    private EditText mFirstName;
    private EditText mUserName;

    private EditText mLastet;

    private EditText mPassword;

    private EditText mConPassword;


    private String mfistNameString;

    private String mlnV;
    private String muserNameString;
    private String mpasswordString;
    private String mconPassString;
    private String mIdString;

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

        mSignupBtn = (Button) findViewById(R.id.btn_signup);



        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        mfistNameString = mFirstName.getText().toString();
        muserNameString = mUserName.getText().toString();
        mpasswordString = mPassword.getText().toString();
        mconPassString = mConPassword.getText().toString();


                if(!mpasswordString.equals(mconPassString)){
                //popup msg
                Toast.makeText(RegisterActivity.this, "Passwords don't match",
                        Toast.LENGTH_SHORT).show();
            } else {

                    userInfo con = new userInfo();
                    con.setName(mfistNameString);
                    con.setUsername(muserNameString);
                    con.setPass(mpasswordString);
                    if (mDBHelper != null && !mfistNameString.isEmpty() && !muserNameString.isEmpty() && !mpasswordString.isEmpty()) {
                        mDBHelper.insertContactInfo(con);
                        mFirstName.getText().clear();
                        mFirstName.setHint("");
                        mUserName.getText().clear();
                        mUserName.setHint("");
                        mPassword.getText().clear();
                        mPassword.setHint("");
                        mConPassword.getText().clear();
                        mConPassword.setHint("");
                        newIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(newIntent);
                        finish();
                        Toast.makeText(RegisterActivity.this, "Registration Successful",
                                Toast.LENGTH_SHORT).show();
                    }

                    
                    Toast.makeText(RegisterActivity.this, "Invalid registration details", Toast.LENGTH_SHORT).show();

                }
            }

        });

    }

}
