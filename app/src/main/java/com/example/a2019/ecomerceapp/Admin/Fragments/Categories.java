package com.example.a2019.ecomerceapp.Admin.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.a2019.ecomerceapp.Admin.Activiteis.AddCategory;
import com.example.a2019.ecomerceapp.Admin.Activiteis.AdminPanel;
import com.example.a2019.ecomerceapp.Admin.Activiteis.Commodities;
import com.example.a2019.ecomerceapp.Admin.Activiteis.EditCategory;
import com.example.a2019.ecomerceapp.Admin.Adapters.CategoriesAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.CategoryFragmentVm;
import com.example.a2019.ecomerceapp.Base.BaseFragment;
import com.example.a2019.ecomerceapp.R;
import java.util.List;
public class Categories extends BaseFragment {

    FloatingActionButton button;
    CategoryFragmentVm MyViewModel ;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CategoriesAdapter adapter;
    public  static CategoryModel categoryModeWeWantToUpdate;
    public  static  CategoryModel categoryModeWeWantToSHowHisItem;
    public Categories() {
        // Required empty public constructor
    }

    View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_categories, container, false);
        button=view.findViewById(R.id.add);
        recyclerView=view.findViewById(R.id.category_RecycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        IntiAdapter();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(Categories.this).commit();
                getActivity().finish();
                startActivity(new Intent(getContext(),AddCategory.class));
                getActivity().finish();
            }
        });
        MyViewModel = ViewModelProviders.of(this).get(CategoryFragmentVm.class);
        MyViewModel.SetData();
        Observe();

        return view;
    }
    private void Observe() {
        MyViewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showMessage("error",s,"yes");
            }
        });
        MyViewModel.getMyCategoryItem().observe(this, new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(@Nullable List<CategoryModel> categoryModels) {

                AdapterClickLisner();
                 adapter.ChangeData(categoryModels);
            }
        });
        MyViewModel.getHideProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean!=null)
                {
                    if(aBoolean)
                    {
                      hideProgressBar();
                    }
                    else
                    {
                      showProgressBar(R.string.Loading) ;
                    }
                }
            }
        });
    }

    public void IntiAdapter() {
        adapter=new CategoriesAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void AdapterClickLisner() {
        adapter.setOnCategoreyEditListener(new CategoriesAdapter.OnCategoreyEditListener() {
            @Override
            public void onItemEdit(int pos, CategoryModel model) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(Categories.this).commit();
                getActivity().finish();
                Intent intent=new Intent(getContext(),EditCategory.class);
                categoryModeWeWantToUpdate=model;
                startActivity(intent);


            }
        });
        adapter.setOnCategoreyClickedListener(new CategoriesAdapter.OnCategoreyClickedListener() {
            @Override
            public void onItemClicked(int pos, CategoryModel MyCategory) {
                categoryModeWeWantToSHowHisItem = MyCategory;
                Intent intent=new Intent(getContext(), Commodities.class);
                startActivity(intent);

            }
        });
        adapter.setOnCategoreyDeleteListener(new CategoriesAdapter.OnCategoreyDeleteListener() {
            @Override
            public void onItemDelete(int pos, final CategoryModel model) {
                showConfirmationMessage(R.string.Error,
                        R.string.You_Will_Be_Remove_All,
                        R.string.Yes,
                        new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                MyViewModel.Delete(model);
                                getActivity().getSupportFragmentManager().beginTransaction().remove(Categories.this).commit();
                              //  getActivity().finish();
                              //  startActivity(new Intent(getActivity(),AdminPanel.class));
                            }
                        });
            }


        });
    }



}
