package com.ananta.myapplication.manufacturer;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ananta.myapplication.R;

public class ManufacturerDashboard extends AppCompatActivity {
    private TextView txtProduct,txtState,txtInventory,txtOrder;
    private Typeface typefaceBold,typefaceRegular;
    private CardView cardStatistic,cardProducts,cardOrders,cardInventory;
    private ImageView imgSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_dashboard);

        txtState = (TextView)findViewById(R.id.txtState);
        txtInventory = (TextView)findViewById(R.id.txtInventory);
        txtOrder = (TextView)findViewById(R.id.txtOrder);
        txtProduct = (TextView)findViewById(R.id.txtProduct);
        imgSettings = (ImageView)findViewById(R.id.imgSettings);

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");


        txtInventory.setTypeface(typefaceRegular);
        txtOrder.setTypeface(typefaceRegular);
        txtState.setTypeface(typefaceRegular);
        txtProduct.setTypeface(typefaceRegular);

        cardProducts = (CardView)findViewById(R.id.cardProducts);
        cardOrders = (CardView)findViewById(R.id.cardOrders);
        cardInventory = (CardView)findViewById(R.id.cardInventory);
        cardStatistic = (CardView)findViewById(R.id.cardStatistic);


        cardProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManufacturerDashboard.this,ManufacturerProduct.class);
                startActivity(intent);
            }
        });

        cardInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManufacturerDashboard.this,InventoryActivity.class);
                startActivity(intent);
            }
        });

        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManufacturerDashboard.this,ManufacturerSettings.class);
                startActivity(intent);
            }
        });

        cardOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManufacturerDashboard.this,OrderActivity.class);
                startActivity(intent);
            }
        });
    }
}
