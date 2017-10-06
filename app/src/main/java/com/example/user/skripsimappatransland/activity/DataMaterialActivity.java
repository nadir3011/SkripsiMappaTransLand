package com.example.user.skripsimappatransland.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.fragment.DataMaterialFragment;
import com.example.user.skripsimappatransland.fragment.StokFragment;
import com.example.user.skripsimappatransland.model.Material_InOut;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;

import java.util.ArrayList;

/**
 * Created by User on 9/10/2017.
 */

public class DataMaterialActivity extends AppCompatActivity {

    private ArrayList<Material_Stok> material_stok;
    private ArrayList<Material_InOut> material_inOut;
    private int position, material_apa;
    public DataMaterialActivity(){
//        getData(getBaseContext());
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        material_stok = getIntent().getParcelableArrayListExtra("material");
        material_inOut = getIntent().getParcelableArrayListExtra("inout");
        position = getIntent().getIntExtra("position",0);
        material_apa = getIntent().getIntExtra("transaksi",0);
        MskTerz.datake = 0;

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("material", material_stok);
        bundle.putParcelableArrayList("inout", material_inOut);
        bundle.putInt("position", position);
        bundle.putInt("transaksi", material_apa);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        DataMaterialFragment fragment = new DataMaterialFragment(this);
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
        super.onBackPressed();
        finish();
    }
}
