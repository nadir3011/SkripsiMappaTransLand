package com.example.user.skripsimappatransland.model;

import java.util.ArrayList;

/**
 * Created by MSI on 10/5/2017.
 */

public class ReportOutGroup {

    String tanggal, tim, keterangan;

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }

    public String getTim() {
        return tim;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    ArrayList<String> material = new ArrayList<String>();
    ArrayList<String> satuan = new ArrayList<String>();
    ArrayList<String> jumlah = new ArrayList<String>();
    ArrayList<String> harga = new ArrayList<String>();
    ArrayList<String> total = new ArrayList<String>();



    public void setTotal(ArrayList<String> total) {
        this.total = total;
    }

    public ArrayList<String> getTotal() {
        return total;
    }

    public void setHarga(ArrayList<String> harga) {
        this.harga = harga;
    }

    public ArrayList<String> getHarga() {
        return harga;
    }

    public void setJumlah(ArrayList<String> jumlah) {
        this.jumlah = jumlah;
    }

    public ArrayList<String> getJumlah() {
        return jumlah;
    }

    public void setMaterial(ArrayList<String> material) {
        this.material = material;
    }

    public ArrayList<String> getMaterial() {
        return material;
    }

    public void setSatuan(ArrayList<String> satuan) {
        this.satuan = satuan;
    }

    public ArrayList<String> getSatuan() {
        return satuan;
    }

    //    String[] material, satuan, jumlah, harga, total;

    //
//    public void setMaterial(String[] material) {
//        this.material = material;
//    }
//
//    public String[] getMaterial() {
//        return material;
//    }
//
//    public void setSatuan(String[] satuan) {
//        this.satuan = satuan;
//    }
//
//    public String[] getSatuan() {
//        return satuan;
//    }
//
//    public void setJumlah(String[] jumlah) {
//        this.jumlah = jumlah;
//    }
//
//    public String[] getJumlah() {
//        return jumlah;
//    }
//
//    public void setHarga(String[] harga) {
//        this.harga = harga;
//    }
//
//    public String[] getHarga() {
//        return harga;
//    }
//
//    public void setTotal(String[] total) {
//        this.total = total;
//    }
//
//    public String[] getTotal() {
//        return total;
//    }
}
