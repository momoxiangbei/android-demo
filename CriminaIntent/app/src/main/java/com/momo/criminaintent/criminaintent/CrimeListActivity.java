package com.momo.criminaintent.criminaintent;

import android.app.Fragment;

/**
 * Created by mmxb on 2015/4/3.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
