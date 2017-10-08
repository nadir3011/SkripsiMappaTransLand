package com.example.user.skripsimappatransland.json;

import android.content.Context;
import android.widget.Toast;

import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.model.ReportIn;
import com.example.user.skripsimappatransland.model.ReportInGroup;
import com.example.user.skripsimappatransland.model.ReportOut;
import com.example.user.skripsimappatransland.model.ReportOutGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 8/8/2017.
 */

public class JSON{

    Context context;

    public JSON(){

    }

    public JSON(Context context){
        this.context = context;
    }

    public Boolean jsonLogin(String jsonString) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.getBoolean("error")==false){
            MskTerz.M_nama = jsonObject.getString("nama");
            MskTerz.M_user = jsonObject.getString("username");
            MskTerz.M_apikey = jsonObject.getString("apikey");
            MskTerz.M_alamat = jsonObject.getString("alamat");
            MskTerz.M_tmp_lahir = jsonObject.getString("tempat_lahir");
            MskTerz.M_tgl_lahir = jsonObject.getString("tanggal_lahir");
            MskTerz.M_kontak = jsonObject.getString("kontak");
            return true;
        }else{
            Toast.makeText(context, "Pastikan username and password benar",Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public Boolean jsonSukses(String jsonString) throws JSONException{
        JSONObject jobj = new JSONObject(jsonString);
        if(jobj.getBoolean("error")==false){
            return true;
        }
        return false;
    }

    public String jsonKode(String jsonString) throws JSONException{
        String kodenya="";
        JSONObject jobj = new JSONObject(jsonString);
        if(jobj.getBoolean("error")==false){
            kodenya = jobj.getString("kode");
            return kodenya;
        }
        return "";
    }

    public void jsonMaterialStok(String jsonString, DataMaterial dataMaterial) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.getBoolean("error")==false){
            JSONArray jsonArray = jsonObject.getJSONArray("hasil");
            ArrayList<Material_Stok> materials = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                Material_Stok material = new Material_Stok();
                material.setKode(jsonArray.getJSONObject(i).getString("kode"));
                material.setNama(jsonArray.getJSONObject(i).getString("material"));
                material.setSatuan(jsonArray.getJSONObject(i).getString("satuan"));
                material.setHarga(String.valueOf(jsonArray.getJSONObject(i).getDouble("harga")));
                material.setJml_masuk(String.valueOf(jsonArray.getJSONObject(i).getInt("masuk")));
                material.setJml_keluar(String.valueOf(jsonArray.getJSONObject(i).getInt("keluar")));
                material.setJml_stok(String.valueOf(jsonArray.getJSONObject(i).getInt("stok")));
                materials.add(material);
            }
            dataMaterial.onMaterial(materials);
        }else{
            Toast.makeText(context,"Message : "+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
        }
    }

    public void jsonReportIn(String jsonString, DataReportIn dataReportIn) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.getBoolean("error")==false){
            JSONArray jsonArray = jsonObject.getJSONArray("hasil");
            ArrayList<ReportIn> reportIns = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                ReportIn reportIn = new ReportIn();
                reportIn.setMaterial(jsonArray.getJSONObject(i).getString("material"));
                reportIn.setSupplier(jsonArray.getJSONObject(i).getString("supplier"));
                reportIn.setTanggal(jsonArray.getJSONObject(i).getString("tanggal"));
                reportIn.setJumlah(jsonArray.getJSONObject(i).getString("jumlah"));
                reportIn.setHarga(jsonArray.getJSONObject(i).getString("harga"));
                reportIns.add(reportIn);
            }
            dataReportIn.onReport(reportIns);
        }else{
            Toast.makeText(context,"Message : "+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
        }
    }

    public void jsonReportInGroup(String jsonString, DataReportInGroup dataReportInGroup) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.getBoolean("error")==false){
            JSONArray jsonArray = jsonObject.getJSONArray("hasil");
            ArrayList<ReportInGroup> reportInGroups = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                ReportInGroup reportIn = new ReportInGroup();
                reportIn.setSupplier(jsonArray.getJSONObject(i).getString("supplier"));
                reportIn.setTanggal(jsonArray.getJSONObject(i).getString("tanggal"));
                JSONArray masuk = jsonArray.getJSONObject(i).getJSONArray("masuk");
                ArrayList<String> material = new ArrayList<>();
                ArrayList<String> satuan = new ArrayList<String>();
                ArrayList<String> jumlah = new ArrayList<String>();
                ArrayList<String> harga = new ArrayList<String>();
                ArrayList<String> total = new ArrayList<String>();
                for(int b = 0; b < masuk.length(); b++){
                    material.add(masuk.getJSONObject(b).getString("material"));
                    satuan.add(masuk.getJSONObject(b).getString("satuan"));
                    jumlah.add(masuk.getJSONObject(b).getString("jumlah"));
                    harga.add(masuk.getJSONObject(b).getString("harga"));
                    total.add(masuk.getJSONObject(b).getString("total"));

                    reportIn.setMaterial(material);
                    reportIn.setSatuan(satuan);
                    reportIn.setJumlah(jumlah);
                    reportIn.setHarga(harga);
                    reportIn.setTotal(total);
                }
                reportInGroups.add(reportIn);
            }
            dataReportInGroup.onReport(reportInGroups);
        }else{
            Toast.makeText(context,"Message : "+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
        }
    }

    public void jsonReportOut(String jsonString, DataReportOut dataReportOut) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.getBoolean("error")==false){
            JSONArray jsonArray = jsonObject.getJSONArray("hasil");
            ArrayList<ReportOut> reportOuts = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                ReportOut reportOut = new ReportOut();
                reportOut.setMaterial(jsonArray.getJSONObject(i).getString("material"));
                reportOut.setTim(jsonArray.getJSONObject(i).getString("tim"));
                reportOut.setTanggal(jsonArray.getJSONObject(i).getString("tanggal"));
                reportOut.setJumlah(jsonArray.getJSONObject(i).getString("jumlah"));
                reportOut.setHarga(jsonArray.getJSONObject(i).getString("harga"));
                reportOut.setBlok(jsonArray.getJSONObject(i).getString("keterangan"));
                reportOuts.add(reportOut);
            }
            dataReportOut.onReport(reportOuts);
        }else{
            Toast.makeText(context,"Message : "+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
        }
    }

    public void jsonReportOutGroup(String jsonString, DataReportOutGroup dataReportOutGroup) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.getBoolean("error")==false){
            JSONArray jsonArray = jsonObject.getJSONArray("hasil");
            ArrayList<ReportOutGroup> reportOutGroups = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                ReportOutGroup reportIn = new ReportOutGroup();
                reportIn.setTim(jsonArray.getJSONObject(i).getString("tim"));
                reportIn.setTanggal(jsonArray.getJSONObject(i).getString("tanggal"));
                reportIn.setKeterangan(jsonArray.getJSONObject(i).getString("keterangan"));
                JSONArray masuk = jsonArray.getJSONObject(i).getJSONArray("keluar");
                ArrayList<String> material = new ArrayList<>();
                ArrayList<String> satuan = new ArrayList<String>();
                ArrayList<String> jumlah = new ArrayList<String>();
                ArrayList<String> harga = new ArrayList<String>();
                ArrayList<String> total = new ArrayList<String>();
                for(int b = 0; b < masuk.length(); b++){
                    material.add(masuk.getJSONObject(b).getString("material"));
                    satuan.add(masuk.getJSONObject(b).getString("satuan"));
                    jumlah.add(masuk.getJSONObject(b).getString("jumlah"));
                    harga.add(masuk.getJSONObject(b).getString("harga"));
                    total.add(masuk.getJSONObject(b).getString("total"));

                    reportIn.setMaterial(material);
                    reportIn.setSatuan(satuan);
                    reportIn.setJumlah(jumlah);
                    reportIn.setHarga(harga);
                    reportIn.setTotal(total);
                }
                reportOutGroups.add(reportIn);
            }
            dataReportOutGroup.onReport(reportOutGroups);
        }else{
            Toast.makeText(context,"Message : "+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
        }
    }

    public interface DataMaterial{
        void onMaterial(ArrayList<Material_Stok> material_stoks);
    }

    public interface DataReportIn{
        void onReport(ArrayList<ReportIn> reportIns);
    }

    public interface DataReportInGroup{
        void onReport(ArrayList<ReportInGroup> reportInGroups);
    }

    public interface DataReportOut{
        void onReport(ArrayList<ReportOut> reportOuts);
    }

    public interface DataReportOutGroup{
        void onReport(ArrayList<ReportOutGroup> reportOutGroups);
    }

