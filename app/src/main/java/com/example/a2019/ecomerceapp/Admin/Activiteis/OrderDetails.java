package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a2019.ecomerceapp.Admin.Adapters.OrderItemAdapter;
import com.example.a2019.ecomerceapp.Admin.Fragments.Oreders;
import com.example.a2019.ecomerceapp.Admin.Models.OrderModel;
import com.example.a2019.ecomerceapp.Admin.Models.Order_Commedity;
import com.example.a2019.ecomerceapp.Admin.ViewModel.Order_details_Vm;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class OrderDetails extends BaseActivity {
   public static OrderModel MyOrder;
   RecyclerView myRecycler;
   TextView name,Data,Adrees,Phone,TotalPrice;
   Button Send , Ignore;
   RecyclerView.LayoutManager layoutManager;
   OrderItemAdapter adapter;
   Order_details_Vm MViewModel;
   public  static boolean DoRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Init();
        SetText();
        ButtonListener();
        MViewModel.GetAllOrdersItemByOrderId(MyOrder.getId());
          Observe();
    }

    private void ButtonListener() {
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MViewModel.SendToSalesBasket(MyOrder);
            }
        });
        Ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              MViewModel.IgnoreThisOrder(MyOrder);
            }
        });
    }

    private void SetText() {
        name.setText("Name is:"+MyOrder.getName());
        Data.setText("Data is:"+MyOrder.getDate());
        Phone.setText("Phone is:"+MyOrder.getPhone());
        Adrees.setText("Adrees is : "+MyOrder.getAdrees());
        TotalPrice.setText("TotalPrice is : "+MyOrder.getTotalPrice());
    }

    private void Init() {
        DoRefresh = false;
        myRecycler = findViewById(R.id.Order_Item_Recycler);
        name =  findViewById(R.id.Customer_name);
        Data=findViewById(R.id.Order_Data);
        Adrees =findViewById(R.id.Customer_adrees);
        Phone=findViewById(R.id.Customer_phone);
        TotalPrice=findViewById(R.id.Customer_TotalPrice);
        layoutManager = new LinearLayoutManager(this);
        adapter = new OrderItemAdapter(null);
        myRecycler.setAdapter(adapter);
        myRecycler.setLayoutManager(layoutManager);
        Send = findViewById(R.id.Send_To_Sales);
        Ignore = findViewById(R.id.Ignore);
        MViewModel = ViewModelProviders.of(this).get(Order_details_Vm.class);
    }
    private void Observe() {
    MViewModel.getMyOrderComedity().observe(this, new Observer<List<Order_Commedity>>() {
        @Override
        public void onChanged(@Nullable List<Order_Commedity> order_commedities) {
            adapter.ChangeData(order_commedities);
        }
    });
    MViewModel.getReturn().observe(this, new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            if(aBoolean !=null && aBoolean)
            {
                finish();
                DoRefresh = true;
            }
        }
    });
    MViewModel.getMessage().observe(this, new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            showMessage("Message Error",s,"Yes");
        }
    });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        DoRefresh =true;
    }
}
