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
import com.example.user.skripsimappatransland.activity.MaterialOutFragmentActivity;

/**
 * Created by User on 9/6/2017.
 */

public class MaterialOutFragment extends Fragment {
    private EditText edt_team,edt_transaction,edt_information;
    private Button btn_lanjut_out;
    private Toolbar toolbar;
    private ActionBar actionBar;
    public MaterialOutFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_material_out, container, false);
        edt_team = (EditText) view.findViewById(R.id.edt_team);
        edt_transaction = (EditText) view.findViewById(R.id.edt_transaction);
        edt_information = (EditText) view.findViewById(R.id.edt_information);
        btn_lanjut_out = (Button) view.findViewById(R.id.btn_lanjut_out);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MaterialOutFragmentActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionBar = ((MaterialOutFragmentActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Pemakaian Material");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        btn_lanjut_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String team = edt_team.getText().toString();
                String transaksi = edt_transaction.getText().toString();
                String keterangan = edt_information.getText().toString();
                boolean cekvalue = true;
                if(team.length()<=4){
                    edt_team.setError("Minimal 5 karakter");
                    cekvalue = false;
                }
                if(transaksi.length()==0){
                    edt_transaction.setError("Harap isi jumlah transaksi");
                    cekvalue = false;
                }
                if(keterangan.length()<=4){
                    edt_information.setError("Minimal 4 karakter");
                    cekvalue = false;
                }
                if(cekvalue){
                    MaterialOutLanjutFragment fragment = new MaterialOutLanjutFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putString("team", team);
                    bundle.putString("transaction", transaksi);
                    bundle.putString("information", keterangan);
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
        if(item.getItemId() == android.R.id.home){
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
