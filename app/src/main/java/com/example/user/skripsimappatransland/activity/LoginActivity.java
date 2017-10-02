package com.example.user.skripsimappatransland.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.skripsimappatransland.R;

/**
 * Created by MSI on 10/3/2017.
 */

public class LoginActivity extends AppCompatActivity{

    private EditText edt_user, edt_pass;
    private Button btn_login;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_user = (EditText) findViewById(R.id.edt_username);
        edt_pass = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
