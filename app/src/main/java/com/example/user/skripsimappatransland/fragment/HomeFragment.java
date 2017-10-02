package com.example.user.skripsimappatransland.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.activity.AddMaterialActivity;
import com.example.user.skripsimappatransland.activity.MaterialInFragmentActivity;
import com.example.user.skripsimappatransland.activity.MaterialOutFragmentActivity;
import com.example.user.skripsimappatransland.activity.StokActivity;
import com.example.user.skripsimappatransland.model.CekConnection;

/**
 * Created by User on 8/21/2017.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{

    ImageView img_material_beli,img_material_pakai,img_material_stok,img_material_add;
    private CekConnection cc;

    public HomeFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        cc = new CekConnection(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        img_material_beli = (ImageView) view.findViewById(R.id.img_material_beli);
        img_material_pakai = (ImageView) view.findViewById(R.id.img_material_pakai);
        img_material_stok = (ImageView) view.findViewById(R.id.img_material_stok);
        img_material_add = (ImageView) view.findViewById(R.id.img_material_add);

        img_material_beli.setOnClickListener(this);
        img_material_pakai.setOnClickListener(this);
        img_material_stok.setOnClickListener(this);
        img_material_add.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(cc.isConnected()){
            if(view == img_material_beli){
                Intent i = new Intent(getContext(), MaterialInFragmentActivity.class);
                startActivity(i);
            }else if(view == img_material_pakai){
                Intent i = new Intent(getContext(), MaterialOutFragmentActivity.class);
                startActivity(i);
            }else if(view == img_material_stok){
                Intent i = new Intent(getContext(), StokActivity.class);
                startActivity(i);
            }else if(view == img_material_add){
                Intent i = new Intent(getContext(), AddMaterialActivity.class);
                startActivity(i);
            }
        }else{
            Toast.makeText(getContext(),"Tidak Ada Internet . . . ",Toast.LENGTH_SHORT).show();
        }

    }
}
