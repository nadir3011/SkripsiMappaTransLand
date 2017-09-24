package com.example.user.skripsimappatransland.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.skripsimappatransland.model.Material;
import com.example.user.skripsimappatransland.R;

import java.util.List;

/**
 * Created by User on 8/8/2017.
 */

public class MaterialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int TYPE_ITEM = 0;
    private final int TYPE_FOOTER = 1;
    Context context;
    List<Material> materials;
    FragmentManager fm;
    public MaterialAdapter(Context context, List<Material> materials){
        super();
        this.context = context;
        this.materials = materials;
    }

    public MaterialAdapter(Context context, List<Material> materials, FragmentManager fm){
        super();
        this.context = context;
        this.materials = materials;
        this.fm = fm;
    }

//    @Override
//    public itemViewHolderMaterial onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_material, parent, false);
//        itemViewHolderMaterial vh = new itemViewHolderMaterial(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(itemViewHolderMaterial holder, int position) {
//        final Material material = materials.get(position);
//        holder.txt_nomor.setText(String.valueOf(position+1));
//        holder.txt_kode.setText(material.getKd_material());
//        holder.txt_material.setText(material.getMaterial()+"/"+material.getSatuan());
//
//        holder.img_material.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_stok, parent, false);
            return new MaterialAdapter.itemViewHolderMaterial(view);
        }else if(viewType == TYPE_FOOTER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_button_stok, parent, false);
            return new MaterialAdapter.footerViewHolderMaterial(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return materials.size();
    }

    public class itemViewHolderMaterial extends RecyclerView.ViewHolder{
        private View view;
        private TextView txt_nomor, txt_material, txt_kode;
        private ImageView img_material;

        public itemViewHolderMaterial(View v) {
            super(v);
            view = v;
            txt_nomor = (TextView) v.findViewById(R.id.txt_material_id);
            txt_kode = (TextView) v.findViewById(R.id.txt_material_kode);
            txt_material = (TextView) v.findViewById(R.id.txt_material_nama);

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
