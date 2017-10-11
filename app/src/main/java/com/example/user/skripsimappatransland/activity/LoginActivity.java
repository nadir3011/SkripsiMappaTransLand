package com.example.user.skripsimappatransland.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.json.JSON;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.volley.RequestSTRING;

import org.json.JSONException;

/**
 * Created by MSI on 10/3/2017.
 */

public class LoginActivity extends AppCompatActivity{

    private EditText edt_user, edt_pass;
    private Button btn_login;
    private Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_user = (EditText) findViewById(R.id.edt_username);
        edt_pass = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        context = this;
        edt_user.setText("");
        edt_pass.setText("");
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    void Login(){
        String user = edt_user.getText().toString();
        String pass = edt_pass.getText().toString();

        if(user.length()==0){
            edt_user.setError("Harap isi Username");
            return;
        }

        if(pass.length() == 0){
            edt_pass.setError("Harap isi password");
            return;
        }
        RequestSTRING rs = new RequestSTRING(context);
        rs.setUrlnya(MskTerz.url+"/lapanganlogin");
        rs.setTitle("Verifikasi");
        rs.setMessage("Login "+user);
        rs.setTagString("MSKTERZ_LOGIN");
        rs.setKeynya(new String[] {"username", "password"});
        rs.setValuenya(new String[] {user,pass});
        rs.string_post(new RequestSTRING.VolleyCallBack() {
            @Override
            public void onSuccess(String result) throws JSONException {
                JSON json = new JSON(context);
                if(json.jsonLogin(result)){
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
