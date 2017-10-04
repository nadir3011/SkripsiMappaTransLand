package com.example.user.skripsimappatransland.model;

/**
 * Created by MSI on 10/5/2017.
 */

public class ReportOut {

    String material, tanggal, tim, jumlah, harga, blok;

    public void setBlok(String blok) {
        this.blok = blok;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }


    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getMaterial() {
        return material;
    }

    public String getHarga() {
        return harga;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getBlok() {
        return blok;
    }

    public String getTim() {
        return tim;
    }
}
