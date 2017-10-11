package com.example.user.skripsimappatransland.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.json.JSON;
import com.example.user.skripsimappatransland.model.CekConnection;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.volley.RequestSTRING;

import org.json.JSONException;

/**
 * Created by User on 9/6/2017.
 */

public class AddMaterialActivity extends AppCompatActivity{

    private ActionBar actionBar;
    private Toolbar toolbar;
    private Button btn_add_material;
    private EditText edt_material, edt_unit, edt_price;
    private Context context;
    private CekConnection cc;
    String kode="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Tambah Material");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        edt_material = (EditText) findViewById(R.id.edt_material);
        edt_price = (EditText) findViewById(R.id.edt_price);
        edt_unit = (EditText) findViewById(R.id.edt_unit);

        cc = new CekConnection(this);

        btn_add_material = (Button) findViewById(R.id.btn_add_material);
        context = this;
        btn_add_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String material = edt_material.getText().toString();
                String price = edt_price.getText().toString();
                String unit = edt_unit.getText().toString();
                boolean cekvalue = true;
                if(material.length()<3){
                    edt_material.setError("Minimal 3 Karakter");
                    cekvalue = false;
                }
                if(price.length()==0){
                    edt_price.setError("Isi Harga Material");
                    cekvalue = false;
                }
                if(unit.length() == 0){
                    edt_unit.setError("Isi Satuan Material");
                    cekvalue = false;
                }
                if(cekvalue && cc.isConnected()){
                    RequestSTRING rs = new RequestSTRING(context);
                    rs.setUrlnya(MskTerz.url+"/autonumber"+"/"+MskTerz.M_apikey);
                    rs.setTitle("Tambah Material");
                    rs.setMessage("Proses . . . . !");
                    rs.setTagString("MSKTERZ_KODE");
                    rs.setKeynya(new String[]{"table","kolom","lebar","awalan","user"});
                    rs.setValuenya(new String[]{"mskter_material", "kd_material", "3", "MTR-", MskTerz.M_user});
                    rs.string_status(new RequestSTRING.VolleyCallBack() {
                        @Override
                        public void onSuccess(String result) throws JSONException{

                            JSON json = new JSON(context);
                            kode = json.jsonKode(result);
                        }
                    });

                    if(kode.length() != 0){
                        RequestSTRING rs1 = new RequestSTRING(context);
                        rs1.setUrlnya(MskTerz.url+"/addmaterial/"+MskTerz.M_apikey);
                        rs1.setTitle("Tambah Material");
                        rs1.setMessage("Proses . . . . !");
                        rs1.setTagString("MSKTERZ_ADD");
                        rs1.setKeynya(new String[]{"kd","material","satuan","harga","user"});
                        rs1.setValuenya(new String[]{kode, material, unit, price, MskTerz.M_user});
                        rs1.string_post(new RequestSTRING.VolleyCallBack() {
                            @Override
                            public void onSuccess(String result) throws JSONException {
                                JSON json = new JSON(context);
                                if(json.jsonSukses(result)){
                                    finish();
                                }else{
                                    Toast.makeText(context,"Tidak Berhasil : "+kode,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }else{
                    Toast.makeText(context,"Tidak Terhubung Internet . . . ",Toast.LENGTH_SHORT).show();
                }



            }
        });
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
