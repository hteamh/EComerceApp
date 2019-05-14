package com.example.a2019.ecomerceapp.Admin.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a2019.ecomerceapp.Admin.Models.OrderModel;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<OrderModel> MyOrdersNames;
    OnOrderClick onOrderClick;

    public void setOnOrderClick(OnOrderClick onOrderClick) {
        this.onOrderClick = onOrderClick;
    }

    public OrderAdapter(List<OrderModel> myOrdersNames) {
        MyOrdersNames = myOrdersNames;
    }
    public void ChangeData(List<OrderModel> myOrdersNames)
    {
        if(MyOrdersNames !=null)
        {
            MyOrdersNames.clear();

        }
        else
        {
            this.MyOrdersNames=myOrdersNames;
            this.notifyDataSetChanged();
        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
     String order_name = this.MyOrdersNames.get(i).getName();
     viewHolder.Order_name.setText(order_name);
     if(onOrderClick !=null)
     {
         viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onOrderClick.InorderClick(MyOrdersNames.get(i));
             }
         });
     }
    }

    @Override
    public int getItemCount() {
        if(this.MyOrdersNames==null)
        return 0;
        else
            return this.MyOrdersNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
   TextView Order_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Order_name = itemView.findViewById(R.id.Order_name);
        }
    }
    public interface OnOrderClick
    {
        void InorderClick(OrderModel order);
    }
}
