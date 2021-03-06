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
import com.example.user.skripsimappatransland.activity.MaterialOutFragmentActivity;
import com.example.user.skripsimappatransland.adapter.TransaksiInOutAdapter;
import com.example.user.skripsimappatransland.json.JSON;
import com.example.user.skripsimappatransland.model.Material_InOut;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.volley.RequestSTRING;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 9/6/2017.
 */

public class MaterialOutLanjutFragment extends Fragment {

//    private Button btn_transaction;
    private RecyclerView rv;
    private RecyclerView.LayoutManager rv_lm;
    private RecyclerView.Adapter rvAdapter;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private String team = "", keterangan = "";
    private int jumlah = 0;

    private ArrayList<Material_Stok> material_stok;

    private ArrayList<Material_InOut> material_inOuts;

    public MaterialOutLanjutFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        Bundle bundle=getArguments();
        team = String.valueOf(bundle.getString("team"));
        keterangan = String.valueOf(bundle.getString("information"));
        jumlah = Integer.parseInt(bundle.getString("transaction"));
        material_stok = bundle.getParcelableArrayList("material");

        if(material_inOuts==null){
            material_inOuts = new ArrayList<>();
            for(int a=0; a < material_stok.size(); a++){
                Material_InOut material_inOut = new Material_InOut();
                material_inOut.setPosition(a);
                material_inOut.setKode("");
                material_inOut.setJumlah("0");
                material_inOut.setTotal("");
                material_inOut.setHarga("");
                material_inOut.setMaterial("");
                material_inOut.setStok(0);
                material_inOuts.add(material_inOut);
            }
        }

        setHasOptionsMenu(true);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MaterialOutFragmentActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        actionBar = ((MaterialOutFragmentActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Lanjut Pemakaian Material");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        rv.setHasFixedSize(true);
        rv_lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(rv_lm);
        rvAdapter = new TransaksiInOutAdapter(getActivity(), getFragmentManager(), material_stok, material_inOuts, jumlah, 1, team, keterangan);
        rv.setAdapter(rvAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }
}
