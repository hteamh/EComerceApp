package com.example.a2019.ecomerceapp.Customers.Adapters;

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

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.MyViewHolder> {
    List<ItemModel> Myitem;

    onDeleteClick onDeleteClick;


    public void setOnDeleteClick(BasketAdapter.onDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }



    public BasketAdapter(List<ItemModel> myitem) {
        Myitem = myitem;
    }
    public void ChangeData(List<ItemModel> myitem)
    {
        this.Myitem=myitem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_in_basket,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
           final ItemModel myitem= Myitem.get(position);
           myViewHolder.price.setText(myitem.getPrice());
           myViewHolder.itemname.setText(myitem.getName());
           myViewHolder.Count.setText(myitem.getCount());
        Glide.with(myViewHolder.itemView)
                .load(myitem.getImageUri())
                .into(myViewHolder.imageItem);


          myViewHolder.Min.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String Count = Integer.toString(Integer.parseInt(myitem.getCount())-1);
                  myitem.setCount(Count);
                  myViewHolder.Count.setText(myitem.getCount());

              }
          });



          myViewHolder.Plus.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String Count = Integer.toString(Integer.parseInt(myitem.getCount())+1);
                  myitem.setCount(Count);
                  myViewHolder.Count.setText(myitem.getCount());

              }
          });

        if (onDeleteClick!=null)
        {
            myViewHolder.Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClick.OnClick(position,myitem);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(Myitem==null)
        {
            return 0;
        }
        else
        {
            return Myitem.size();
        }
    }

    public interface onDeleteClick
    {
        void OnClick(int pos,ItemModel itemModel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
{

    ImageView imageItem,Plus,Min,Delete;
    TextView  itemname,price,Count;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageItem = itemView.findViewById(R.id.imageW);
        Plus = itemView.findViewById(R.id.plus);
        Min= itemView.findViewById(R.id.min);
        itemname = itemView.findViewById(R.id.namew);
        price= itemView.findViewById(R.id.pricew);
        Count = itemView.findViewById(R.id.counterw);
        Delete = itemView.findViewById(R.id.deletew);
    }
}
}
