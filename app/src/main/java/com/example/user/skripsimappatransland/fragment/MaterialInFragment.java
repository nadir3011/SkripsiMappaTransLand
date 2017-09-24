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

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.activity.MaterialInFragmentActivity;

/**
 * Created by User on 9/6/2017.
 */

public class MaterialInFragment extends Fragment {

    private EditText edt_supplier, edt_transaction;
    private Button btn_lanjut_in;
    private Toolbar toolbar;
    private ActionBar actionBar;
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
        setHasOptionsMenu(true);
        return view;
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
        btn_lanjut_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String supplier = edt_supplier.getText().toString();
                String transaksi = edt_transaction.getText().toString();
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
                    fragment.setArguments(bundle);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
