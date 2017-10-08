package com.example.user.skripsimappatransland.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.json.JSON;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.model.ReportIn;
import com.example.user.skripsimappatransland.model.ReportInGroup;
import com.example.user.skripsimappatransland.model.ReportOut;
import com.example.user.skripsimappatransland.model.ReportOutGroup;
import com.example.user.skripsimappatransland.model.ViewPagerAdapter;
import com.example.user.skripsimappatransland.volley.RequestSTRING;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by User on 8/21/2017.
 */

public class ReportFragment extends Fragment{

    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentActivity fragmentActivity;
    ViewPagerAdapter viewPagerAdapter;

    private ArrayList<ReportOutGroup> reportOutGroup;
    private ArrayList<ReportInGroup> reportInGroup;

//    public ReportFragment(ArrayList<ReportIn> reportIn, ArrayList<ReportOut> reportOut){
//        this.reportIn = reportIn;
//        this.reportOut = reportOut;
//    }

    public ReportFragment(ArrayList<ReportInGroup> reportInGroups, ArrayList<ReportOutGroup> reportOutGroups){
        this.reportInGroup = reportInGroups;
        this.reportOutGroup = reportOutGroups;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getReportIn();
    }

    @Override
    public void onAttach(Activity activity) {
        fragmentActivity =(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report,container,false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager){
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
//        viewPagerAdapter.addFragment(new ReportMaterialInFragment(reportIn), "Material In");
        viewPagerAdapter.addFragment(new ReportMaterialInFragment(reportInGroup), "Material In");
        viewPagerAdapter.addFragment(new ReportMaterialOutFragment(reportOutGroup), "Material Out");
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.getAdapter().notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
