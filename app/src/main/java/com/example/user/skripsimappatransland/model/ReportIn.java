package com.example.user.skripsimappatransland.model;

/**
 * Created by MSI on 10/5/2017.
 */

public class ReportIn {

    String material, tanggal, supplier, jumlah, harga;

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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

    public String getSupplier() {
        return supplier;
    }

    public String getTanggal() {
        return tanggal;
    }
}
