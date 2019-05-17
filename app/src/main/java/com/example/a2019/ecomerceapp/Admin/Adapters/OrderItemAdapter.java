package com.example.a2019.ecomerceapp.Admin.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.Order_Commedity;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    List<Order_Commedity> Order_Commedity;

    public OrderItemAdapter(List<Order_Commedity> Order_Commedity) {
        this.Order_Commedity = Order_Commedity;
    }

    public void ChangeData(List<Order_Commedity> Order_Commedity) {
        this.Order_Commedity = Order_Commedity;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_in_the_order, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        Order_Commedity order_commedity = this.Order_Commedity.get(pos);
        viewHolder.name.setText(order_commedity.getItem_name());
        viewHolder.count.setText(order_commedity.getCounter());
        Glide.with(viewHolder.itemView)
                .load(order_commedity.getItem_Image()).into(viewHolder.imageView);
        viewHolder.price.setText(order_commedity.getItem_price());
    }

    @Override
    public int getItemCount() {
        if (this.Order_Commedity == null)
            return 0;
        else
            return this.Order_Commedity.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView count, price, name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Image_In_order_item);
            count = itemView.findViewById(R.id.count_item);
            price = itemView.findViewById(R.id.price_in_order_item);
            name = itemView.findViewById(R.id.name_item_in_the_order);
        }
    }

}
