package com.learnandroid.appworld.sharedpffrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.learnandroid.appworld.R;

public class SecondActivity extends AppCompatActivity {

    private TextView mTv1, mTv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_layout_2);
        init();
        retrieveData();
    }

    public void init() {
        mTv1 = (TextView) findViewById(R.id.second_tv1);

        mTv2 = (TextView) findViewById(R.id.second_tv2);
    }

    public void retrieveData(){
        SharedPreferences preferences = getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        String firstVal = preferences.getString("FirstValue", "");
        String secondVal = preferences.getString("SecondValue","");
        //If the first value is not equal to empty then display all values.
        if(!firstVal.equalsIgnoreCase(""))
        {   mTv1.setText(firstVal);
            mTv2.setText(secondVal);
        }
    }
}
