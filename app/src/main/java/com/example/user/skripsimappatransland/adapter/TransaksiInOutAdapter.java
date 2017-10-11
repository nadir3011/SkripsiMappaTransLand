package com.example.user.skripsimappatransland.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.activity.DataMaterialActivity;
import com.example.user.skripsimappatransland.fragment.DataMaterialFragment;
import com.example.user.skripsimappatransland.fragment.MaterialInLanjutFragment;
import com.example.user.skripsimappatransland.json.JSON;
import com.example.user.skripsimappatransland.model.Material_InOut;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.volley.RequestSTRING;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/8/2017.
 */

public class TransaksiInOutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private int jumlah;
    private final int TYPE_ITEM = 0;
    private final int TYPE_FOOTER = 1;
    private Boolean validasi = Boolean.FALSE;

    private String kodeTransaksi = "";
    private String kodeInOut = "";

    private String supplier = "";
    private String keterangan = "";
    private String team = "";

    private int material_input = 0;
    private int material_ouput = 1;
    private int material_apa;

    private FragmentManager fm;

    private ArrayList<Material_Stok> material_stoks;
    private ArrayList<Material_InOut> material_inOuts = new ArrayList<>();
//    private OnEditTextChanged onEditTextChanged;

    public TransaksiInOutAdapter(Context context, FragmentManager fm, ArrayList<Material_Stok> material_stoks, ArrayList<Material_InOut> material_inOuts, int jumlah, int material_apa, String supplier){
        super();
        this.context = context;
        this.fm = fm;
        this.material_stoks = material_stoks;
        this.jumlah = jumlah;
        this.material_apa = material_apa;
        this.supplier = supplier;
        this.material_inOuts.clear();
        this.material_inOuts.addAll(material_inOuts);

        getKodeTransaksiIn(new AutoNumber() {
            @Override
            public void onKode(String kode) {
                kodeTransaksi = kode;
            }
        });

    }

    public TransaksiInOutAdapter(Context context, FragmentManager fm, ArrayList<Material_Stok> material_stoks, ArrayList<Material_InOut> material_inOuts, int jumlah, int material_apa, String team, String keterangan){
        super();
        this.context = context;
        this.fm = fm;
        this.material_stoks = material_stoks;
        this.jumlah = jumlah;
        this.material_apa = material_apa;
        this.team = team;
        this.keterangan = keterangan;
        this.material_inOuts = material_inOuts;

        getKodeTransaksiOut(new AutoNumber() {
            @Override
            public void onKode(String kode) {
                kodeTransaksi = kode;
            }
        });

    }

    public int getItemViewType(int position){

        if(isPositionFooter(position)){
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private Boolean isPositionFooter(int position){
        return position >= jumlah;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_transaksi, parent, false);
            return new itemViewHolderTransaksi(view);
        }else if(viewType == TYPE_FOOTER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_button, parent, false);
            return new footerViewHolderTransaksi(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof itemViewHolderTransaksi){

            final Material_InOut material_inOut = material_inOuts.get(position);

            ((itemViewHolderTransaksi)holder).txt_nomor.setText(String.valueOf(position+1));
            ((itemViewHolderTransaksi)holder).txt_kode.setText(material_inOut.getKode());
            ((itemViewHolderTransaksi)holder).txt_material.setText(material_inOut.getMaterial()+" ("+material_inOut.getStok()+")");

            ((itemViewHolderTransaksi)holder).edt_jumlah.setText(material_inOut.getJumlah());
            ((itemViewHolderTransaksi)holder).edt_harga.setText(material_inOut.getHarga());
            ((itemViewHolderTransaksi)holder).edt_total.setText(material_inOut.getTotal());

            if(material_apa == material_ouput){
                ((itemViewHolderTransaksi)holder).edt_harga.setEnabled(false);
            }

            ((itemViewHolderTransaksi)holder).edt_total.setEnabled(false);


            ((itemViewHolderTransaksi)holder).edt_jumlah.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOuts.get(position).setJumlah(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());

//                    material_inOut.setJumlah(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOuts.get(position).setJumlah(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());

//                    material_inOut.setJumlah(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            ((itemViewHolderTransaksi)holder).edt_harga.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOuts.get(position).setHarga(((itemViewHolderTransaksi)holder).edt_harga.getText().toString());

//                    material_inOut.setHarga(((itemViewHolderTransaksi)holder).edt_harga.getText().toString());
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOuts.get(position).setHarga(((itemViewHolderTransaksi)holder).edt_harga.getText().toString());

                    String harga = ((itemViewHolderTransaksi)holder).edt_harga.getText().toString();
                    Integer jumlah = Integer.parseInt(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());
                    Double total = 0.0;
                    if(harga.length() != 0){
                        total = Double.parseDouble(harga) * jumlah;
                        ((itemViewHolderTransaksi)holder).edt_total.setText(String.valueOf(total));
                        material_inOut.setTotal(((itemViewHolderTransaksi)holder).edt_total.getText().toString());
                    }else{
                        ((itemViewHolderTransaksi)holder).edt_total.setText("");
                        material_inOut.setTotal(((itemViewHolderTransaksi)holder).edt_total.getText().toString());
                    }

//                    material_inOut.setHarga(((itemViewHolderTransaksi)holder).edt_harga.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            ((itemViewHolderTransaksi)holder).txt_kode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOuts.get(position).setKode(((itemViewHolderTransaksi)holder).txt_kode.getText().toString());

//                    material_inOut.setKode(((itemViewHolderTransaksi)holder).txt_kode.getText().toString());
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOuts.get(position).setKode(((itemViewHolderTransaksi)holder).txt_kode.getText().toString());

//                    material_inOut.setKode(((itemViewHolderTransaksi)holder).txt_kode.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            ((itemViewHolderTransaksi)holder).img_kurang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int kurang = Integer.parseInt(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());
                    if(material_inOut.getStok() > kurang && material_apa==material_ouput){
                        if(kurang > 1){
                            kurang -= 1;
                            ((itemViewHolderTransaksi)holder).edt_jumlah.setText(String.valueOf(kurang));

                            String harga = ((itemViewHolderTransaksi)holder).edt_harga.getText().toString();
                            Integer jumlah = Integer.parseInt(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());
                            Double total = 0.0;
                            if(harga.length() != 0){
                                total = Double.parseDouble(harga) * jumlah;
                                ((itemViewHolderTransaksi)holder).edt_total.setText(String.valueOf(total));
                                material_inOut.setTotal(((itemViewHolderTransaksi)holder).edt_total.getText().toString());
                            }
                        }
                    }else if(material_apa == material_input){
                        if(kurang > 1){
                            kurang -= 1;
                            ((itemViewHolderTransaksi)holder).edt_jumlah.setText(String.valueOf(kurang));
                        }
                    }

                }
            });

            ((itemViewHolderTransaksi)holder).img_tambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tambah = Integer.parseInt(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());
                    if(material_inOut.getStok() > tambah && material_apa==material_ouput){
                        if(tambah >= 0 && tambah <= 150){
                            tambah +=1;
                            ((itemViewHolderTransaksi)holder).edt_jumlah.setText(String.valueOf(tambah));

                            String harga = ((itemViewHolderTransaksi)holder).edt_harga.getText().toString();
                            Integer jumlah = Integer.parseInt(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());
                            Double total = 0.0;
                            if(harga.length() != 0){
                                total = Double.parseDouble(harga) * jumlah;
                                ((itemViewHolderTransaksi)holder).edt_total.setText(String.valueOf(total));
                                material_inOut.setTotal(((itemViewHolderTransaksi)holder).edt_total.getText().toString());
                            }
                        }
                    }else if(material_apa == material_input){
                        if(tambah >= 1 && tambah <= 150){
                            tambah +=1;
                            ((itemViewHolderTransaksi)holder).edt_jumlah.setText(String.valueOf(tambah));
                        }
                    }

                }
            });

            ((itemViewHolderTransaksi)holder).img_material.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MskTerz.datakematerial = 0;

