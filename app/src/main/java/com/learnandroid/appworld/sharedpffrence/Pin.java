package com.learnandroid.appworld.sharedpffrence;

import java.util.Date;

/**
 * Created by Ash on 8/2/16.
 */
public class Pin {
    /**
     * Time when pin was created
     */
    private Date mCreationDate;
    /**
     * Pin
     */
    private String mPin;

    public String getPin() {
        return mPin;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }

    public Pin(Date creation, String pin){
        mCreationDate = creation;
        mPin = pin;
    }

}
