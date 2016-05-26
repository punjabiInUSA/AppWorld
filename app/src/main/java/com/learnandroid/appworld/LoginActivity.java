package com.learnandroid.appworld;

import android.app.ActivityManager;
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

import java.util.List;

/**
 * Contains Views and Handlers for Login Related Functionality
 */


public class LoginActivity extends AppCompatActivity {

    private EditText mUsername, mPassword;

    private TextView mSignTV;
    private Button mLoginBtn;
    private AlertDialog.Builder mAlertDialog;
    private DatabaseHelper mDBHelper = new DatabaseHelper(this);
    private ActivityManager mActivityManager;
    private String mAuthenticateExistence, mUserNameString, mPasswordString;


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
                mPasswordString = mPassword.getText().toString();
                mAuthenticateExistence = mDBHelper.searchPass(mUserNameString);

                if (mPasswordString.equals(mAuthenticateExistence)) {
                    finish();
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, UserActivity.class);
                    i.putExtra("Username", mDBHelper.searchFName(mUserNameString));
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials. " +
                            "Please check username and password", Toast.LENGTH_SHORT)
                            .show();
                }
                
            }
        });

    }

    @Override
    public void onBackPressed() {
        mAlertDialog = new AlertDialog.Builder(LoginActivity.this, R.style.AppCompatAlertDialogStyle);
        mAlertDialog.setTitle(getString(R.string.exit_app_dialog_title_login));
        mAlertDialog.setMessage(getString(R.string.exit_app__dialog_detail_login));
        mAlertDialog.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                if (mActivityManager != null) {
                    List<ActivityManager.AppTask> tasks = mActivityManager.getAppTasks();
                    if (tasks != null && tasks.size() > 0) {
                        tasks.get(0).setExcludeFromRecents(true);
                    }
                }
                mAlertDialog = null;
            }

        });
        mAlertDialog.setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            }
        });
        if(mAlertDialog != null) {
            mAlertDialog.show();
        } else{
        super.onBackPressed();
        }

    }
}
