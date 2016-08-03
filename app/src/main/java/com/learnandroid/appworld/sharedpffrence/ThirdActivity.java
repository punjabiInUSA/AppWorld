package com.learnandroid.appworld.sharedpffrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.learnandroid.appworld.R;

public class ThirdActivity extends AppCompatActivity {

    private EditText mET1, mET2, mET3;
    private Button mBtn;
    private TextView mTV1, mTV2;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mET1 = (EditText) findViewById(R.id.reset_key_edit_text);
        mET2 = (EditText) findViewById(R.id.reset_password_edit_text);
        mET3 = (EditText) findViewById(R.id.reset_confirm_password_edit_text);
        mBtn = (Button) findViewById(R.id.reset_button);
        mTV1 = (TextView) findViewById(R.id.reset_old_password);
        mTV2 = (TextView) findViewById(R.id.reset_new_password);
//        setOldPass();
    }

//    public void setOldPass(){
//        SharedPreferences pref = mContext.getSharedPreferences("MyData", Context.MODE_PRIVATE);
//        mTV1.setText(pref.getString("FirstValue",""));
//    }

}
