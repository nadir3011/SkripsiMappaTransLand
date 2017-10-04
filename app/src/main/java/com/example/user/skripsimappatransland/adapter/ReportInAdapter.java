package com.example.user.skripsimappatransland.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.fragment.DataMaterialFragment;
import com.example.user.skripsimappatransland.model.Material_InOut;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;
import com.example.user.skripsimappatransland.model.ReportIn;

import java.util.ArrayList;

/**
 * Created by User on 8/8/2017.
 */

public class ReportInAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int TYPE_ITEM = 0;
    private final int TYPE_FOOTER = 1;
    private Context context;
    private ArrayList<ReportIn> reportIns;
    private FragmentManager fm;


    public ReportInAdapter(Context context, ArrayList<ReportIn> reportIns, FragmentManager fm){
        super();
        this.context = context;
        this.reportIns = reportIns;
        this.fm = fm;
    }

    public int getItemViewType(int position){
        if(isPositionFooter(position)){
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private Boolean isPositionFooter(int position){
        if(reportIns.size()==position){
            return true;
        }
        return position >= reportIns.size()-1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_report, parent, false);
            return new ReportInAdapter.itemViewHolderMaterial(view);
        }else if(viewType == TYPE_FOOTER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_button_stok, parent, false);
            return new ReportInAdapter.footerViewHolderMaterial(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof itemViewHolderMaterial){
            try {
                final ReportIn reportIn = reportIns.get(position);
                final int sekarang = (position)+1;
                ((itemViewHolderMaterial)holder).txt_nomor.setText(String.valueOf(sekarang));
                ((itemViewHolderMaterial)holder).txt_material.setText(reportIn.getMaterial());
                ((itemViewHolderMaterial)holder).txt_tanggal.setText(reportIn.getTanggal() + "("+reportIn.getSupplier()+")");
                double total = Double.parseDouble(reportIn.getJumlah())*Double.parseDouble(reportIn.getHarga());
                ((itemViewHolderMaterial)holder).txt_jumlah.setText("Qty => "+reportIn.getJumlah()+"@Rp. "+reportIn.getHarga()+"(Rp. "+total);
            }catch (Exception e){
                Toast.makeText(context, "Error : "+e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }else if(holder instanceof footerViewHolderMaterial){

            ((footerViewHolderMaterial)holder).btn_next.setVisibility(View.GONE);
            ((footerViewHolderMaterial)holder).btn_prev.setVisibility(View.GONE);

            int cek = (int) Math.ceil((double)MskTerz.jumlahdatamaterial / (double)MskTerz.banyakdata);
            int asli =(MskTerz.jumlahdatamaterial / MskTerz.banyakdata);
            if(MskTerz.datakematerial == 0 || MskTerz.banyakdata == MskTerz.jumlahdatamaterial ){
                ((footerViewHolderMaterial)holder).btn_next.setVisibility(View.VISIBLE);
            }else if(MskTerz.datakematerial == asli || (MskTerz.datakematerial + 1) * MskTerz.banyakdata == MskTerz.jumlahdatamaterial){
                ((footerViewHolderMaterial)holder).btn_prev.setVisibility(View.VISIBLE);
            }else{
                ((footerViewHolderMaterial)holder).btn_next.setVisibility(View.VISIBLE);
                ((footerViewHolderMaterial)holder).btn_prev.setVisibility(View.VISIBLE);
            }


            ((footerViewHolderMaterial)holder).btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataMaterialFragment fragment = new DataMaterialFragment();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    MskTerz.datakematerial +=1;
                }
            });

            ((footerViewHolderMaterial)holder).btn_prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fm.popBackStack();
                    MskTerz.datakematerial -=1;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return reportIns.size();
//        int asli = MskTerz.jumlahdatamaterial / MskTerz.banyakdata;
//        if(MskTerz.jumlahdatamaterial < MskTerz.banyakdata){
//            return MskTerz.jumlahdatamaterial;
//        }else if(MskTerz.datakematerial == asli){
//            return (MskTerz.jumlahdatamaterial - (asli * MskTerz.banyakdata))+1;
//        }else if(MskTerz.banyakdata == MskTerz.jumlahdatamaterial){
//            return MskTerz.banyakdata;
//        } else{
//            return (MskTerz.banyakdata)+TYPE_FOOTER;
//        }
    }

    public class itemViewHolderMaterial extends RecyclerView.ViewHolder{
        private View view;
        private TextView txt_nomor, txt_material, txt_tanggal, txt_jumlah;
        private ImageView img_material;

        public itemViewHolderMaterial(View v) {
            super(v);
            view = v;
            txt_nomor = (TextView) v.findViewById(R.id.txt_report_id);
            txt_tanggal = (TextView) v.findViewById(R.id.txt_report_tanggal);
            txt_material = (TextView) v.findViewById(R.id.txt_report_material);
            txt_jumlah = (TextView) v.findViewById(R.id.txt_report_jumlah);
//            img_material = (ImageView) v.findViewById(R.id.img_material);
        }
    }

    public class footerViewHolderMaterial extends RecyclerView.ViewHolder{
        private View view;
        private Button btn_prev, btn_next;
        public footerViewHolderMaterial(View v) {
            super(v);
            view = v;
            btn_prev = (Button) v.findViewById(R.id.btn_prev);
            btn_next = (Button) v.findViewById(R.id.btn_next);
        }
    }
}
