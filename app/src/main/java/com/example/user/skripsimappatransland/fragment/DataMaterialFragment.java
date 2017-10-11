package com.example.user.skripsimappatransland.fragment;

import android.content.Context;
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
import android.widget.Toast;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.activity.DataMaterialActivity;
import com.example.user.skripsimappatransland.activity.MaterialInFragmentActivity;
import com.example.user.skripsimappatransland.activity.MaterialOutFragmentActivity;
import com.example.user.skripsimappatransland.adapter.MaterialAdapter;
import com.example.user.skripsimappatransland.adapter.TransaksiInOutAdapter;
import com.example.user.skripsimappatransland.model.Material_InOut;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 9/6/2017.
 */

public class DataMaterialFragment extends Fragment {

    private RecyclerView rv;
    private RecyclerView.LayoutManager rv_lm;
    private RecyclerView.Adapter rvAdapter;
    private Toolbar toolbar;
    private ActionBar actionBar;
//    private String supplier = "";
//    private int jumlah = 0;
    private int urutan, material_apa;

    private ArrayList<Material_Stok> material_stoks;

    private ArrayList<Material_InOut> material_inOuts;
    private Context context;

    public DataMaterialFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container,false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        rv = (RecyclerView) view.findViewById(R.id.recyclerView);

        try{
            material_stoks = getArguments().getParcelableArrayList("material");
            material_inOuts = getArguments().getParcelableArrayList("inout");
            urutan = getArguments().getInt("position");
            material_apa = getArguments().getInt("transaksi");
//            Material_Stok material_stok = material_stoks.get(0);
//            Toast.makeText(getActivity(), "Data ke "+urutan,Toast.LENGTH_LONG).show();
            MskTerz.jumlahdatamaterial = material_stoks.size();
        }catch (Exception e){
            Toast.makeText(getActivity(), "Error Material: "+e.toString(),Toast.LENGTH_LONG).show();
        }

        setHasOptionsMenu(true);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if(material_apa == 0){
            ((MaterialInFragmentActivity)getActivity()).setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            actionBar = ((MaterialInFragmentActivity)getActivity()).getSupportActionBar();
        }else if(material_apa == 1){
            ((MaterialOutFragmentActivity)getActivity()).setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            actionBar = ((MaterialOutFragmentActivity)getActivity()).getSupportActionBar();
        }else{
            ((DataMaterialActivity)getActivity()).setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            actionBar = ((DataMaterialActivity)getActivity()).getSupportActionBar();
        }


        actionBar.setTitle("Data Material");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);


        rv.setHasFixedSize(true);

        rv_lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(rv_lm);

        rvAdapter = new MaterialAdapter(getActivity(), material_stoks, material_inOuts, getFragmentManager(), urutan, material_apa);
        rv.setAdapter(rvAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getActivity().finish();
//            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }
}
