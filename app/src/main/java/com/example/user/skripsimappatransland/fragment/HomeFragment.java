package com.example.user.skripsimappatransland.fragment;

import android.content.Context;
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
import com.example.user.skripsimappatransland.json.JSON;
import com.example.user.skripsimappatransland.model.CekConnection;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.volley.RequestSTRING;

import org.json.JSONException;

import java.util.ArrayList;

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
                getData(getContext());
            }else if(view == img_material_add){
                Intent i = new Intent(getContext(), AddMaterialActivity.class);
                startActivity(i);
            }
        }else{
            Toast.makeText(getContext(),"Tidak Ada Internet . . . ",Toast.LENGTH_SHORT).show();
        }

    }

    void getData(final Context context){
        RequestSTRING rs = new RequestSTRING(context);
        rs.setUrlnya(MskTerz.url+"/stokya/"+MskTerz.M_apikey);
        rs.setTitle("Stok Material");
        rs.setMessage("Proses . . . . !");
        rs.setTagString("MSKTERZ_STOK");
        rs.setKeynya(new String[]{"user"});
        rs.setValuenya(new String[] {MskTerz.M_user});
        rs.string_post(new RequestSTRING.VolleyCallBack() {
            @Override
            public void onSuccess(String result) throws JSONException {
                JSON json = new JSON(context);
                json.jsonMaterialStok(result, new JSON.DataMaterial() {
                    @Override
                    public void onMaterial(ArrayList<Material_Stok> material_stoks) {
                        MskTerz.jumlahdata = material_stoks.size();
                        Intent i = new Intent(getContext(), StokActivity.class);
                        i.putParcelableArrayListExtra("stok",material_stoks);
                        startActivity(i);
                    }
                });
            }
        });
    }
}
