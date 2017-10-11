package com.example.user.skripsimappatransland.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 8/8/2017.
 */

public class Material_InOut implements Parcelable {

    String kode, jumlah, harga, material, total;
    int position, stok;

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getStok() {
        return stok;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
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

    public String getMaterial() {
        return material;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kode);
        dest.writeString(this.jumlah);
        dest.writeString(this.harga);
        dest.writeString(this.material);
        dest.writeString(this.total);
        dest.writeInt(this.position);
        dest.writeInt(this.stok);
    }

    public Material_InOut() {
    }

    protected Material_InOut(Parcel in) {
        this.kode = in.readString();
        this.jumlah = in.readString();
        this.harga = in.readString();
        this.material = in.readString();
        this.total = in.readString();
        this.position = in.readInt();
        this.stok = in.readInt();
    }

    public static final Parcelable.Creator<Material_InOut> CREATOR = new Parcelable.Creator<Material_InOut>() {
        @Override
        public Material_InOut createFromParcel(Parcel source) {
            return new Material_InOut(source);
        }

        @Override
        public Material_InOut[] newArray(int size) {
            return new Material_InOut[size];
        }
    };
}

