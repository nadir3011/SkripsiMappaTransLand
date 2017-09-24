package com.example.user.skripsimappatransland.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Material implements Parcelable {

    String kd_material, material, satuan, harga;

    public void setKd_material(String kd_material) {
        this.kd_material = kd_material;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getHarga() {
        return harga;
    }

    public String getKd_material() {
        return kd_material;
    }

    public String getMaterial() {
        return material;
    }

    public String getSatuan() {
        return satuan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kd_material);
        dest.writeString(this.material);
        dest.writeString(this.satuan);
        dest.writeString(this.harga);
    }

    public Material() {
    }

    protected Material(Parcel in) {
        this.kd_material = in.readString();
        this.material = in.readString();
        this.satuan = in.readString();
        this.harga = in.readString();
    }

    public static final Parcelable.Creator<Material> CREATOR = new Parcelable.Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel source) {
            return new Material(source);
        }

        @Override
        public Material[] newArray(int size) {
            return new Material[size];
        }
    };
}
