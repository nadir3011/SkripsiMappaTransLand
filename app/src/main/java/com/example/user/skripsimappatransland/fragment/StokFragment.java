package com.example.user.skripsimappatransland.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.activity.StokActivity;
import com.example.user.skripsimappatransland.adapter.StokAdapter;
import com.example.user.skripsimappatransland.model.MskTerz;


/**
 * Created by User on 9/6/2017.
 */

public class StokFragment extends Fragment {

    private RecyclerView rv;
    private RecyclerView.LayoutManager rv_lm;
    private RecyclerView.Adapter rvAdapter;
    private Toolbar toolbar;
    private ActionBar actionBar;

    public StokFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container,false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        setHasOptionsMenu(true);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((StokActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        actionBar = ((StokActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Stok Material");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        rv.setHasFixedSize(true);
        rv_lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(rv_lm);
        if(MskTerz.datake == 0){
            rvAdapter = new StokAdapter(getActivity(), getFragmentManager());
            rv.setAdapter(rvAdapter);
        }else{
            rvAdapter = new StokAdapter(getActivity(),getFragmentManager());
            rv.setAdapter(rvAdapter);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getActivity().finish();
        }

//        if(MskTerz.datake == 0){
//            if(item.getItemId() == android.R.id.home){
//                getActivity().finish();
//            }
//        }else{
//            if(item.getItemId() == android.R.id.home){
//                MskTerz.datake -=1;
//                getFragmentManager().popBackStack();
//            }
//        }
        return super.onOptionsItemSelected(item);
    }
}
