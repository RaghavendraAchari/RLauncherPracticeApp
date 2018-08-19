package com.raghav.android.rlauncher;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RLauncherActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return RLauncherFragment.newInstance();
    }
}
