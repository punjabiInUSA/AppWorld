package com.learnandroid.appworld.sharedpffrence;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.learnandroid.appworld.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity {

    private TextView mTv1, mTv2, mTv3, mTv4;
    PinGenerator mPinGen = new PinGenerator();
    Context mContext;
    SharedPreferences mPreferences;

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

        mTv3 = (TextView) findViewById(R.id.second_encrypted_key);

        mTv4 = (TextView) findViewById(R.id.second_pin_code);

        mTv2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(i);
                return false;
            }
        });
    }

    public void retrieveData() {
        SharedPreferences preferences = getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        String firstVal = preferences.getString("FirstValue", "");
        String secondVal = preferences.getString("SecondValue", "");
        String encryptPass = EncryptionUtil.encryptPassword(firstVal);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Encrypted", encryptPass);
        editor.apply();
        //If the first value is not equal to empty then display all values.
        if (!firstVal.equalsIgnoreCase("")) {
            mTv1.setText(firstVal);
            mTv2.setText(secondVal);
            mTv3.setText(encryptPass);

//         mPinCode =   preferences.getString("PinCode","");
            mTv4.setText(mPinGen.generatePin());
            mTv4.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String newPin = mPinGen.generatePin();
                    mTv4.setText(newPin);
                    return false;
                }
            });
        }
    }


//
//    public String getPin(){
//        Pin pin = getNewPin();
//        if (pin == null){
//            return generatePin();
//        }
//        Date currentDate = new Date();
//        long timeDiff = currentDate.getTime() - pin.getCreationDate().getTime();
//        if (TimeUnit.MILLISECONDS.toHours(timeDiff) > 24){
//            return generatePin();
//        }
//        else{
//            return pin.getPin();
//        }
//    }
//
//    public void savePin(Pin pin){
//
//        SharedPreferences preferences = mContext.getSharedPreferences("MyData",Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("PinKey", pin.getPin());
//        editor.putLong("DateKey",pin.getCreationDate().getTime());
//        editor.apply();
//
//    }
//
//    public Pin getNewPin(){
//        if(mPreferences.contains("PinKey")) {
//            Date date = new Date(mPreferences.getLong("DateKey", 0));
//            String pin = mPreferences.getString("PinKey","");
//            return new Pin(date, pin);
//        }
//        return null;
//    }


}
