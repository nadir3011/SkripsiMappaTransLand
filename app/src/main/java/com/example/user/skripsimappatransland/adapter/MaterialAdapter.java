package com.example.user.skripsimappatransland.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.user.skripsimappatransland.activity.StokActivity;
import com.example.user.skripsimappatransland.fragment.DataMaterialFragment;
import com.example.user.skripsimappatransland.R;
import com.example.user.skripsimappatransland.model.Material_InOut;
import com.example.user.skripsimappatransland.model.Material_Stok;
import com.example.user.skripsimappatransland.model.MskTerz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/8/2017.
 */

public class MaterialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int TYPE_ITEM = 0;
    private final int TYPE_FOOTER = 1;
    private Context context;
    private ArrayList<Material_Stok> material_stoks;
    private ArrayList<Material_InOut> material_inOuts;
    private FragmentManager fm;
    private int urutan;

//    public MaterialAdapter(Context context, List<Material_Stok> material_stoks){
//        super();
//        this.context = context;
//        this.material_stoks = material_stoks;
//    }

    public MaterialAdapter(Context context, ArrayList<Material_Stok> material_stoks, ArrayList<Material_InOut> material_inOuts, FragmentManager fm, int urutan){
        super();
        this.context = context;
        this.material_stoks = material_stoks;
        this.material_inOuts = material_inOuts;
        this.urutan = urutan;
        this.fm = fm;
    }

    public int getItemViewType(int position){
        if(isPositionFooter(position)){
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private Boolean isPositionFooter(int position){
        int asli = MskTerz.jumlahdatamaterial / MskTerz.banyakdata;
        if(MskTerz.datakematerial == asli && (position == MskTerz.jumlahdatamaterial - (asli * MskTerz.banyakdata))){
            return true;
        }
        return position >= MskTerz.banyakdata;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_material, parent, false);
            return new MaterialAdapter.itemViewHolderMaterial(view);
        }else if(viewType == TYPE_FOOTER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_button_stok, parent, false);
            return new MaterialAdapter.footerViewHolderMaterial(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        if(holder instanceof itemViewHolderMaterial){
            try {
                final Material_Stok material_stok = material_stoks.get((MskTerz.datakematerial*MskTerz.banyakdata)+(position));
                final int sekarang = (MskTerz.datakematerial*MskTerz.banyakdata)+(position)+1;
                ((itemViewHolderMaterial)holder).txt_nomor.setText(String.valueOf(sekarang));
                ((itemViewHolderMaterial)holder).txt_kode.setText("Kode : "+material_stok.getKode());
                ((itemViewHolderMaterial)holder).txt_material.setText("Material : "+material_stok.getNama());
                ((itemViewHolderMaterial)holder).txt_stok.setText("STOK : "+material_stok.getJml_stok());
                ((itemViewHolderMaterial)holder).img_material.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        material_inOuts.get(urutan).setKode(material_stok.getKode());
                        material_inOuts.get(urutan).setMaterial(material_stok.getNama());


                        material_stoks.remove((MskTerz.datakematerial*MskTerz.banyakdata)+(position));
                        for(int a=0; a<=MskTerz.datakematerial;a++){
                            fm.popBackStack();
                        }

                    }
                });
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

//                    Intent i = new Intent(context, StokActivity.class);
//                    i.putParcelableArrayListExtra("material",material_stoks);
//                    context.startActivity(i);

                    DataMaterialFragment fragment = new DataMaterialFragment(context);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("material", material_stoks);
                    bundle.putParcelableArrayList("inout", material_inOuts);
                    fragment.setArguments(bundle);
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
        int asli = MskTerz.jumlahdatamaterial / MskTerz.banyakdata;
        if(MskTerz.jumlahdatamaterial < MskTerz.banyakdata){
            return MskTerz.jumlahdatamaterial;
        }else if(MskTerz.datakematerial == asli){
            return (MskTerz.jumlahdatamaterial - (asli * MskTerz.banyakdata))+1;
        }else if(MskTerz.banyakdata == MskTerz.jumlahdatamaterial){
            return MskTerz.banyakdata;
        } else{
            return (MskTerz.banyakdata)+TYPE_FOOTER;
        }
    }

    public class itemViewHolderMaterial extends RecyclerView.ViewHolder{
        private View view;
        private TextView txt_nomor, txt_material, txt_kode, txt_stok;
        private ImageView img_material;

        public itemViewHolderMaterial(View v) {
            super(v);
            view = v;
            txt_nomor = (TextView) v.findViewById(R.id.txt_material_id);
            txt_kode = (TextView) v.findViewById(R.id.txt_material_kode);
            txt_material = (TextView) v.findViewById(R.id.txt_material_nama);
            txt_stok = (TextView) v.findViewById(R.id.txt_material_stok);
            img_material = (ImageView) v.findViewById(R.id.img_material);
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
