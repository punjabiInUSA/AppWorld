package com.learnandroid.appworld;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private TextView mTextUser;

    private TextView mTextUser2;

    private String mUserName2;

    private Intent newIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
         mUserName2 = getIntent().getStringExtra("Username");
        init();
    }

    private void init() {

        mTextUser = (TextView) findViewById(R.id.userActivityTVL);

        mTextUser2 = (TextView) findViewById(R.id.userActivityTVL2);

        if (mTextUser2 != null) {
            mTextUser2.setText(mUserName2);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        newIntent = new Intent(UserActivity.this,LoginActivity.class);
        startActivity(newIntent);
        finish();

    }


}
