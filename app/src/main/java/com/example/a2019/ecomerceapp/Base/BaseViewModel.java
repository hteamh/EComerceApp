package com.example.a2019.ecomerceapp.Base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class BaseViewModel extends AndroidViewModel {
  private   MutableLiveData <String> Message;
   private MutableLiveData<Boolean> HideProgress;

    public MutableLiveData<String> getMessage() {
        return Message;
    }

    public MutableLiveData<Boolean> getHideProgress() {
        return HideProgress;
    }

    public BaseViewModel(@NonNull Application application) {
        super(application);
        Message = new MutableLiveData<>();
        HideProgress= new MutableLiveData<>();

    }
    public void SetMessage(String message)
    {
        Message.postValue(message);
    }
    public void SetHideProgrees(Boolean b)
    {
        this.HideProgress.postValue(b);
    }
    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }


}
