package com.example.a2019.ecomerceapp.Customers.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.Item_Images_Models;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    List<Item_Images_Models> item_images_modelsList;

    public ImageAdapter(List<Item_Images_Models> item_images_modelsList) {
        this.item_images_modelsList = item_images_modelsList;
    }
    public void ChangeData(List<Item_Images_Models> item_images_modelsList)
    {
        this.item_images_modelsList = item_images_modelsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_in_details_customer_item,
                viewGroup,false) ;
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        Item_Images_Models item_images_models = this.item_images_modelsList.get(pos);
        Glide.with(viewHolder.itemView).load(item_images_models.getUrl()).into(viewHolder.myImage);
    }

    @Override
    public int getItemCount() {
if(this.item_images_modelsList==null)
    return 0;
    else
    return this.item_images_modelsList.size();
    }

    public  class ViewHolder  extends RecyclerView.ViewHolder {
     ImageView myImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myImage= itemView.findViewById(R.id.Image);
        }
    }
}
