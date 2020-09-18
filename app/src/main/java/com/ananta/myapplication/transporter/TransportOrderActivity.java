package com.ananta.myapplication.transporter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ananta.myapplication.R;
import com.ananta.myapplication.manufacturer.ManuCurrentOrderFragment;
import com.ananta.myapplication.manufacturer.ManuPastOrderFragment;
import com.ananta.myapplication.manufacturer.ManuTabAdapter;
import com.google.android.material.tabs.TabLayout;

public class TransportOrderActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TransporterTabAdapter adapter;
    private ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_order);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TransporterTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new TransCurrentOrderFragment(), "CURRENT");
        adapter.addFragment(new TransPastOrderFragment(), "PAST");
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
