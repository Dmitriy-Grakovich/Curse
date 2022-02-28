package com.example.cursecb;

import android.support.v4.app.Fragment;

public class MoneyListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new MoneyListFragment();
    }
}
