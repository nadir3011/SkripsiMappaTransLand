package com.example.user.skripsimappatransland.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.adapter.StokAdapter;
import com.example.user.skripsimappatransland.fragment.StokFragment;
import com.example.user.skripsimappatransland.json.JSON;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.volley.RequestSTRING;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by User on 9/10/2017.
 */

public class StokActivity extends AppCompatActivity {

    private ArrayList<Material_Stok> material_stok;

    public StokActivity(){
//        getData(getBaseContext());
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        material_stok = getIntent().getParcelableArrayListExtra("stok");
        MskTerz.datake = 0;

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("stok", material_stok);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        StokFragment fragment = new StokFragment(this);
        fragment.setArguments(bundle);

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.add(R.id.frame_container, fragment);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }



    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
