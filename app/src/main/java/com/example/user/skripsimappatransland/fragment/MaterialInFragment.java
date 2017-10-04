package com.example.user.skripsimappatransland.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.activity.MaterialInFragmentActivity;
import com.example.user.skripsimappatransland.json.JSON;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.volley.RequestSTRING;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by User on 9/6/2017.
 */

public class MaterialInFragment extends Fragment implements View.OnClickListener{

    private EditText edt_supplier, edt_transaction;
    private Button btn_lanjut_in;
    private ImageView img_kurang, img_tambah;
    private Toolbar toolbar;
    private ActionBar actionBar;

    private ArrayList<Material_Stok> material_stok;
    public MaterialInFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_material_in, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        edt_supplier = (EditText) view.findViewById(R.id.edt_supplier);
        edt_transaction = (EditText) view.findViewById(R.id.edt_transaction);
        btn_lanjut_in = (Button) view.findViewById(R.id.btn_lanjut_in);
        img_kurang = (ImageView) view.findViewById(R.id.img_kurang);
        img_tambah = (ImageView) view.findViewById(R.id.img_tambah);
        edt_transaction.setEnabled(false);
        getDataMaterial();
        setHasOptionsMenu(true);
        return view;
    }

    void getDataMaterial(){
        RequestSTRING rs = new RequestSTRING(getActivity());
        rs.setUrlnya(MskTerz.url+"/stokya/"+MskTerz.M_apikey);
        rs.setTitle("Data Material");
        rs.setMessage("Proses . . . . !");
        rs.setTagString("MSKTERZ_STOK");
        rs.setKeynya(new String[]{"user"});
        rs.setValuenya(new String[] {MskTerz.M_user});
        rs.string_post(new RequestSTRING.VolleyCallBack() {
            @Override
            public void onSuccess(String result) throws JSONException {
                JSON json = new JSON(getActivity());
                json.jsonMaterialStok(result, new JSON.DataMaterial() {
                    @Override
                    public void onMaterial(ArrayList<Material_Stok> material_stoks) {
                        material_stok = material_stoks;
                        Toast.makeText(getActivity(), "Jumlah Material : "+material_stoks.size(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MaterialInFragmentActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        actionBar = ((MaterialInFragmentActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Pembelian Material");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        edt_supplier.setText("DececenyaMo");
        edt_transaction.setText("1");
        btn_lanjut_in.setOnClickListener(this);
        img_tambah.setOnClickListener(this);
        img_kurang.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        String supplier = edt_supplier.getText().toString();
        String transaksi = edt_transaction.getText().toString();
        int nilai = Integer.parseInt(transaksi);
        if(view == btn_lanjut_in){
            boolean cekvalue = true;
            if(supplier.length()<=4){
                edt_supplier.setError("Minimal 5 karakter");
                cekvalue = false;
            }
            if(transaksi.length()==0){
                edt_transaction.setError("Isi jumlah transaksi");
                cekvalue = false;
            }
            if(cekvalue){
                MaterialInLanjutFragment fragment = new MaterialInLanjutFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                bundle.putString("supplier",supplier);
                bundle.putString("transaction",transaksi);
                bundle.putParcelableArrayList("material", material_stok);
                fragment.setArguments(bundle);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.replace(R.id.frame_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }else if(view == img_tambah){
            if(material_stok.size() > nilai){
                nilai += 1;
                edt_transaction.setText(String.valueOf(nilai));
            }
        }else if(view == img_kurang){
            if(nilai>1){
                nilai -= 1;
                edt_transaction.setText(String.valueOf(nilai));
            }
        }
    }
}
