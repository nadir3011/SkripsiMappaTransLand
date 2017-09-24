package com.example.user.skripsimappatransland.model;


public class Material {

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
}
