package com.example.a2019.ecomerceapp.Admin.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.Item_Images_Models;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyImageViewHolder> {
    List<Item_Images_Models> itemImagesModels;
    onDeleteClick onDeleteClick;

    public void setOnDeleteClick(ImageAdapter.onDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }

    public ImageAdapter(List<Item_Images_Models> itemImagesModels) {
        this.itemImagesModels = itemImagesModels;
    }

    @NonNull
    @Override
    public MyImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.imageitem,viewGroup,false);

        return new MyImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyImageViewHolder myImageViewHolder, final int pos) {
        final Item_Images_Models item_image = itemImagesModels.get(pos);
        Glide.with(myImageViewHolder.itemView.getContext())
        .load(item_image.getUrl())
         .into(myImageViewHolder.imageView);
        if(onDeleteClick!=null)
        {
            myImageViewHolder.Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClick.OnCLick(item_image);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(this.itemImagesModels ==null) return 0;
        else return this.itemImagesModels.size();
    }
    public void ChangeData( List<Item_Images_Models> itemImagesModel) {
        if (itemImagesModels != null) {
            this.itemImagesModels.clear();
            this.itemImagesModels = itemImagesModel;
            notifyDataSetChanged();
        }
        else {
            this.itemImagesModels = itemImagesModel;
            notifyDataSetChanged();
        }
    }

    public class MyImageViewHolder extends RecyclerView.ViewHolder{
      public   ImageView imageView;
        Button Delete ;
        public MyImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView . findViewById(R.id.image_item_new);
            Delete = itemView . findViewById(R.id.delete_new);
        }

    }
    public interface onDeleteClick
    {
        void OnCLick(Item_Images_Models item_images_models);
    }

}
