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

public class HomeCommoditiesAdapter extends RecyclerView.Adapter<HomeCommoditiesAdapter.ViewHolder> {

  private   List<ItemModel>list;
   private OnSHopClick onSHopClick;
  private   OnHeart onHeart;
    OnItemClick OnItemClick;

    public void setOnItemClick(HomeCommoditiesAdapter.OnItemClick onItemClick) {
        OnItemClick = onItemClick;
    }

    public void setOnSHopClick(OnSHopClick onSHopClick) {
        this.onSHopClick = onSHopClick;
    }

    public HomeCommoditiesAdapter(List<ItemModel> list) {
        this.list = list;
    }

    public void setOnHeart(OnHeart onHeart) {
        this.onHeart = onHeart;
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
        if(onSHopClick !=null)
        {
            viewHolder.fev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  onSHopClick.OnClick(model);
                }
            });
        }
        if(onHeart !=null)
        {
            viewHolder.Love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onHeart.OnClick(model);
                }
            });

        }
        if(OnItemClick !=null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnItemClick.OnClick(model);
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
        ImageView imageView,fev,Love;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_com);
            name=itemView.findViewById(R.id.name_com);
            price=itemView.findViewById(R.id.price_com);
            fev= itemView.findViewById(R.id.fav);
            Love = itemView.findViewById(R.id.Lovely);
        }
    }
    public interface OnSHopClick {
         void OnClick(ItemModel itemModel);
    }
    public interface OnHeart{
         void OnClick(ItemModel itemModel);
    }
    public interface OnItemClick
    {
        void OnClick(ItemModel itemModel);
    }

}
