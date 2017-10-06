package com.example.user.skripsimappatransland.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.activity.StokActivity;
import com.example.user.skripsimappatransland.adapter.StokAdapter;
import com.example.user.skripsimappatransland.json.JSON;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.volley.RequestSTRING;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 9/6/2017.
 */

public class StokFragment extends Fragment {

    private RecyclerView rv;
    private RecyclerView.LayoutManager rv_lm;
    private RecyclerView.Adapter rvAdapter;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private Context context;
    private FragmentManager fm;
    private ArrayList<Material_Stok> material_stok;

    public StokFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container,false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        material_stok = getArguments().getParcelableArrayList("stok");
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
        fm = getFragmentManager();

        rvAdapter = new StokAdapter(context,fm, material_stok);
        rv.setAdapter(rvAdapter);
//        if(MskTerz.cekstok){
////            Toast.makeText(context,"Data Ke : "+MskTerz.datake,Toast.LENGTH_LONG).show();
//            rvAdapter = new StokAdapter(context,fm, material_stok);
//            rv.setAdapter(rvAdapter);
//        }
//        else{
////            Toast.makeText(context,"Data Ke : "+MskTerz.datake,Toast.LENGTH_LONG).show();
//            rvAdapter = new StokAdapter(context,fm, material_stok);
//            rv.setAdapter(rvAdapter);
//        }
//        MskTerz.cekstok = false;
//        if(MskTerz.datake == 0){
//            rvAdapter = new StokAdapter(getActivity(), getFragmentManager());
//            rv.setAdapter(rvAdapter);
//        }else{
//            rvAdapter = new StokAdapter(getActivity(),getFragmentManager());
//            rv.setAdapter(rvAdapter);
//        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
