package com.example.user.skripsimappatransland.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.example.user.skripsimappatransland.model.Material_InOut;

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
    private List<Material_InOut> material_inOuts;
    private Boolean validasi = Boolean.FALSE;
//    private OnEditTextChanged onEditTextChanged;

    public TransaksiInOutAdapter(Context context, int jumlah){
        super();
        this.context = context;
        this.jumlah = jumlah;
        material_inOuts = new ArrayList<>();
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
//
//    public interface OnEditTextChanged {
//        void onTextChanged(int position, String charSeq);
//    }

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
            ((itemViewHolderTransaksi)holder).txt_nomor.setText(String.valueOf(position+1));
            ((itemViewHolderTransaksi)holder).txt_kode.setText("");
            ((itemViewHolderTransaksi)holder).txt_material.setText("");

            ((itemViewHolderTransaksi)holder).edt_jumlah.setText("");
            ((itemViewHolderTransaksi)holder).edt_harga.setText("");
            ((itemViewHolderTransaksi)holder).edt_total.setText("");

            final Material_InOut material_inOut = new Material_InOut();
            material_inOut.setPosition(position);
            material_inOut.setJumlah("");
            material_inOut.setHarga("");
            material_inOut.setKode("");

            ((itemViewHolderTransaksi)holder).edt_jumlah.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOut.setJumlah(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOut.setJumlah(((itemViewHolderTransaksi)holder).edt_jumlah.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            ((itemViewHolderTransaksi)holder).edt_harga.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOut.setHarga(((itemViewHolderTransaksi)holder).edt_harga.getText().toString());
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOut.setHarga(((itemViewHolderTransaksi)holder).edt_harga.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            ((itemViewHolderTransaksi)holder).txt_kode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOut.setKode(((itemViewHolderTransaksi)holder).txt_kode.getText().toString());
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    material_inOut.setKode(((itemViewHolderTransaksi)holder).txt_kode.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            material_inOuts.add(material_inOut);
            ((itemViewHolderTransaksi)holder).img_material.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Pilih Material ke-"+(position+1),Toast.LENGTH_SHORT).show();
                    ((itemViewHolderTransaksi)holder).txt_kode.setText("MTR-"+(position+1));
                    ((itemViewHolderTransaksi)holder).txt_material.setText("MaTeRiaL-"+(position+1));
                }
            });

        }else if(holder instanceof footerViewHolderTransaksi){
            ((footerViewHolderTransaksi)holder).btn_transaksi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int a = 0; a < jumlah;a++){
                        Material_InOut material_inOut = (Material_InOut) material_inOuts.get(a);
                        if(material_inOut.getJumlah().length()==0){
                            Toast.makeText(context, "Data Jumlah Belum Di Isi...! - "+(material_inOut.getPosition()+1),Toast.LENGTH_SHORT).show();
                            return ;
                        }
                    }
                    ((Activity) context).finish();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return jumlah+TYPE_FOOTER;
    }

    public class itemViewHolderTransaksi extends RecyclerView.ViewHolder{
        public View view;
        private TextView txt_nomor, txt_kode, txt_material;
        private EditText edt_jumlah, edt_harga, edt_total;
        private ImageView img_material;
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
