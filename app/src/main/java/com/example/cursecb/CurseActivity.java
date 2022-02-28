package com.example.cursecb;



import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


public class CurseActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID =
            "com.example.cursecb.money_id";
    public static Intent newIntent(Context packageContext, String moneyId) {
        Intent intent = new Intent(packageContext, CurseActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, moneyId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        String moneyId = (String) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        return MoneyFragment.newInstance(moneyId);
    }
}