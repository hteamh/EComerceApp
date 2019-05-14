package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.a2019.ecomerceapp.Admin.Adapters.CommoditiesAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.CommoditiesVm;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class Commodities extends BaseActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CommoditiesAdapter adapter;
    CommoditiesVm vm;
    public static ItemModel comodityWeWantEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodities);
        vm= ViewModelProviders.of(this).get(CommoditiesVm.class);

        recyclerView=findViewById(R.id.commodities_RecycleView);
        layoutManager=new LinearLayoutManager(this);
        intiAdapter();
        vm.setData();
        observe();

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Commodities.this,Add_Commodity.class));

            }
        });
    }

    private void observe() {
        vm.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                showMessage("error",s,"yes");

            }
        });

        vm.getListMutableLiveData().observe(this, new Observer<List<ItemModel>>() {
            @Override
            public void onChanged(@Nullable List<ItemModel> list) {
               adapter.ChangeData(list);
                AdapterListner();

            }
        });
    }

    public void AdapterListner()
    {

        adapter.setOnComodityEditListener(new CommoditiesAdapter.OnComodityEditListener() {
            @Override
            public void onItemEdit(int pos, ItemModel model) {
                comodityWeWantEdit=model;
                startActivity(new Intent(Commodities.this,EditComodity.class));

            }
        });
        adapter.setOnComodityDeleteListener(new CommoditiesAdapter.OnComodityDeleteListener() {
            @Override
            public void onItemEdit(int pos, final ItemModel model) {
                showConfirmationMessage(R.string.Error,
                        R.string.You_Will_Be_Remove_Item,
                        R.string.Yes,
                        new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                vm.Delete(model);
                                finish();
                                startActivity(new Intent(Commodities.this,Commodities.class));
                            }
                        });
            }
        });
        adapter.setOnItemClickListener(new CommoditiesAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(ItemModel itemModel) {
              Add_Item_Image_Activity.itemModel=itemModel;
              startActivity(new Intent(Commodities.this,Add_Item_Image_Activity.class));
            }
        });
    }
    public void intiAdapter()
    {
        adapter=new CommoditiesAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        vm.setData();
        observe();
    }
}
