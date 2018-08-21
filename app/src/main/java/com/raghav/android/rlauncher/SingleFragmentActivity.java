package com.raghav.android.rlauncher;

import android.app.WallpaperManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rlauncher);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        //WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        if(fragment == null){
            fragment = createFragment();
            fragment.setEnterTransition(new Fade());
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }

    }

    protected abstract Fragment createFragment();
}
