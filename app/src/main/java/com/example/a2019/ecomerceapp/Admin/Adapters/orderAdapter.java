package com.example.a2019.ecomerceapp.Admin.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.MyVH> {
  private   List<ItemModel> itemModels;

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sup_order_priduct_item,viewGroup,false);
        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH myVH, int i) {
        ItemModel myitem = this.itemModels.get(i);
        myVH.Des.setText(myitem.getDescription());
        myVH.Count.setText(myitem.getCount());
        myVH.name.setText(myitem.getName());
        Glide.with(myVH.itemView)
                .load(myitem.getImageUri()).into(myVH.imageView);
    }

    @Override
    public int getItemCount() {
        if(this.itemModels==null) return 0;
        return this.itemModels.size();
    }

    public orderAdapter(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }
    public  void ChangeData(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
        this.notifyDataSetChanged();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name , Count,Des;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Productname);
            Count = itemView.findViewById(R.id.ProductCount);
            imageView = itemView.findViewById(R.id.productimage);
            Des = itemView.findViewById(R.id.ProductDes);
        }
    }
}
