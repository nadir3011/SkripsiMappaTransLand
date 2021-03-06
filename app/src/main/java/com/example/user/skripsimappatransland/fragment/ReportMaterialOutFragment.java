package com.example.user.skripsimappatransland.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.adapter.ReportInAdapter;
import com.example.user.skripsimappatransland.adapter.ReportOutAdapter;
import com.example.user.skripsimappatransland.model.ReportIn;
import com.example.user.skripsimappatransland.model.ReportOut;
import com.example.user.skripsimappatransland.model.ReportOutGroup;

import java.util.ArrayList;

/**
 * Created by User on 8/23/2017.
 */

public class ReportMaterialOutFragment extends Fragment{

    private FragmentActivity fragmentActivity;
    private RecyclerView rv;
    private RecyclerView.LayoutManager rv_lm;
    private RecyclerView.Adapter rvAdapter;
    private ArrayList<ReportOutGroup> reportOutGroups;

    public ReportMaterialOutFragment(ArrayList<ReportOutGroup> reportOutGroups){
        this.reportOutGroups = reportOutGroups;
    }

    @Override
    public void onAttach(Activity activity) {
        fragmentActivity =(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_material_in, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv.setHasFixedSize(true);
        rv_lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(rv_lm);

        rvAdapter = new ReportOutAdapter(getActivity(), reportOutGroups, getFragmentManager());
        rv.setAdapter(rvAdapter);
    }
}
