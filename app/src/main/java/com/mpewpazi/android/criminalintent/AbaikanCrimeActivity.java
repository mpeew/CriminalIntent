package com.mpewpazi.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;


// A class that host fragment should extends fragment activity
public class AbaikanCrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID="com.mpewpazi.android.criminalintent.crimeid";

    public static Intent newIntent(Context context,UUID crimeid){
        Intent intent=new Intent(context,AbaikanCrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeid);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        UUID crimeId=(UUID)getIntent().getSerializableExtra(AbaikanCrimeActivity.EXTRA_CRIME_ID);


        //return disini ya fragment yang akan di tampilkan di layar activity/fragment container
        return new CrimeFragment().newInstance(crimeId);
    }
}


