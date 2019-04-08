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
import com.google.firebase.database.connection.util.RetryHelper;

public class AddCategoryVm extends AndroidViewModel {
    private static CategoryModel MyCategoryItem;
    MutableLiveData <String> ShowThisMessage;
    MutableLiveData <Boolean> HideBrogressBar;
    MutableLiveData <Boolean> OpenPanelActivity;

    public AddCategoryVm(@NonNull Application application) {
        super(application);
        ShowThisMessage = new MutableLiveData<>();
        HideBrogressBar = new MutableLiveData<>();
        OpenPanelActivity = new MutableLiveData<>();
    }
           ///      Get               ///
    public MutableLiveData<String> getShowThisMessage() {
        return ShowThisMessage;
    }
    public MutableLiveData<Boolean> getHideBrogressBar() {
        return HideBrogressBar;
    }
    public MutableLiveData<Boolean> getOpenPanelActivity() {
        return OpenPanelActivity;
    }
   public void InsertNewCategory(String name, String id, String ImageUri,String Description)
   {
       HideBrogressBar.postValue(false);
       MyCategoryItem = new CategoryModel(name,ImageUri,id,Description);
       if(internetIsConnected())
       {
           AddCategoryToFireBaseSC(MyCategoryItem);
           return;
       }
       else
       {
           ShowThisMessage.postValue("No internet Connection");
           return;
       }

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
      Categorybranches.AddCategory(categoryModel,new OnSuccessListener() {
          @Override
          public void onSuccess(Object o) {
              AddCategoryToRoomDb(MyCategoryItem);
          }
      },new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              HideBrogressBar.postValue(true);
              ShowThisMessage.postValue(e.getMessage());
          }
      });

   }
    public void AddCategoryToFireBaseSC(CategoryModel categoryModel)
    {
     CategoryImageBranches.AddCategoryImage(categoryModel, new OnSuccessListener() {
         @Override
         public void onSuccess(Object o) {
             CategoryImageBranches.GetUri(MyCategoryItem, new CategoryImageBranches.GetUriListner() {
                 @Override
                 public void MyUri(String Uri) {
                     if(Uri == null)
                     {
                         ShowThisMessage.postValue("Uri Is Success  Upload");
                     }
                     else
                     {
                         AddCategoryToFireBaseDB( new CategoryModel(MyCategoryItem.getName(),Uri,MyCategoryItem.getId(),MyCategoryItem.getDescription()));
                     }
                 }
             });

         }
     }, new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {

             HideBrogressBar.postValue(true);
             ShowThisMessage.postValue(e.getMessage());

         }
     });
    }
    public void AddCategoryToRoomDb(CategoryModel categoryModel)
    {
        AddCategory addCategory = new AddCategory(categoryModel);
        addCategory.start();
        HideBrogressBar.postValue(true);
        OpenPanelActivity.postValue(true);

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
