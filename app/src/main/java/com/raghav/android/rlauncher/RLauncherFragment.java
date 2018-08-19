package com.raghav.android.rlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RLauncherFragment extends Fragment {
    private RecyclerView mRecyclerView;

    private static final String TAG = "RLauncherFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rlauncher,container,false);

        mRecyclerView = v.findViewById(R.id.fragment_container);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

    private void setAdapter(){
        //get the activities which defines CATEGORY_LAUNCHER
        Intent startUpIntent = new Intent(Intent.ACTION_MAIN);
        startUpIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        //get PackageManager to get list of activities
        PackageManager packageManager = getActivity().getPackageManager();
        //query the activities by packageManager
        //type = ResolveInfo
        List<ResolveInfo> activities = packageManager.queryIntentActivities(startUpIntent,0);

        Log.i(TAG,"No. of activities : "+activities.size());
        
    }

    public static RLauncherFragment newInstance(){
        return new RLauncherFragment();
    }
}
