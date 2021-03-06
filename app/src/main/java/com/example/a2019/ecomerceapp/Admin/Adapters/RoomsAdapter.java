package com.example.a2019.ecomerceapp.Admin.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a2019.ecomerceapp.Customers.Models.RoomModel;
import com.example.a2019.ecomerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {

    List<RoomModel>list;
    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public RoomsAdapter(List<RoomModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.room_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {

        final RoomModel model=list.get(pos);
        viewHolder.name.setText(model.getRoomname());
        if(onItemClick!=null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.OnItemClick(model);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if (list==null)return 0;
        return list.size();
    }

    public void ChangeData(List<RoomModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }
    public void AddRoom(RoomModel roomModel)
    {
        if(this.list==null)
        {
            list=new ArrayList<>();
            list.add(roomModel);
        }
        else
        {
            list.add(roomModel);
        }
        this.notifyItemInserted(list.size()-1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.roomName);
        }
    }
    public interface OnItemClick{
        void OnItemClick(RoomModel roomModel);
    }


}
