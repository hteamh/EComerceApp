package com.example.a2019.ecomerceapp.Base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 1/28/2019.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */
public class BaseFragment extends Fragment {
    protected BaseActivity activity;
    BaseViewModel baseViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(BaseActivity) context;
        baseViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);


    }

    public MaterialDialog showMessage(int titleResId, int messageResId, int posResText){
        return activity.showMessage(titleResId,messageResId,posResText);

    }


    public MaterialDialog showConfirmationMessage(int titleResId, int messageResId, int posResText,
                                                  MaterialDialog.SingleButtonCallback onPosAction){
        return activity.showConfirmationMessage(titleResId,messageResId,posResText,onPosAction);

    }
    public MaterialDialog showMessage(String title,String message,String posText){

        return activity.showMessage(title,message,posText);
    }
    public MaterialDialog showProgressBar(int message){

        return activity.showProgressBar(message);
    }
    public void hideProgressBar() {
        activity.hideProgressBar();
    }
}
