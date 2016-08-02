package com.learnandroid.appworld.sharedpffrence;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.learnandroid.appworld.R;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DemoLayoutActivity extends AppCompatActivity {

    private TextView mTVDemo;
    private EditText mDetail1, mDetail2;
    private Button mButton;
    private String empty = "";
    private String textValue,textValue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_layout);
        init();
    }

    public void init(){

        mTVDemo = (TextView) findViewById(R.id.enter_details_demo_tv);

        mDetail1 = (EditText) findViewById(R.id.detail_one_demo_et);

        mDetail2 = (EditText) findViewById(R.id.detail_two_demo_et);

        mButton = (Button) findViewById(R.id.button_detail_demo);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DemoLayoutActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                saveTextValues();
                Intent i = new Intent(DemoLayoutActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });

    }

    public void saveTextValues(){
     textValue = mDetail1.getText().toString();
        textValue2 = mDetail2.getText().toString();
        if(!mDetail1.getText().toString().equals(textValue)){
            //Mydata below refers to the filename that is stored as shared preferences
            //Mode private will allow only this application to be able to read data.
            SharedPreferences preferences = getSharedPreferences("Mydata", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstValue", textValue);
            editor.putString("SecondValue",textValue2);
            editor.apply();
        }
    }

}
