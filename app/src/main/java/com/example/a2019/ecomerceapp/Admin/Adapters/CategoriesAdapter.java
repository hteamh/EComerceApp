package com.example.a2019.ecomerceapp.Admin.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

  private   List<CategoryModel> list;
   private OnCategoreyClickedListener onCategoreyClickedListener;
  private   OnCategoreyEditListener onCategoreyEditListener;
  private   OnCategoreyDeleteListener onCategoreyDeleteListener;

    public void setOnCategoreyDeleteListener(OnCategoreyDeleteListener onCategoreyDeleteListener) {
        this.onCategoreyDeleteListener = onCategoreyDeleteListener;
    }

    public CategoriesAdapter(List<CategoryModel> list) {
        this.list = list;
    }

    public void setOnCategoreyEditListener(OnCategoreyEditListener onCategoreyEditListener) {
        this.onCategoreyEditListener = onCategoreyEditListener;
    }

    public void setOnCategoreyClickedListener(OnCategoreyClickedListener onCategoreyClickedListener) {
        this.onCategoreyClickedListener = onCategoreyClickedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int pos) {
        final CategoryModel model = list.get(pos);
        viewHolder.text_item.setText(model.getName());
        viewHolder.price.setText(model.getDescription());
        Glide.with(viewHolder.itemView)
                .load(model.getImageUri()).into(viewHolder.image_item);

        if (onCategoreyClickedListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCategoreyClickedListener.onItemClicked(pos,model);
                }
            });
        }
        if(onCategoreyDeleteListener!=null)
        {
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCategoreyDeleteListener.onItemDelete(pos,model);
                }
            });
        }

        if (onCategoreyEditListener!=null){
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCategoreyEditListener.onItemEdit(pos,model);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_item;
        TextView text_item, price;
        Button edit;
        Button delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_item = itemView.findViewById(R.id.image_item);
            text_item = itemView.findViewById(R.id.name_item);
            price = itemView.findViewById(R.id.description);
            edit=itemView.findViewById(R.id.editButton);
            delete = itemView.findViewById(R.id.delete);
        }
    }//,,,,

    public interface OnCategoreyClickedListener{
        void onItemClicked(int pos,CategoryModel model);
    }

    public interface OnCategoreyEditListener{
        void onItemEdit(int pos,CategoryModel model);
    }
    public interface OnCategoreyDeleteListener{
        void onItemDelete(int pos,CategoryModel model);
    }


}
