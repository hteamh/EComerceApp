package com.example.a2019.ecomerceapp.Customers.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class HomeCommoditiesAdapter extends RecyclerView.Adapter<HomeCommoditiesAdapter.ViewHolder> {

    List<ItemModel>list;
    OnHeartClick onHeartClick;

    public void setOnHeartClick(OnHeartClick onHeartClick) {
        this.onHeartClick = onHeartClick;
    }

    public HomeCommoditiesAdapter(List<ItemModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.commodity_row,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        final ItemModel model=list.get(pos);
        viewHolder.name.setText(model.getName());
        viewHolder.price.setText(model.getPrice());

        Glide.with(viewHolder.itemView)
                .load(model.getImageUri()).into(viewHolder.imageView);
        if(onHeartClick !=null)
        {
            viewHolder.fev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  onHeartClick.OnClick(model);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (list==null)return 0;
        return list.size();
    }

    public void ChangeData(List<ItemModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price;
        ImageView imageView,fev;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_com);
            name=itemView.findViewById(R.id.name_com);
            price=itemView.findViewById(R.id.price_com);
            fev= itemView.findViewById(R.id.fav);
        }
    }
    public interface OnHeartClick{
        public void OnClick(ItemModel itemModel);
    }
}
