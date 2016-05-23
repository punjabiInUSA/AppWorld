package com.learnandroid.appworld;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity{

    private TextView mFirstNameLabel;

    private TextView mLastNameLabel;
    private TextView mUserNameLabel;

    private TextView mPassLabel;
    private TextView mConfirmPassLabel;
    private Button mSignupBtn;

    private EditText mFirstNet;
    private EditText mUserNet;

    private EditText mLastet;

    private EditText mPasset;

    private EditText mConPasset;


    private String mfnV;

    private String mlnV;
    private String munV;
    private String mpV;
    private String mcpV;
    private String mIdV;

private DatabaseHelper mDBHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
    }



    private void init() {


        mFirstNet = (EditText) findViewById(R.id.et_firstname_signup);

        mLastet = (EditText) findViewById(R.id.et_lastname_signup);

        mPasset = (EditText) findViewById(R.id.et_pass_signup);

        mConPasset = (EditText) findViewById(R.id.et_confirm_pass_signup);

        mUserNet = (EditText) findViewById(R.id.et_username_signup);

        mSignupBtn = (Button) findViewById(R.id.btn_register_signup);



        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        mfnV = mFirstNet.getText().toString();
        mlnV = mLastet.getText().toString();
        munV = mUserNet.getText().toString();
        mpV = mPasset.getText().toString();
        mcpV = mConPasset.getText().toString();


                if(!mpV.equals(mcpV)){
                //popup msg
                Toast.makeText(SignupActivity.this, "Passwords don't match",
                        Toast.LENGTH_SHORT).show();
            }
                else {

                    Contact con = new Contact();
                    con.setName(mfnV);
                    con.setUsername(munV);
                    con.setPass(mpV);
                    if(mDBHelper !=null) {
                        mDBHelper.insertContactInfo(con);
                    }
                }

            }
        });

    }
}
