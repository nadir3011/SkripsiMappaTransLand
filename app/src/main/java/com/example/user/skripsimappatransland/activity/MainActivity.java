package com.example.user.skripsimappatransland.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.fragment.AkunFragment;
import com.example.user.skripsimappatransland.fragment.HomeFragment;
import com.example.user.skripsimappatransland.fragment.ReportFragment;
import com.example.user.skripsimappatransland.model.BottomNavigationViewHelper;
import com.example.user.skripsimappatransland.model.CekConnection;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;
    private Fragment fragment = null;
    private FragmentManager fragmentManager;
    private CekConnection cc;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PT. MappaTransLand");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_material_add);
        cc = new CekConnection(this);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navview);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainContainer,new HomeFragment()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(cc.isConnected()){
            Toast.makeText(this,"Terhubung Internet . . . ",Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(this,"Tidak Ada Internet . . . ",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.bottom_home:
                fragment = new HomeFragment();
                break;
            case R.id.bottom_report:
                fragment = new ReportFragment();
                break;
            case R.id.bottom_akun:
                fragment = new AkunFragment();
                break;
        }

        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer,fragment).commit();
        return true;
    }
}
