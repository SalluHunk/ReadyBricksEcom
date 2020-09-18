package com.ananta.myapplication.transporter;

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
import com.ananta.myapplication.manufacturer.InventoryActivity;
import com.ananta.myapplication.manufacturer.ManufacturerDashboard;
import com.ananta.myapplication.manufacturer.ManufacturerProduct;
import com.ananta.myapplication.manufacturer.ManufacturerSettings;

import java.sql.Driver;

import presenter.TruckListPresenter;

public class TransporterDashboard extends AppCompatActivity {
    private TextView txtDrivers,txtState,txtTruck,txtOrder;
    private Typeface typefaceBold,typefaceRegular;
    private CardView cardStatistic,cardDrivers,cardOrders,cardTrucks;
    private ImageView imgSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_dashboard);

        txtState = (TextView)findViewById(R.id.txtState);
        txtTruck = (TextView)findViewById(R.id.txtTruck);
        txtOrder = (TextView)findViewById(R.id.txtOrder);
        txtDrivers = (TextView)findViewById(R.id.txtDrivers);
        imgSettings = (ImageView)findViewById(R.id.imgSettings);

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");


        txtTruck.setTypeface(typefaceRegular);
        txtOrder.setTypeface(typefaceRegular);
        txtState.setTypeface(typefaceRegular);
        txtDrivers.setTypeface(typefaceRegular);

        cardDrivers = (CardView)findViewById(R.id.cardDrivers);
        cardOrders = (CardView)findViewById(R.id.cardOrders);
        cardTrucks = (CardView)findViewById(R.id.cardTrucks);
        cardStatistic = (CardView)findViewById(R.id.cardStatistic);


        cardDrivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransporterDashboard.this, DriverListActivity.class);
                startActivity(intent);
            }
        });

        cardTrucks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransporterDashboard.this, TruckListActivity.class);
                startActivity(intent);
            }
        });

        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransporterDashboard.this, ManufacturerSettings.class);
                startActivity(intent);
            }
        });

        cardOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransporterDashboard.this, TransportOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}
