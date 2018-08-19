package com.raghav.android.rlauncher;

import android.app.Activity;
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
import android.widget.Adapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
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

        Collections.sort(activities, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo o1, ResolveInfo o2) {
                PackageManager pm = getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(o1.loadLabel(pm).toString(),o2.loadLabel(pm).toString());
            }
        });
        mRecyclerView.setAdapter(new ActivityAdapter(activities));
        Log.i(TAG,"No. of activities : "+activities.size());
        
    }


    private class ActivityHolder extends RecyclerView.ViewHolder{
        private ResolveInfo mResolveInfo;
        private TextView mActivityName;

        public ActivityHolder(View itemView){
            super(itemView);
            mActivityName = (TextView)itemView;
        }

        public void bind(ResolveInfo resolveInfo){
            mResolveInfo = resolveInfo;
            PackageManager pm = getActivity().getPackageManager();
            String appName = mResolveInfo.loadLabel(pm).toString();
            mActivityName.setText(appName);
        }
    }

    private class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder>{
        private List<ResolveInfo> mActivities;

        ActivityAdapter(List<ResolveInfo> list){
            mActivities = list;
        }

        @NonNull
        @Override
        public ActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            return new ActivityHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ActivityHolder holder, int position) {
            ResolveInfo resolveInfo = mActivities.get(position);
            holder.bind(resolveInfo);
        }

        @Override
        public int getItemCount() {
            return mActivities.size();
        }
    }

    public static RLauncherFragment newInstance(){
        return new RLauncherFragment();
    }
}
