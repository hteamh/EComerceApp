package com.example.a2019.ecomerceapp.Customers.Activities;

import android.app.DownloadManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Activiteis.AdminPanel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.Customers.Fragments.CategoryHomeFragment;
import com.example.a2019.ecomerceapp.Customers.Models.RoomModel;
import com.example.a2019.ecomerceapp.Customers.ViewModel.HomeViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.RoomBranch;
import com.example.a2019.ecomerceapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static List<ItemModel> itemModels;
    HomeViewModel homeViewModel;
    public  static UserModel userModel;
    public  static RoomModel roomModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       itemModels=new ArrayList<>();
        loadFragment();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadFragment() {

        Fragment fragment = new CategoryHomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.homeContainerFragment, fragment)
                .commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Basket) {
            startActivity(new Intent(Home.this,BasktActivity.class));

            return true;
        }else if (id==R.id.chate){
                CheekRegister();
        }

        return super.onOptionsItemSelected(item);
    }

    private void CheekRegister() {
        ThreadGetUser threadGetUser = new ThreadGetUser(new InUserGet() {
            @Override
            public void GetUser(final UserModel userModel) {
                if(userModel==null)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Home.this, Google_Email.class));

                        }
                    });
                }
                else
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Home.userModel=userModel;
                            roomModel = new RoomModel(userModel,userModel.getUid());
                            startActivity(new Intent(Home.this,ChatActivity.class));

                        }
                    });
                }
            }
        });
        threadGetUser.start();

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            startActivity(new Intent(this,Home.class));
            finish();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void admin(View view) {
        startActivity(new Intent(this,AdminPanel.class));
    }
    public class ThreadGetUser extends Thread{
        InUserGet inUserGet;

        private   ThreadGetUser(InUserGet inUserGet) {
            this.inUserGet = inUserGet;
        }

        @Override
        public void run() {
            super.run();
        final List<UserModel> AllUser;
            AllUser =MyDatabase.getInstance().userDao().GetAllUser();
   if (AllUser.size()>0)
   {
           inUserGet.GetUser(AllUser.get(0));
   }
   else
   {
       inUserGet.GetUser(null);
   }


        }
    }
    private interface InUserGet
    {
        void GetUser(UserModel userModel);
    }
}
