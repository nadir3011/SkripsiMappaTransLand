package com.example.user.skripsimappatransland.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.fragment.MaterialOutFragment;

/**
 * Created by User on 9/6/2017.
 */

public class MaterialOutFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frame_container, new MaterialOutFragment(), MaterialOutFragment.class.getSimpleName()).
                    commit();
        }
    }
}
