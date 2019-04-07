package com.example.a2019.ecomerceapp.Admin.ViewModel;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Categorybranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.CategoryImageBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
public class AddCategoryVm extends AndroidViewModel {

    MutableLiveData <String> ShowThisMessage;
    MutableLiveData <Boolean> HideBrogressBar;
    MutableLiveData <Boolean> OpenPanelActivity;

    public AddCategoryVm(@NonNull Application application) {
        super(application);
        ShowThisMessage = new MutableLiveData<>();
        HideBrogressBar = new MutableLiveData<>();
        OpenPanelActivity = new MutableLiveData<>();
    }

    public MutableLiveData<String> getShowThisMessage() {
        return ShowThisMessage;
    }

    public MutableLiveData<Boolean> getHideBrogressBar() {
        return HideBrogressBar;
    }

    public MutableLiveData<Boolean> getOpenPanelActivity() {
        return OpenPanelActivity;
    }

     OnSuccessListener MySuccessListenerForFireBaseDB = new OnSuccessListener() {
         @Override
         public void onSuccess(Object o) {
             HideBrogressBar.postValue(true);
         }
     };
     OnFailureListener MyFailureListenerForFireBaseDB = new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             ShowThisMessage.postValue(" can not Insert the new Category please Cheek your Internet Connection");
         }
     };
     OnSuccessListener MySuccessListenerForFireBaseSC = new OnSuccessListener() {
         @Override
         public void onSuccess(Object o) {
              //
             //

             /// **************************
             //****************************************************************8
             //////  Here i want to Acces Room Database And Insert new category
             ////////////////
             //////////////////////
         }
     };
     OnFailureListener MyFailureListenerForFireBaseSC = new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             ShowThisMessage.postValue("can not Insert the new Category Image please Cheek your Internet Connection");
         }
     };
   public void InsertNewCategory(String name, String id, String ImageUri,String Description)
   {

       CategoryModel categoryModel = new CategoryModel(name,ImageUri,id,Description);
       AddCategoryToFireBaseDB(categoryModel);
       HideBrogressBar.postValue(true);
          return;
   }
   public class AddCategory extends Thread
   {
       CategoryModel categoryModel;
       public AddCategory ( CategoryModel categoryModel)
       {
           this.categoryModel = categoryModel;
       }
       @Override
       public void run() {
           super.run();
           MyDatabase.getInstance().categoryDao().AddCategory(categoryModel);
           OpenPanelActivity.postValue(true);
       }
   }
     public void AddCategoryToFireBaseDB(CategoryModel categoryModel)
   {
      Categorybranches.AddCategory(categoryModel,MySuccessListenerForFireBaseDB,MyFailureListenerForFireBaseDB);

   }
    public void AddCategoryToFireBaseSC(CategoryModel categoryModel)
    {
     CategoryImageBranches.AddCategoryImage(categoryModel,MySuccessListenerForFireBaseSC,MyFailureListenerForFireBaseSC);
    }
    public void AddCategoryToRoomDb(CategoryModel categoryModel)
    {
        AddCategory addCategory = new AddCategory(categoryModel);
        addCategory.start();
    }


}
