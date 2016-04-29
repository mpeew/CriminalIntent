package com.mpewpazi.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by mpewpazi on 3/29/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
