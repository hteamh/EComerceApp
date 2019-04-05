package com.example.a2019.ecomerceapp.Admin.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2019.ecomerceapp.Admin.Activiteis.AddCategory;
import com.example.a2019.ecomerceapp.R;


public class Categories extends Fragment {

    FloatingActionButton button;


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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddCategory.class));
            }
        });

        return view;
    }



}
