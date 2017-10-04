package com.example.user.skripsimappatransland.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.model.MskTerz;

/**
 * Created by User on 8/21/2017.
 */

public class AkunFragment extends Fragment {

    TextView txtNama, txtUser, txtAlamat, txtKontak, txtTmpTgl;

    public AkunFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_akun, container, false);
        txtNama = (TextView) view.findViewById(R.id.akunNama);
        txtAlamat = (TextView) view.findViewById(R.id.akunAlamat);
        txtKontak = (TextView) view.findViewById(R.id.akunKontak);
        txtUser = (TextView) view.findViewById(R.id.akunUser);
        txtTmpTgl = (TextView) view.findViewById(R.id.akunTmpTgl);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txtNama.setText("Nama : "+MskTerz.M_nama);
        txtAlamat.setText("Alamat : "+MskTerz.M_alamat);
        txtTmpTgl.setText("Tempat/Tanggal Lahir : "+MskTerz.M_tmp_lahir+"/"+MskTerz.M_tgl_lahir);
        txtUser.setText("Username : "+MskTerz.M_user);
        txtKontak.setText("Handphone : "+MskTerz.M_kontak);
    }
}
