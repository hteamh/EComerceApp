package com.example.a2019.ecomerceapp.Admin.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class CommoditiesAdapter extends RecyclerView.Adapter<CommoditiesAdapter.ViewHolder> {


    // don't forget to change the model

    List<CategoryModel> list;
    OnCommodityClickedListener onCommodityClickedListener;
    OnCommodityEditListener onCommodityEditListener;



    public CommoditiesAdapter(List<CategoryModel> list) {
        this.list = list;
    }

    public void setOnCommodityEditListener(OnCommodityEditListener onCommodityEditListener) {
        this.onCommodityEditListener = onCommodityEditListener;
    }

    public void setOnCommodityClickedListener(OnCommodityClickedListener onCommodityClickedListener) {
        this.onCommodityClickedListener = onCommodityClickedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.commodity_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int pos) {
        final CategoryModel model = list.get(pos);
        viewHolder.text_item.setText(model.getName());
        viewHolder.price.setText(model.getDescription());
        Glide.with(viewHolder.itemView)
                .load(model.getImageUri()).into(viewHolder.image_item);

        if (onCommodityClickedListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCommodityClickedListener.onItemClicked(pos,model);
                }
            });
        }

        if (onCommodityEditListener!=null){
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCommodityEditListener.onItemEdit(pos,model);
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_item;
        TextView text_item, price;
        Button edit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_item = itemView.findViewById(R.id.commodity_image);
            text_item = itemView.findViewById(R.id.name_comm);
            price = itemView.findViewById(R.id.price_comm);
            edit=itemView.findViewById(R.id.edit_comm);
        }
    }

    public interface OnCommodityClickedListener{
        void onItemClicked(int pos, CategoryModel model);
    }

    public interface OnCommodityEditListener{
        void onItemEdit(int pos, CategoryModel model);
    }


}
