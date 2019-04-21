package com.example.a2019.ecomerceapp.Admin.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.a2019.ecomerceapp.Admin.Models.OrdersCountainer;
import com.example.a2019.ecomerceapp.R;
import java.util.List;

public class OrdarAdapter extends RecyclerView.Adapter<OrdarAdapter.MyViewHolder> {
   private List<OrdersCountainer> ordersCountainers;
  private   orderAdapter orderAdapter ;
    OnSendClickListner onSendClickListner;


    public OrdarAdapter(List<OrdersCountainer> ordersCountainers ,orderAdapter orderAdapter ) {
        this.ordersCountainers = ordersCountainers;
        this.orderAdapter = orderAdapter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_order_item,viewGroup,false);
         MyViewHolder MyViewH=  new MyViewHolder(view);
        MyViewH.innerRecyclerView.setLayoutManager(new LinearLayoutManager(viewGroup.getContext()));
        MyViewH.innerRecyclerView.setAdapter(orderAdapter);
        return MyViewH;
    }

    public void setOnSendClickListner(OnSendClickListner onSendClickListner) {
        this.onSendClickListner = onSendClickListner;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final OrdersCountainer ordersCountainer = this.ordersCountainers.get(i);
        myViewHolder.Date.setText("Date :" + ordersCountainer.getDate());
        myViewHolder.Adrees.setText("Adrees:" + ordersCountainer.getAdrees());
        myViewHolder.price.setText("T price"+ ordersCountainer.getTotalPrice());
        myViewHolder.name.setText("name :" + ordersCountainer.getName());
        myViewHolder.phone.setText("phone  :"+ordersCountainer.getPhone());
         orderAdapter.ChangeData(ordersCountainer.getItemModels());
        if(this.onSendClickListner!=null)
        {
            myViewHolder.Send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSendClickListner.OnSendClick(ordersCountainer,i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(this.ordersCountainers == null ||this.ordersCountainers.size()<1 ) return 0;
        else
            return this.ordersCountainers.size();
    }


    public void ChangeData(List<OrdersCountainer> ordersCountainers) {
        this.ordersCountainers = ordersCountainers;
        this.notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView name,price,phone,Adrees,Date;
         RecyclerView innerRecyclerView;
        Button Send;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.newCustomerName);
            price = itemView.findViewById(R.id.newTotalprice);
            phone=itemView.findViewById(R.id.newCustomerPhone);
            Adrees=itemView.findViewById(R.id.newCustomerAdrees);
            Date= itemView.findViewById(R.id.newDate);
            Send = itemView.findViewById(R.id.newButton);
            innerRecyclerView = itemView.findViewById(R.id.SubRecyclerView);
        }
    }
    public interface OnSendClickListner{
         void OnSendClick(OrdersCountainer ordersCountainer , int Pos);
    }
}
