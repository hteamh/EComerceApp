package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.example.a2019.ecomerceapp.Admin.Fragments.Categories;
import com.example.a2019.ecomerceapp.Admin.Fragments.Oreders;
import com.example.a2019.ecomerceapp.Admin.Fragments.Message_Fragment;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.R;

public class AdminPanel extends BaseActivity {

    Fragment fragment = null;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.add_category:
                    fragment = new Categories();

                    break;
                case R.id.orders:
                    fragment = new Oreders();
                    break;
                case R.id.message_fra:
                    fragment = new Message_Fragment();
                    break;
            }

            linkfraggment();
            return true;
        }

    };

    private void linkfraggment() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, fragment).commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        fragment = new Categories();
        linkfraggment();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
