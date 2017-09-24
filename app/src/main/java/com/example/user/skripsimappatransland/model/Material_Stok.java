package com.example.user.skripsimappatransland.model;

/**
 * Created by User on 8/8/2017.
 */

public class Material_Stok {

    String kode, nama, satuan, harga, jml_masuk, jml_keluar, jml_stok;

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public void setJml_keluar(String jml_keluar) {
        this.jml_keluar = jml_keluar;
    }

    public void setJml_masuk(String jml_masuk) {
        this.jml_masuk = jml_masuk;
    }

    public void setJml_stok(String jml_stok) {
        this.jml_stok = jml_stok;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getHarga() {
        return harga;
    }

    public String getJml_keluar() {
        return jml_keluar;
    }

    public String getJml_masuk() {
        return jml_masuk;
    }

    public String getJml_stok() {
        return jml_stok;
    }

    public String getNama() {
        return nama;
    }
}
