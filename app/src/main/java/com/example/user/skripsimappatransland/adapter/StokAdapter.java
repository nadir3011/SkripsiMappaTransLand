package com.example.user.skripsimappatransland.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.fragment.StokFragment;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;

import java.util.List;

/**
 * Created by User on 8/8/2017.
 */

public class StokAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int TYPE_ITEM = 0;
    private final int TYPE_FOOTER = 1;
    private Context context;
    private List<Material_Stok> material_stoks;
    private FragmentManager fm;

    public StokAdapter(Context context, List<Material_Stok> material_stoks){
        super();
        this.context = context;
        this.material_stoks = material_stoks;
    }


    public StokAdapter(Context context, FragmentManager fm, List<Material_Stok> material_stoks){
        super();
        this.context = context;
        this.fm = fm;
        this.material_stoks = material_stoks;
    }

    public int getItemViewType(int position){
        if(isPositionFooter(position)){
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private Boolean isPositionFooter(int position){
        int asli = MskTerz.jumlahdata / MskTerz.banyakdata;
        if(MskTerz.datake == asli && (position == MskTerz.jumlahdata - (asli * MskTerz.banyakdata))){
            return true;
        }
        return position >= MskTerz.banyakdata;
    }



//    @Override
//    public viewHolderStok onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_stok, parent, false);
//        viewHolderStok vh = new viewHolderStok(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(viewHolderStok holder, int position) {
//        final Material_Stok material_stok = material_stoks.get(position);
//        holder.txt_nomor.setText(String.valueOf(position+1));
//        holder.txt_kode.setText(material_stok.getKode());
//        holder.txt_material.setText(material_stok.getNama());
//        holder.txt_stok.setText(material_stok.getJml_stok());
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_stok, parent, false);
            return new StokAdapter.itemViewHolderStok(view);
        }else if(viewType == TYPE_FOOTER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_button_stok, parent, false);
            return new StokAdapter.footerViewHolderStok(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof itemViewHolderStok){
            final Material_Stok material_stok = material_stoks.get(position);
//        holder.txt_nomor.setText(String.valueOf(position+1));
//        holder.txt_kode.setText(material_stok.getKode());
//        holder.txt_material.setText(material_stok.getNama());
//        holder.txt_stok.setText(material_stok.getJml_stok());
            int sekarang = (MskTerz.datake*MskTerz.banyakdata)+(position)+1;
            ((itemViewHolderStok)holder).txt_nomor.setText(String.valueOf(sekarang));
            ((itemViewHolderStok)holder).txt_kode.setText("Kode : "+material_stok.getKode());
            ((itemViewHolderStok)holder).txt_material.setText("Material : "+material_stok.getNama());
            ((itemViewHolderStok)holder).txt_stok.setText("STOK : "+material_stok.getJml_stok());

        }else if(holder instanceof footerViewHolderStok){

            ((footerViewHolderStok)holder).btn_next.setVisibility(View.GONE);
            ((footerViewHolderStok)holder).btn_prev.setVisibility(View.GONE);

            int cek = (int) Math.ceil((double)MskTerz.jumlahdata / (double)MskTerz.banyakdata);
            int asli =(MskTerz.jumlahdata / MskTerz.banyakdata);
            if(MskTerz.datake == 0 || MskTerz.banyakdata == MskTerz.jumlahdata ){
                ((footerViewHolderStok)holder).btn_next.setVisibility(View.VISIBLE);
            }else if(MskTerz.datake == asli || (MskTerz.datake + 1) * MskTerz.banyakdata == MskTerz.jumlahdata){
                ((footerViewHolderStok)holder).btn_prev.setVisibility(View.VISIBLE);
            }else{
                ((footerViewHolderStok)holder).btn_next.setVisibility(View.VISIBLE);
                ((footerViewHolderStok)holder).btn_prev.setVisibility(View.VISIBLE);
            }


            ((footerViewHolderStok)holder).btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StokFragment fragment = new StokFragment(context);
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    MskTerz.datake +=1;
                }
            });

            ((footerViewHolderStok)holder).btn_prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fm.popBackStack();
                    MskTerz.datake -=1;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int asli = MskTerz.jumlahdata / MskTerz.banyakdata;
        if(MskTerz.jumlahdata < MskTerz.banyakdata){
            return MskTerz.jumlahdata;
        }else if(MskTerz.datake == asli){
            return (MskTerz.jumlahdata - (asli * MskTerz.banyakdata))+1;
        }else if(MskTerz.banyakdata == MskTerz.jumlahdata){
            return MskTerz.banyakdata;
        } else{
            return (MskTerz.banyakdata)+TYPE_FOOTER;
        }
    }

    public class itemViewHolderStok extends RecyclerView.ViewHolder{
        public View view;
        TextView txt_nomor, txt_kode, txt_material, txt_stok;
        public itemViewHolderStok(View v) {
            super(v);
            view = v;
            txt_nomor = (TextView) v.findViewById(R.id.txt_stok_id);
            txt_kode = (TextView) v.findViewById(R.id.txt_stok_kode);
            txt_material = (TextView) v.findViewById(R.id.txt_stok_nama);
            txt_stok = (TextView) v.findViewById(R.id.txt_stok_jml);
        }
    }

    public class footerViewHolderStok extends RecyclerView.ViewHolder{
        public View view;
        private Button btn_prev, btn_next;
        public footerViewHolderStok(View v) {
            super(v);
            view = v;
            btn_prev = (Button) v.findViewById(R.id.btn_prev);
            btn_next = (Button) v.findViewById(R.id.btn_next);
        }
    }
}