//                    Intent i = new Intent(context, DataMaterialActivity.class);
//                    i.putParcelableArrayListExtra("material",material_stoks);
//                    i.putParcelableArrayListExtra("inout",material_inOuts);
//                    i.putExtra("position", position);
//                    i.putExtra("transaksi", material_apa);
//                    context.startActivity(i);

                    DataMaterialFragment fragment = new DataMaterialFragment(context);
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("material", material_stoks);
                    bundle.putParcelableArrayList("inout", material_inOuts);
                    bundle.putInt("position", position);
                    bundle.putInt("transaksi", material_apa);
                    fragment.setArguments(bundle);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

        }else if(holder instanceof footerViewHolderTransaksi){
            ((footerViewHolderTransaksi)holder).btn_transaksi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(material_apa == material_input){
                        saveTransaksiIn();
                    }else if(material_apa == material_ouput){
                        saveTransaksiOut();
                    }

                }
            });
        }
    }


    void saveTransaksiIn(){
        getKodeTransaksiIn(new AutoNumber() {
            @Override
            public void onKode(String kode) {
                kodeTransaksi = kode;
            }
        });


        for(int a = 0; a < jumlah;a++) {
            Material_InOut material_inOut = (Material_InOut) material_inOuts.get(a);
            if (material_inOut.getJumlah().length() == 0 || material_inOut.getHarga().length() == 0 || material_inOut.getKode().length() == 0) {
                Toast.makeText(context, "Data Jumlah Belum Di Isi...! - " + (material_inOut.getPosition() + 1), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        for( int a = 0; a < jumlah;a++){
            Material_InOut material_inOut = (Material_InOut) material_inOuts.get(a);

            final int berhasil = a;
            RequestSTRING rs = new RequestSTRING(context);
            rs.setUrlnya(MskTerz.url+"/materialmasuk/"+MskTerz.M_apikey);
            rs.setTitle("Transaksi");
            rs.setMessage("Proses . . . . !");
            rs.setTagString("MSKTERZ_INOUT");
            rs.setKeynya(new String[]{"transaksi", "material", "user", "supplier", "jumlah", "harga"});
            rs.setValuenya(new String[]{kodeTransaksi, material_inOut.getKode(),  MskTerz.M_user, supplier, material_inOut.getJumlah(), material_inOut.getHarga()});
            rs.string_post(new RequestSTRING.VolleyCallBack() {
                @Override
                public void onSuccess(String result) throws JSONException {
                    JSON json = new JSON(context);
                    if(json.jsonSukses(result)){
                        if(berhasil == jumlah-1){
                            ((Activity) context).finish();
                        }
                    }else{
                        Toast.makeText(context,"Ada yang Salah...!",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }


    void saveTransaksiOut(){
        getKodeTransaksiOut(new AutoNumber() {
            @Override
            public void onKode(String kode) {
                kodeTransaksi = kode;
            }
        });



        for(int a = 0; a < jumlah;a++) {
            Material_InOut material_inOut = (Material_InOut) material_inOuts.get(a);
            if (material_inOut.getJumlah().length() == 0 || material_inOut.getHarga().length() == 0 || material_inOut.getKode().length() == 0 || material_inOut.getStok()==0) {
                Toast.makeText(context, "Data Jumlah Belum Di Isi...! - " + (material_inOut.getPosition() + 1), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        for(int a = 0; a < jumlah;a++){
            Material_InOut material_inOut = (Material_InOut) material_inOuts.get(a);

            final int berhasil = a;

            RequestSTRING rs = new RequestSTRING(context);
            rs.setUrlnya(MskTerz.url+"/materialkeluar/"+MskTerz.M_apikey);
            rs.setTitle("Transaksi");
            rs.setMessage("Proses . . . . !");
            rs.setTagString("MSKTERZ_INOUT");
            rs.setKeynya(new String[]{"transaksi", "material", "user", "tim", "jumlah", "harga", "ket"});
            rs.setValuenya(new String[]{kodeTransaksi, material_inOut.getKode(),  MskTerz.M_user, team, material_inOut.getJumlah(), material_inOut.getHarga(), keterangan});
            rs.string_post(new RequestSTRING.VolleyCallBack() {
                @Override
                public void onSuccess(String result) throws JSONException {
                    JSON json = new JSON(context);
                    if(json.jsonSukses(result)){
                        if(berhasil == jumlah-1){
                            ((Activity) context).finish();
                        }
                    }else{
                        Toast.makeText(context,"Ada yang Salah...!",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    public interface AutoNumber{
        void onKode(String kode);
    }

    void getKodeTransaksiIn(final AutoNumber autoNumber){
        RequestSTRING rs = new RequestSTRING(context);
        rs.setUrlnya(MskTerz.url+"/autonumber"+"/"+MskTerz.M_apikey);
        rs.setTitle("Cari Kode");
        rs.setMessage("Proses . . . . !");
        rs.setTagString("MSKTERZ_KODE");
        rs.setKeynya(new String[]{"table","kolom","lebar","awalan","user"});
        rs.setValuenya(new String[]{"mskter_material_masuk", "kd_transaksi_in", "3", "INTRA-", MskTerz.M_user});
        rs.string_status(new RequestSTRING.VolleyCallBack() {
            @Override
            public void onSuccess(String result) throws JSONException {
                String kode="";
                JSON json = new JSON(context);
                kode = json.jsonKode(result);
                autoNumber.onKode(kode);
            }
        });
    }
    void getKodeTransaksiOut(final AutoNumber autoNumber){
        RequestSTRING rs = new RequestSTRING(context);
        rs.setUrlnya(MskTerz.url+"/autonumber"+"/"+MskTerz.M_apikey);
        rs.setTitle("Cari Kode");
        rs.setMessage("Proses . . . . !");
        rs.setTagString("MSKTERZ_KODE");
        rs.setKeynya(new String[]{"table","kolom","lebar","awalan","user"});
        rs.setValuenya(new String[]{"mskter_material_out", "kd_transaksi_out", "3", "OUTTRA-", MskTerz.M_user});
        rs.string_status(new RequestSTRING.VolleyCallBack() {
            @Override
            public void onSuccess(String result) throws JSONException {
                String kode="";
                JSON json = new JSON(context);
                kode = json.jsonKode(result);
                autoNumber.onKode(kode);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jumlah+TYPE_FOOTER;
    }

    public class itemViewHolderTransaksi extends RecyclerView.ViewHolder{
        public View view;
        private TextView txt_nomor, txt_kode, txt_material;
        private EditText edt_jumlah, edt_harga, edt_total;
        private ImageView img_material, img_tambah, img_kurang;
        public itemViewHolderTransaksi(View v) {
            super(v);
            view = v;
            txt_nomor = (TextView) v.findViewById(R.id.txt_trans_id);
            txt_kode = (TextView) v.findViewById(R.id.txt_trans_kode);
            txt_material = (TextView) v.findViewById(R.id.txt_trans_nama);

            edt_jumlah = (EditText) v.findViewById(R.id.edt_trans_jumlah);
            edt_harga = (EditText) v.findViewById(R.id.edt_trans_harga);
            edt_total = (EditText) v.findViewById(R.id.edt_trans_total);

            img_material = (ImageView) v.findViewById(R.id.img_trans_material);
            img_kurang = (ImageView) v.findViewById(R.id.img_kurang);
            img_tambah = (ImageView) v.findViewById(R.id.img_tambah);

        }
    }

    public class footerViewHolderTransaksi extends RecyclerView.ViewHolder{
        public View view;
        private Button btn_transaksi;
        public footerViewHolderTransaksi(View v) {
            super(v);
            view = v;
            btn_transaksi = (Button) v.findViewById(R.id.btn_transaction);
        }
    }
}
