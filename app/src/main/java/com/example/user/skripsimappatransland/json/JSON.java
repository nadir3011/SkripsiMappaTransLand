package com.example.user.skripsimappatransland.json;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.skripsimappatransland.adapter.MaterialAdapter;
import com.example.user.skripsimappatransland.adapter.StokAdapter;
import com.example.user.skripsimappatransland.model.Material;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.activity.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/8/2017.
 */

public class JSON{

    Context context;

    public JSON(){

    }

    public JSON(Context context){
        this.context = context;
    }

    public void jsonLogin(String jsonString) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.getBoolean("error")==false){
            MskTerz.M_nama = jsonObject.getString("nama");
            MskTerz.M_user = jsonObject.getString("username");
            MskTerz.M_apikey = jsonObject.getString("apikey");
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        }else{
            Toast.makeText(context,"Meesage : "+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
        }
    }

    public Boolean jsonSukses(String jsonString) throws JSONException{
        JSONObject jobj = new JSONObject(jsonString);
        if(jobj.getBoolean("error")==false){
            return true;
        }
        return false;
    }

    public String jsonKode(String jsonString) throws JSONException{
        String kodenya="";
        JSONObject jobj = new JSONObject(jsonString);
        if(jobj.getBoolean("error")==false){
            kodenya = jobj.getString("kode");
            return kodenya;
        }
        return "";
    }

    public void jsonMaterial(String jsonString, RecyclerView rv) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.getBoolean("error")==false){
            JSONArray jsonArray = jsonObject.getJSONArray("material");
            RecyclerView.Adapter rvAdapter;
            List<Material> materials = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                Material material = new Material();
                material.setKd_material(jsonArray.getJSONObject(i).getString("kd_material"));
                material.setMaterial(jsonArray.getJSONObject(i).getString("material"));
                material.setSatuan(jsonArray.getJSONObject(i).getString("satuan"));
                material.setHarga(jsonArray.getJSONObject(i).getString("harga"));

                materials.add(material);
            }
            rvAdapter = new MaterialAdapter(context,materials);
            rv.setAdapter(rvAdapter);

        }else{
            Toast.makeText(context,"Meesage : "+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
        }
    }

    public void jsonMaterialStok(String jsonString, RecyclerView rv, FragmentManager fm) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.getBoolean("error")==false){
            JSONArray jsonArray = jsonObject.getJSONArray("hasil");
            RecyclerView.Adapter rvAdapter;
            List<Material_Stok> materials = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                Material_Stok material = new Material_Stok();
                material.setKode(jsonArray.getJSONObject(i).getString("kode"));
                material.setNama(jsonArray.getJSONObject(i).getString("material"));
                material.setSatuan(jsonArray.getJSONObject(i).getString("satuan"));
                material.setHarga(String.valueOf(jsonArray.getJSONObject(i).getDouble("harga")));
                material.setJml_masuk(String.valueOf(jsonArray.getJSONObject(i).getInt("masuk")));
                material.setJml_keluar(String.valueOf(jsonArray.getJSONObject(i).getInt("keluar")));
                material.setJml_stok(String.valueOf(jsonArray.getJSONObject(i).getInt("stok")));
                materials.add(material);
            }
            MskTerz.jumlahdata = jsonArray.length();
            rvAdapter = new StokAdapter(context, fm, materials);
            rv.setAdapter(rvAdapter);
        }else{
            Toast.makeText(context,"Message : "+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
        }
    }
}
