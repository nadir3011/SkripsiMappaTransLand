package com.example.user.skripsimappatransland.model;

/**
 * Created by User on 8/8/2017.
 */

public class Material_InOut {

    String kode, jumlah, harga;
    int position;

    public void setPosition(int position) {
        this.position = position;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public int getPosition() {
        return position;
    }

    public String getHarga() {
        return harga;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getKode() {
        return kode;
    }
}

