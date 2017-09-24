package com.example.user.skripsimappatransland.backup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.skripsimappatransland.R;

/**
 * Created by User on 8/23/2017.
 */

public class MaterialInActivity extends AppCompatActivity {
    EditText edt_supplier, edt_transaction;
    Button btn_lanjut_in;
    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_material_in);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pembelian Material");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        edt_supplier = (EditText) findViewById(R.id.edt_supplier);
        edt_transaction = (EditText) findViewById(R.id.edt_transaction);
        btn_lanjut_in = (Button) findViewById(R.id.btn_lanjut_in);

        btn_lanjut_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String supplier = edt_supplier.getText().toString();
                String transaksi = edt_transaction.getText().toString();
                if(supplier.length()<=4){
                    edt_supplier.setError("Minimal 5 karakter");
                }else if(transaksi.length()==0){
                    edt_transaction.setError("Isi jumlah transaksi");
                }else{
                    Intent i = new Intent(getApplicationContext(), MaterialInLanjutActivity.class);
                    i.putExtra("supplier",supplier);
                    i.putExtra("transaction",transaksi);
                    startActivity(i);
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
