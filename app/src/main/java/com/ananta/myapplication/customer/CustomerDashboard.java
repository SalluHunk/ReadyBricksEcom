package com.ananta.myapplication.customer;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.CustomGridProducts;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import impl.CustomerListProductPresenterImpl;
import network.CustomerProductData;
import presenter.CustomerListProductPresenter;
import util.AlaBricks;
import view.CustomerListProductView;

public class CustomerDashboard extends AppCompatActivity  {
    private ImageView imgAddProduct;
    private ListView lstProducts;
    BottomNavigationView bottomNavigationView;
    private GridView grid;
    AHBottomNavigation bottomNavigation;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);


         bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Products", R.drawable.product, R.color.colorMain);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Orders", R.drawable.ic_order, R.color.colorMain);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Cart", R.drawable.cart, R.color.colorMain);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Account", R.drawable.account, R.color.colorMain);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
// Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));

// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F44343"));
        bottomNavigation.setInactiveColor(Color.parseColor("#000000"));

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        bottomNavigation.setTranslucentNavigationEnabled(true);

// Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);


// Set current item programmatically
        bottomNavigation.setCurrentItem(0);


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                if(position==0 && !wasSelected)
                {
                    ProdcutFragment prodcutFragment = new ProdcutFragment();
                    replaceFragment(prodcutFragment);
                }
                if(position==1 && !wasSelected)
                {
                    OrderFragment orderFragment = new OrderFragment();
                    replaceFragment(orderFragment);
                }
                if(position==2 && !wasSelected)
                {
                    CartFragment cartFragment =new CartFragment();
                    replaceFragment(cartFragment);
                }
                if(position==3 && !wasSelected)
                {
                    AccountFragment accountFragment = new AccountFragment();
                    replaceFragment(accountFragment);
                }

                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
        ProdcutFragment prodcutFragment = new ProdcutFragment();
        //above part is to determine which fragment is in your frame_container
        replaceFragment(prodcutFragment);



    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        if (AlaBricks.isProductAdded)
        {
            bottomNavigation.setCurrentItem(2);
            AlaBricks.isProductAdded = false;
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
           finish();
            return;
        }
        if(bottomNavigation.getCurrentItem()==0)
        {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
        else
        {
            bottomNavigation.setCurrentItem(0);
        }
    }
}
