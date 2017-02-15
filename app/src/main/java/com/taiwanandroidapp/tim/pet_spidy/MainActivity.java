package com.taiwanandroidapp.tim.pet_spidy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, DisplayFragment.newInstance())
                .commit();
    }
}
