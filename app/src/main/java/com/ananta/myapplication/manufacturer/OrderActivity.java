package com.ananta.myapplication.manufacturer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ananta.myapplication.R;
import com.ananta.myapplication.customer.CurrentOrderFragment;
import com.ananta.myapplication.customer.PastOrderFragment;
import com.ananta.myapplication.customer.TabAdapter;
import com.google.android.material.tabs.TabLayout;

public class OrderActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ManuTabAdapter adapter;
    private ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_order);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new ManuTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new ManuCurrentOrderFragment(), "CURRENT");
        adapter.addFragment(new ManuPastOrderFragment(), "PAST");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        imgBack = (ImageView)findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
