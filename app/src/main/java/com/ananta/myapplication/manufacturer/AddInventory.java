package com.ananta.myapplication.manufacturer;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.general.SignupThreeActivity;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;
import impl.AddInventoryPresenterImpl;
import impl.ProductListPresenterImpl;
import network.ProductData;
import presenter.AddInventoryPresenter;
import presenter.ManufacturerListPresenter;
import util.AlaBricks;
import view.AddInventoryView;
import view.ManufacturerListView;

public class AddInventory extends AppCompatActivity implements ManufacturerListView, AddInventoryView {
    private ImageView imgBack;
    private MaterialSpinner spiProductList;
    private MaterialEditText edInventory;
    private LoadingButton btnProceed;
    ArrayList<String> productName = new ArrayList<>();
    ArrayList<ProductData> productData = new ArrayList<>();
    String currentProductId="";
    AddInventoryPresenter addInventoryPresenter;
    ManufacturerListPresenter manufacturerListPresenter;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);

        progressDialog = new ProgressDialog(AddInventory.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        spiProductList = (MaterialSpinner)findViewById(R.id.spiProductList);
        edInventory = (MaterialEditText)findViewById(R.id.edInventory);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);
        imgBack = (ImageView)findViewById(R.id.imgBack);

        listeners();
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);

        progressDialog.show();
        manufacturerListPresenter = new ProductListPresenterImpl(this);
        manufacturerListPresenter.onLoadProductList(sharedPreferences.getString(AlaBricks.sharedUserId,""));

        addInventoryPresenter = new AddInventoryPresenterImpl(this);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void listeners()
    {

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentProductId.equals(""))
                {
                    Toast.makeText(AddInventory.this, "Please Select Product", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!AlaBricks.validateBlankValidation(edInventory))
                    {
                        Toast.makeText(AddInventory.this, "Please Add Inventory", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.show();
                        addInventoryPresenter.addStock(currentProductId,AlaBricks.getStringFromEditText(edInventory));
                    }
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        spiProductList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0)
                {
                    Toast.makeText(AddInventory.this, ""+productData.get(position).getProductId(), Toast.LENGTH_SHORT).show();
                    currentProductId =productData.get(position).getProductId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    @Override
    public void onSuccessManufacturerList(ArrayList<ProductData> productData) {
        progressDialog.dismiss();
        this.productData = productData;
        if(productData.size()>0)
        {
            for (int i=0;i<productData.size();i++)
            {
                productName.add(productData.get(i).getProductName());
            }
        }
        if(productName.size()>0)
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddInventory.this, android.R.layout.simple_spinner_item,productName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spiProductList.setAdapter(adapter);
        }

    }

    @Override
    public void onFailedManufacturerList() {
        progressDialog.dismiss();
        FBToast.errorToast(AddInventory.this,"No Products",FBToast.LENGTH_LONG);
    }

    @Override
    public void onSuccessAddInventory() {
        progressDialog.dismiss();
        Toast.makeText(this, "Inventory Updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailedAddInventory() {
        progressDialog.dismiss();
    }
}
