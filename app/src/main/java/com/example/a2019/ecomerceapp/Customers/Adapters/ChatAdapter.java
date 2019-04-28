package com.example.a2019.ecomerceapp.Customers.Adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a2019.ecomerceapp.Customers.Activities.ChatActivity;
import com.example.a2019.ecomerceapp.Customers.Models.MessageModel;
import com.example.a2019.ecomerceapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.List;

import static com.example.a2019.ecomerceapp.Customers.Activities.ChatActivity.MyGooGleMap;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MainVH> {
     static List<MessageModel> MyMessage;
      String Sender_Name;
     int Message_Text_Out = 1;
     int Message_Image_Out=2;
     int Message_Map_out=3;
     int Message_text_in=4;
     int Message_image_in=5;
     int Message_Map_in=6;

    public ChatAdapter(List<MessageModel> myMessage, String sender_Name) {
        MyMessage = myMessage;
        Sender_Name = sender_Name;
    }

    @Override
    public int getItemViewType(int position) {
        if(MyMessage.get(position).getSender_name()==Sender_Name)
        {
            // Out Message
            if(MyMessage.get(position).getImageUri()!=null)
            {
                return Message_Image_Out;
            }
            else if(MyMessage.get(position).getLatitude()!=null)
            {
              return   Message_Map_out;
            }
            else if(MyMessage.get(position).getText()!=null)
            {
                return Message_Text_Out;
            }
        }
        else
        {
            //  in Message
            if(MyMessage.get(position).getImageUri()!=null)
            {
                return Message_image_in;

            }
            else if(MyMessage.get(position).getLatitude()!=null)
            {
                return   Message_Map_in;
            }
            else if(MyMessage.get(position).getText()!=null)
            {
                return Message_text_in;
            }
        }
        return 0;
    }@NonNull
    @Override
    public MainVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int TypeView) {
        if(TypeView==Message_Text_Out)
        {
            View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.out_message_text,viewGroup,false);
            return new out_Message_Text_VH(view);
        }
        else if(TypeView==Message_Image_Out)
        {
            View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.out_message_image,
                    viewGroup,false);
            return new outMessageImageVh(view);
        }
        else if(TypeView==Message_Map_out)
        {
            View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.out_message_map,
                    viewGroup,false);
            return new Out_message_MapVH(view);
        }
        else if(TypeView == Message_text_in)
        {
            View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.in_message_text,
                    viewGroup,false);
            return new In_Message_TextVH(view);
        }
        else if(TypeView== Message_image_in)
        {
            View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.in_message_image,
                    viewGroup,false);
            return new In_Message_Image_VH(view);
        }
        else if(TypeView == Message_Map_in)
        {
            View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.in_message_map,
                    viewGroup,false);
            return new In_Message_MapVH(view);
        }
        return null;
    }
    @Override public void onBindViewHolder(@NonNull MainVH mainVH, int pos) {
        MessageModel message = MyMessage.get(pos);
        int Type = getItemViewType(pos);
        if(Type ==Message_Text_Out)
        {
            ((out_Message_Text_VH) mainVH).Data.setText(message.getData());
            ((out_Message_Text_VH) mainVH).message.setText(message.getText());
        }
       else if(Type == Message_Image_Out)
        {
            ((outMessageImageVh) mainVH).Data.setText(message.getData());
            ((outMessageImageVh) mainVH).Image.setImageURI(Uri.parse( message.getImageUri()));
        }
        else if(Type == Message_Map_out)
        {
            ((Out_message_MapVH) mainVH).Data.setText(message.getData());
            ((Out_message_MapVH) mainVH).mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    MyGooGleMap=googleMap;
                }
            });
            ((Out_message_MapVH) mainVH).Data.setText(message.getData());

        }
        else if(Type== Message_text_in)
        {
            ((In_Message_TextVH) mainVH).Message.setText(message.getText());
            ((In_Message_TextVH) mainVH).Data.setText(message.getData());
            ((In_Message_TextVH) mainVH).Sender_Name.setText(message.getSender_name());
        }
        else if(Type== Message_image_in)
        {
            ((In_Message_Image_VH) mainVH).imageView.setImageURI(Uri.parse(message.getImageUri()));
            ((In_Message_Image_VH) mainVH).Sender_name.setText(message.getSender_name());
            ((In_Message_Image_VH) mainVH).Data.setText(message.getData());
        }
        else if(Type==Message_Map_in )
        {
            ((In_Message_MapVH) mainVH).mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    MyGooGleMap = googleMap;
                }
            });
            ((In_Message_MapVH) mainVH).Data.setText(message.getData());
        }
    }

    @Override
    public int getItemCount() {
      if(MyMessage==null)  return 0;
      else  return MyMessage.size();
    }
    class MainVH extends RecyclerView.ViewHolder{

         MainVH(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class out_Message_Text_VH extends MainVH {
        TextView message , Data;
        public out_Message_Text_VH(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.Text_Out_Message);
            Data = itemView.findViewById(R.id.Text_Out_Message_Data);
        }
    }
    public class outMessageImageVh extends MainVH {
        ImageView Image;
        TextView Data;
        public outMessageImageVh(@NonNull View itemView) {
            super(itemView);
            Image=  itemView.findViewById(R.id.Image_Out_Message);
            Data = itemView.findViewById(R.id.Image_Out_MessageData);

        }
    }
    public class Out_message_MapVH extends MainVH {
            MapView mapView;
            TextView Data;
        Out_message_MapVH(@NonNull View itemView) {
            super(itemView);
            mapView = itemView.findViewById(R.id.Map_Out_Message);
            mapView = itemView.findViewById(R.id.Map_Out_MessageData);
        }
    }
    public class In_Message_TextVH extends MainVH{
        TextView Message,Data,Sender_Name;
        In_Message_TextVH(@NonNull View itemView) {
            super(itemView);
            Message = itemView.findViewById(R.id.Text_In_Message);
            Data= itemView.findViewById(R.id.Text_In_Message_Data);
            Sender_Name = itemView.findViewById(R.id.Sender_Name);
        }
    }
    public class In_Message_MapVH extends MainVH {
          TextView name,Data;
          MapView mapView;
        In_Message_MapVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Sender_Name_map);
            mapView = itemView.findViewById(R.id.Map_In_Message);
            Data = itemView.findViewById(R.id.Map_In_Message_Data);
        }
    }
    public class In_Message_Image_VH extends MainVH {
         TextView Sender_name , Data;
         ImageView imageView;
        In_Message_Image_VH(@NonNull View itemView) {
            super(itemView);
            Sender_name = itemView.findViewById(R.id.Sender_Name_Image);
            Data = itemView.findViewById(R.id.Image_In_Message_Data);
            imageView = itemView.findViewById(R.id.Image_In_Message);

        }
    }

}