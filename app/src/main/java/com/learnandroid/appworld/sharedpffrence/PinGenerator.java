package com.learnandroid.appworld.sharedpffrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

import java.util.Random;

/**
 * Created by Ash on 8/2/16.
 */
public class PinGenerator {

    Context mContext;
    private static final char INITIAL_CHAR = 'A';
    private static final int DIGIT_RANGE = 10;
    private static final int RANDOM_RANGE = 36;
    private static final int PIN_LENGTH = 6;

    public String generatePin() {
        Random r = new Random();
        String pinCode = "";
        int offset = INITIAL_CHAR - DIGIT_RANGE;
        while (pinCode.length() < PIN_LENGTH) {
            int value = r.nextInt(RANDOM_RANGE);
            //O and 0 will not be used to avoid confusion
            if (value < DIGIT_RANGE && value > 0) {
                pinCode += value;
            } else {

                value += offset;
                char letter = (char) value;
                //O and 0 will not be used to avoid confusion
                if (letter != 'O') {
                    pinCode += (char) value;
                }
            }
        }
//        savePin();
        return pinCode;
    }




}
