package com.learnandroid.appworld;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private TextView mTextUser, mTextUser2;

    private Button mButton;

    private String mUserName2;

    private Intent newIntent;
    AlertDialog.Builder mAlertDialog;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mUserName2 = getIntent().getStringExtra("Username");
        init();
        onLogoutClickHandler();
    }

    private void init() {

        mTextUser = (TextView) findViewById(R.id.userActivityTVL);

        mTextUser2 = (TextView) findViewById(R.id.userActivityTVL2);


        mButton = (Button) findViewById(R.id.btn_logout_user);

        if (mTextUser2 != null) {
            mTextUser2.setText(mUserName2.toUpperCase());
        }
    }

    private void onLogoutClickHandler() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogOffDialog();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder alert = new AlertDialog.Builder(UserActivity.this,
                R.style.AppCompatAlertDialogStyle);
        alert.setMessage("Do you want to logout?");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLogin();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void showLogOffDialog() {
        mAlertDialog = new AlertDialog.Builder(UserActivity.this, R.style.AppCompatAlertDialogStyle);
        mAlertDialog.setMessage("Are you sure you want to log off?");
        mAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLogin();

            }
        });

        mAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mAlertDialog.show();
    }

    private void showLogin() {
        finish();
        newIntent = new Intent(UserActivity.this, LoginActivity.class);
        startActivity(newIntent);

    }
}
