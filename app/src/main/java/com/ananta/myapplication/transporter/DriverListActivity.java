package com.ananta.myapplication.transporter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.DriverListAdapter;
import com.ananta.myapplication.manufacturer.ManufacturerAddProducts;
import com.ananta.myapplication.manufacturer.ManufacturerProduct;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import impl.DriverListPresenterImpl;
import network.DriverListData;
import presenter.DriverListPresenter;
import util.AlaBricks;
import view.DriverListView;

public class DriverListActivity extends AppCompatActivity implements DriverListView {

    private ImageView imgBack,imgAddProduct;
    private ListView lstProducts;
    DriverListPresenter driverListPresenter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_drivers);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        driverListPresenter = new DriverListPresenterImpl(this);

        imgBack = (ImageView)findViewById(R.id.imgBack);
        imgAddProduct = (ImageView)findViewById(R.id.imgAddProduct);
        lstProducts = (ListView)findViewById(R.id.lstProducts);

        imgAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverListActivity.this, AddDriverActivity.class);
                startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        driverListPresenter.listDrivers(sharedPreferences.getString(AlaBricks.sharedUserId,""));
        super.onResume();
    }

    @Override
    public void onSuccessDriverList(ArrayList<DriverListData> driverListData) {
        if(driverListData.size()>0)
        {
            lstProducts.setAdapter(new DriverListAdapter(DriverListActivity.this,driverListData));
        }
    }

    @Override
    public void onFailedDriverList() {

    }
}
