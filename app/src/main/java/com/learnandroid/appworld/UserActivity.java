package com.learnandroid.appworld;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private TextView mTextUser, mTextUser2;

    private String mFullName, mUserName;

    private Intent newIntent;
    AlertDialog.Builder mAlertDialog, mAlert;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mFullName = getIntent().getStringExtra("FullName");
        setTitle(getString(R.string.empty_string));
        init();
    }

    private void init() {

        mTextUser = (TextView) findViewById(R.id.userActivityTVL);

        mTextUser2 = (TextView) findViewById(R.id.userActivityTVL2);

        if (mTextUser2 != null) {
            mTextUser2.setText(mFullName);
        }
    }

    @Override
    public void onBackPressed() {
        mAlert = new AlertDialog.Builder(UserActivity.this,
                R.style.AppCompatAlertDialogStyle);
        mAlert.setMessage("Do you want to logout?");
        mAlert.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLogin();
            }
        });
        mAlert.setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        if (mAlert != null) {
            mAlert.show();
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_logout:
                showLogOffDialog();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showLogOffDialog() {
        mAlertDialog = new AlertDialog.Builder(UserActivity.this, R.style.AppCompatAlertDialogStyle);
        mAlertDialog.setMessage("Are you sure you want to log off?");
        mAlertDialog.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLogin();

            }
        });

        mAlertDialog.setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
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
