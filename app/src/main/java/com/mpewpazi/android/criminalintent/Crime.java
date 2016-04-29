package com.mpewpazi.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by mpewpazi on 3/29/16.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;

    public Crime(){
        //generate random id pada setiap instance yang dibuat
        this(UUID.randomUUID());

        //inisiasi date bakal set date ke tanggal sekarang
       // mDate=new Date();
    }

    public Crime(UUID id){
        mId=id;
        mDate=new Date();
    }


    public void setDate(Date date) {
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }


    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }
}
