package com.example.user.skripsimappatransland.activity;

import android.content.Context;
import android.content.Intent;
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
import com.example.user.skripsimappatransland.json.JSON;
import com.example.user.skripsimappatransland.model.BottomNavigationViewHelper;
import com.example.user.skripsimappatransland.model.CekConnection;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.model.ReportIn;
import com.example.user.skripsimappatransland.model.ReportOut;
import com.example.user.skripsimappatransland.volley.RequestSTRING;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;
    private Fragment fragment = null;
    private FragmentManager fragmentManager;
    private CekConnection cc;
    private Toolbar toolbar;
    private Context context;

    private ArrayList<ReportIn> reportIn;
    private ArrayList<ReportOut> reportOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        Intent login = new Intent(this,LoginActivity.class);
        startActivity(login);

        context = this;
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
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainContainer,fragment).commit();
                break;
            case R.id.bottom_report:
                getReportIn();
                getReportOut();
//                fragment = new ReportFragment(reportIn);
                break;
            case R.id.bottom_akun:
                fragment = new AkunFragment();
                final FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.mainContainer,fragment).commit();
                break;
        }

//        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.mainContainer,fragment).commit();
        return true;
    }

    void getReportIn(){
        RequestSTRING rs = new RequestSTRING(context);
        rs.setUrlnya(MskTerz.url+"/cekmaterialmasukuser/"+MskTerz.M_apikey);
        rs.setTitle("Report Material Ku");
        rs.setMessage("Proses . . . . !");
        rs.setTagString("MSKTERZ_REPORT");
        rs.setKeynya(new String[]{"user","status"});
        rs.setValuenya(new String[] {MskTerz.M_user, "Y"});
        rs.string_post(new RequestSTRING.VolleyCallBack() {
            @Override
            public void onSuccess(String result) throws JSONException {
                JSON json = new JSON(context);
                json.jsonReportIn(result, new JSON.DataReportIn() {
                    @Override
                    public void onReport(ArrayList<ReportIn> reportIns) {
                        reportIn = reportIns;
//                        fragment = new ReportFragment(reportIn,reportOut);
//                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.mainContainer,fragment).commit();
                    }
                });
            }
        });
    }

    void getReportOut(){
        RequestSTRING rs = new RequestSTRING(context);
        rs.setUrlnya(MskTerz.url+"/cekmaterialkeluaruser/"+MskTerz.M_apikey);
        rs.setTitle("Report Material Ku");
        rs.setMessage("Proses . . . . !");
        rs.setTagString("MSKTERZ_REPORT");
        rs.setKeynya(new String[]{"user","status"});
        rs.setValuenya(new String[] {MskTerz.M_user, "Y"});
        rs.string_post(new RequestSTRING.VolleyCallBack() {
            @Override
            public void onSuccess(String result) throws JSONException {
                JSON json = new JSON(context);
                json.jsonReportOut(result, new JSON.DataReportOut() {
                    @Override
                    public void onReport(ArrayList<ReportOut> reportOuts) {
                        reportOut = reportOuts;
                        fragment = new ReportFragment(reportIn,reportOut);
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.mainContainer,fragment).commit();
                    }
                });
            }
        });
    }
}
