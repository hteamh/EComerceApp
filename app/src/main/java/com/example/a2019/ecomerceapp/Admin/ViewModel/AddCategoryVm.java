package com.example.a2019.ecomerceapp.Admin.ViewModel;
import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Categorybranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.CategoryImageBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
public class AddCategoryVm extends BaseViewModel {
    private static CategoryModel MyCategoryItem;

  private   MutableLiveData <Boolean> OpenPanelActivity;

    public AddCategoryVm(@NonNull Application application) {
        super(application);

        OpenPanelActivity = new MutableLiveData<>();
    }
           ///      Get               ///

    public MutableLiveData<Boolean> getOpenPanelActivity() {
        return OpenPanelActivity;
    }
   public void InsertNewCategory(String name, String id, String ImageUri,String Description)
   {
      SetHideProgrees(false);
       MyCategoryItem = new CategoryModel(name,ImageUri,id,Description);
       if(internetIsConnected())
       {
           AddCategoryToFireBaseSC(MyCategoryItem);
       }
       else
       {
           SetMessage("No internet Connection");

       }

   }
   public class AddCategory extends Thread
   {
       CategoryModel categoryModel;
       private AddCategory ( CategoryModel categoryModel)
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
     private void AddCategoryToFireBaseDB(CategoryModel categoryModel)
   {
      Categorybranches.AddCategory(categoryModel,new OnSuccessListener() {
          @Override
          public void onSuccess(Object o) {
              SetHideProgrees(true);
              OpenPanelActivity.postValue(true);
              AddCategoryToRoomDb(MyCategoryItem);
          }
      },new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              SetHideProgrees(true);
              SetMessage(e.getMessage());
          }
      });

   }
    private void AddCategoryToFireBaseSC(CategoryModel categoryModel)
    {
     CategoryImageBranches.AddCategoryImage(categoryModel, new OnSuccessListener() {
         @Override
         public void onSuccess(Object o) {
             CategoryImageBranches.GetUri(MyCategoryItem, new CategoryImageBranches.GetUriListner() {
                 @Override
                 public void MyUri(String Uri) {
                     if(Uri == null)
                     {
                         SetMessage("Uri Is Not  Upload");
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

            SetHideProgrees(true);
             SetMessage(e.getMessage());

         }
     });
    }
    private void AddCategoryToRoomDb(CategoryModel categoryModel)
    {
        AddCategory addCategory = new AddCategory(categoryModel);
        addCategory.start();

    }


}
