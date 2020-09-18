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
import com.ananta.myapplication.adapter.TruckListAdapter;

import java.util.ArrayList;

import impl.TruckListPresenterImpl;
import network.VehicleListData;
import presenter.TruckListPresenter;
import util.AlaBricks;
import view.TruckListView;

public class TruckListActivity extends AppCompatActivity implements TruckListView {

    private ImageView imgBack,imgAddProduct;
    private ListView lstProducts;
    TruckListPresenter truckListPresenter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_drivers);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        truckListPresenter = new TruckListPresenterImpl(this);
        imgBack = (ImageView)findViewById(R.id.imgBack);
        imgAddProduct = (ImageView)findViewById(R.id.imgAddProduct);
        lstProducts = (ListView)findViewById(R.id.lstProducts);

        imgAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TruckListActivity.this, AddTruckActivity.class);
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
        truckListPresenter.listTrucks(sharedPreferences.getString(AlaBricks.sharedUserId,""));
        super.onResume();
    }

    @Override
    public void onSuccessTruckList(ArrayList<VehicleListData> vehicleListData) {
        lstProducts.setAdapter(new TruckListAdapter(TruckListActivity.this,vehicleListData));
    }

    @Override
    public void onFailedTruckList() {

    }
}
