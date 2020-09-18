package com.ananta.myapplication.manufacturer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.InventoryListAdapter;
import com.tfb.fbtoast.FBToast;

import java.util.ArrayList;

import impl.ProductListPresenterImpl;
import network.ProductData;
import presenter.ManufacturerListPresenter;
import util.AlaBricks;
import view.ManufacturerListView;

public class InventoryActivity extends AppCompatActivity implements ManufacturerListView {

    private ImageView imgAddProduct;
    ManufacturerListPresenter manufacturerListPresenter;
    SharedPreferences sharedPreferences;
    private ListView lstInventory;
    ProgressDialog progressDialog;
    private ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);


        progressDialog = new ProgressDialog(InventoryActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        imgAddProduct = (ImageView)findViewById(R.id.imgAddProduct);
        imgBack = (ImageView)findViewById(R.id.imgBack);


        lstInventory = (ListView)findViewById(R.id.lstInventory);
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        imgAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InventoryActivity.this,AddInventory.class);
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
    public void onSuccessManufacturerList(ArrayList<ProductData> productData) {
        progressDialog.dismiss();
        InventoryListAdapter inventoryListAdapter = new InventoryListAdapter(InventoryActivity.this,productData);
        lstInventory.setAdapter(inventoryListAdapter);
    }

    @Override
    public void onFailedManufacturerList() {
        progressDialog.dismiss();
        FBToast.errorToast(InventoryActivity.this,"No Products Found",FBToast.LENGTH_LONG);
    }

    @Override
    protected void onResume() {
        progressDialog.show();
        manufacturerListPresenter = new ProductListPresenterImpl(this);
        manufacturerListPresenter.onLoadProductList(sharedPreferences.getString(AlaBricks.sharedUserId,""));
        super.onResume();
    }
}
