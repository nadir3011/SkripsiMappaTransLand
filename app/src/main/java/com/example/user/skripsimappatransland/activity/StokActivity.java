package com.example.user.skripsimappatransland.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.fragment.StokFragment;
import com.example.user.skripsimappatransland.model.MskTerz;

/**
 * Created by User on 9/10/2017.
 */

public class StokActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        if(savedInstanceState == null){
            MskTerz.datake = 0;
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frame_container,
                            new StokFragment(),
                            StokFragment.class.getSimpleName()).commit();
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
