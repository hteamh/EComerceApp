package com.example.a2019.ecomerceapp.Admin.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class CommoditiesAdapter extends RecyclerView.Adapter<CommoditiesAdapter.ViewHolder> {

  private   List<ItemModel> list;
  private   OnComodityEditListener onComodityEditListener;
  private   OnComodityDeleteListener onComodityDeleteListener;
  private  OnItemClickListener onItemClickListener ;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CommoditiesAdapter(List<ItemModel> list) {
        this.list = list;
    }

    public void setOnComodityEditListener(OnComodityEditListener onComodityEditListener) {
        this.onComodityEditListener = onComodityEditListener;
    }

    public void setOnComodityDeleteListener(OnComodityDeleteListener onComodityDeleteListener) {
        this.onComodityDeleteListener = onComodityDeleteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.commodity_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int pos) {

        final ItemModel model=list.get(pos);
        viewHolder.name.setText(model.getName());
        viewHolder.price.setText(model.getPrice());

        Glide.with(viewHolder.itemView)
                .load(model.getImageUri()).into(viewHolder.imageView);

        if (onComodityEditListener!=null){
            viewHolder.buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onComodityEditListener.onItemEdit(pos,model);
                }
            });
        }
        if(onComodityDeleteListener!=null)
        {
            viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onComodityDeleteListener.onItemEdit(pos,model);
                }
            });
        }
        if(onItemClickListener!=null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(model);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (list==null)return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price;
        ImageView imageView;
        Button buttonEdit;
        Button buttonDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_comm);
            price=itemView.findViewById(R.id.price_comm);
            imageView=itemView.findViewById(R.id.image_comm);
            buttonEdit=itemView.findViewById(R.id.edit_comm);
            buttonDelete=itemView.findViewById(R.id.delete);
        }
    }

    public interface OnComodityEditListener{
        void onItemEdit(int pos,ItemModel model);
    }
    public interface  OnComodityDeleteListener{
        void onItemEdit(int pos,ItemModel model);
    }
    public void ChangeData(List<ItemModel> list)
    {
        this.list = list;
        this.notifyDataSetChanged();
    }
    public interface OnItemClickListener
    {
        void OnItemClick(ItemModel  itemModel );
    }
}
