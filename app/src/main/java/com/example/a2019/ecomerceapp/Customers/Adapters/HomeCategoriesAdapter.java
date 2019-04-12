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
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class HomeCategoriesAdapter extends RecyclerView.Adapter<HomeCategoriesAdapter.ViewHolder> {

    List<CategoryModel> list;
    OnCategoryClickedListener onCategoryClickedListener;

    public HomeCategoriesAdapter(List<CategoryModel> list) {
        this.list = list;
    }

    public void setOnCategoryClickedListener(OnCategoryClickedListener onCategoryClickedListener) {
        this.onCategoryClickedListener = onCategoryClickedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_row,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int pos) {
        final CategoryModel model=list.get(viewHolder.getAdapterPosition());
        viewHolder.name.setText(model.getName());
        Glide.with(viewHolder.itemView)
                .load(model.getImageUri()).into(viewHolder.imageView);

        if (onCategoryClickedListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCategoryClickedListener.onItemClicked(viewHolder.getAdapterPosition(),model);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (list==null)return 0;
        return list.size();
    }

    public void ChangeData(List<CategoryModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_cat);
            imageView=itemView.findViewById(R.id.image_cat);
        }
    }

    public interface OnCategoryClickedListener{
        void onItemClicked(int pos,CategoryModel model);
    }

}
