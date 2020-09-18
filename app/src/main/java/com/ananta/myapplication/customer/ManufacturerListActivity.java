package com.ananta.myapplication.customer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.ManufacturerListAdapter;
import com.tfb.fbtoast.FBToast;

import util.AlaBricks;

public class ManufacturerListActivity extends AppCompatActivity {

    private ListView lstManufacturers;
    private ImageView imgBack;
    private Button btnApply;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturer_list);

        imgBack = (ImageView)findViewById(R.id.imgBack);
        lstManufacturers = (ListView)findViewById(R.id.lstManufacturers);
        btnApply = (Button)findViewById(R.id.btnApply);

        ManufacturerListAdapter manufacturerListAdapter = new ManufacturerListAdapter(ManufacturerListActivity.this);
        lstManufacturers.setAdapter(manufacturerListAdapter);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AlaBricks.manufacturerId.size()>0)
                {
                    AlaBricks.finalManufacturerId.clear();
                    AlaBricks.finalManufacturerId.addAll(AlaBricks.manufacturerId);
                    finish();
                }
                else
                {
                    FBToast.errorToast(ManufacturerListActivity.this,"No Manufacturer Selected",FBToast.LENGTH_LONG);
                }
            }
        });


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
