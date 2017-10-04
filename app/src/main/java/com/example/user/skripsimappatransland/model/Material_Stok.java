package com.example.user.skripsimappatransland.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 8/8/2017.
 */

public class Material_Stok implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kode);
        dest.writeString(this.nama);
        dest.writeString(this.satuan);
        dest.writeString(this.harga);
        dest.writeString(this.jml_masuk);
        dest.writeString(this.jml_keluar);
        dest.writeString(this.jml_stok);
    }

    public Material_Stok() {
    }

    protected Material_Stok(Parcel in) {
        this.kode = in.readString();
        this.nama = in.readString();
        this.satuan = in.readString();
        this.harga = in.readString();
        this.jml_masuk = in.readString();
        this.jml_keluar = in.readString();
        this.jml_stok = in.readString();
    }

    public static final Parcelable.Creator<Material_Stok> CREATOR = new Parcelable.Creator<Material_Stok>() {
        @Override
        public Material_Stok createFromParcel(Parcel source) {
            return new Material_Stok(source);
        }

        @Override
        public Material_Stok[] newArray(int size) {
            return new Material_Stok[size];
        }
    };
}
