package com.example.a2019.ecomerceapp.Admin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2019.ecomerceapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Reports extends Fragment {


    public Reports() {
        // Required empty public constructor
    }

    View view ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reports, container, false);

        return view;
    }

}
