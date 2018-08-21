package com.raghav.android.rlauncher;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class PagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> mFragments;


    private class Adapter extends FragmentStatePagerAdapter{
        List<Fragment> mFragments;

        public void setFragmentsList(List<Fragment> list){
            mFragments = list;
        }

        public Adapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        if(mFragments == null){
            mFragments.add(PatternFragment.newInstance());
            mFragments.add(PlaneFragment.newInstance());
            mFragments.add(RLauncherFragment.newInstance());
        }



        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Adapter adapter = new Adapter(fragmentManager);
        adapter.setFragmentsList(mFragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(1);

    }
}
