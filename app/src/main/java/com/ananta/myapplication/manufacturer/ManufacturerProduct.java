package com.ananta.myapplication.manufacturer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.ProductListAdapter;

import java.util.ArrayList;

import impl.ProductListPresenterImpl;
import network.ProductData;
import presenter.ManufacturerListPresenter;
import util.AlaBricks;
import view.ManufacturerListView;

public class ManufacturerProduct extends AppCompatActivity implements ManufacturerListView {

    private ListView lstProducts;
    private ImageView imgAddProduct;
    private ImageView imgBack;
    ManufacturerListPresenter manufacturerListPresenter;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_product);

        progressDialog = new ProgressDialog(ManufacturerProduct.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        initUi();
        clickListener();

    }

    public void initUi()
    {
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        lstProducts = (ListView)findViewById(R.id.lstProducts);
        imgAddProduct = (ImageView)findViewById(R.id.imgAddProduct);
        imgBack = (ImageView)findViewById(R.id.imgBack);
        }
    public void clickListener()
    {

        imgAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManufacturerProduct.this,ManufacturerAddProducts.class);
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
        if(productData.size()>0)
        {
            ProductListAdapter productListAdapter  = new ProductListAdapter(ManufacturerProduct.this,productData);
            lstProducts.setAdapter(productListAdapter);
        }
    }

    @Override
    public void onFailedManufacturerList() {
        progressDialog.dismiss();
    }

    @Override
    protected void onResume() {
        progressDialog.show();
        manufacturerListPresenter = new ProductListPresenterImpl(this);
        manufacturerListPresenter.onLoadProductList(sharedPreferences.getString(AlaBricks.sharedUserId,""));
        super.onResume();
    }
}
