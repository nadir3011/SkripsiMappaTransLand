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
 * Created by User on 8/25/2017.
 */

public class MaterialOutActivity extends AppCompatActivity {

    EditText edt_team,edt_transaction,edt_information;
    Button btn_lanjut_out;
    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_material_out);
        edt_team = (EditText) findViewById(R.id.edt_team);
        edt_transaction = (EditText) findViewById(R.id.edt_transaction);
        edt_information = (EditText) findViewById(R.id.edt_information);
        btn_lanjut_out = (Button) findViewById(R.id.btn_lanjut_out);

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pemakaian Material");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        btn_lanjut_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String team = edt_team.getText().toString();
                String transaksi = edt_transaction.getText().toString();
                String keterangan = edt_information.getText().toString();

                if(team.length()<=4){
                    edt_team.setError("Minimal 5 karakter");
                }else if(transaksi.length()==0){
                    edt_transaction.setError("Harap isi jumlah transaksi");
                }else if(keterangan.length()<=4){
                    edt_information.setError("Minimal 4 karakter");
                }else{
                    Intent i = new Intent(getApplicationContext(), MaterialOutLanjutActivity.class);
                    i.putExtra("team",team);
                    i.putExtra("transaction",transaksi);
                    i.putExtra("information",keterangan);
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