//    public void jsonDataMaterialStok(String jsonString, DataMaterial dataMaterial) throws JSONException{
//        JSONObject jsonObject = new JSONObject(jsonString);
//        if(jsonObject.getBoolean("error")==false){
//            JSONArray jsonArray = jsonObject.getJSONArray("hasil");
//            RecyclerView.Adapter rvAdapter;
//            List<Material_Stok> materials = new ArrayList<>();
//            for(int i = 0; i < jsonArray.length(); i++){
//                Material_Stok material = new Material_Stok();
//                material.setKode(jsonArray.getJSONObject(i).getString("kode"));
//                material.setNama(jsonArray.getJSONObject(i).getString("material"));
//                material.setSatuan(jsonArray.getJSONObject(i).getString("satuan"));
//                material.setHarga(String.valueOf(jsonArray.getJSONObject(i).getDouble("harga")));
//                material.setJml_masuk(String.valueOf(jsonArray.getJSONObject(i).getInt("masuk")));
//                material.setJml_keluar(String.valueOf(jsonArray.getJSONObject(i).getInt("keluar")));
//                material.setJml_stok(String.valueOf(jsonArray.getJSONObject(i).getInt("stok")));
//                materials.add(material);
//            }
//            dataMaterial.onMaterial(materials);
//            MskTerz.jumlahdata = jsonArray.length();
////            rvAdapter = new StokAdapter(context, fm, materials);
////            rv.setAdapter(rvAdapter);
//        }else{
//            Toast.makeText(context,"Message : "+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
//        }
//    }
}
