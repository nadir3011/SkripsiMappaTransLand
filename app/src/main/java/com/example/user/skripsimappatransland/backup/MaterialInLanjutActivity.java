package com.example.user.skripsimappatransland.backup;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.adapter.TransaksiInOutAdapter;

/**
 * Created by User on 8/24/2017.
 */

public class MaterialInLanjutActivity extends AppCompatActivity {
    RecyclerView rv;
    RecyclerView.LayoutManager rv_lm;
    RecyclerView.Adapter rvAdapter;
    Toolbar toolbar;
//    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_transaction);

//        context = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lanjut Pembelian Material");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        String supplier = getIntent().getStringExtra("supplier");
        int jumlah = Integer.parseInt(getIntent().getStringExtra("transaction"));

        rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv_lm = new LinearLayoutManager(this);
        rv.setLayoutManager(rv_lm);
//        rvAdapter = new TransaksiInOutAdapter(this,jumlah);
//        rv.setAdapter(rvAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
