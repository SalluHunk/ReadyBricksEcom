package com.ananta.myapplication.customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ananta.myapplication.R;
import com.ananta.myapplication.manufacturer.ManufacturerDashboard;
import com.ananta.myapplication.transporter.TransporterDashboard;
import com.bumptech.glide.Glide;

import util.AlaBricks;

public class SingleNotificationActivity extends AppCompatActivity {

    private TextView txtDesc,txtName;
    ImageView imgList;
    private Typeface typefaceReg,typefaceBold;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_notification);
        imgList = (ImageView)findViewById(R.id.imgList);
        txtName = (TextView)findViewById(R.id.txtName);
        txtDesc = (TextView)findViewById(R.id.txtDesc);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
         typefaceReg = Typeface.createFromAsset(getAssets(),"regularfont.otf");
        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");


        txtName.setTypeface(typefaceBold);
        txtDesc.setTypeface(typefaceReg);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        if(AlaBricks.notificationData.getNotificationTitle()!=null)
        {
            txtName.setText(AlaBricks.notificationData.getNotificationTitle());
        }
        if(AlaBricks.notificationData.getNotificationDescription()!=null)
        {
            txtDesc.setText(AlaBricks.notificationData.getNotificationDescription());
        }
        Glide.with(SingleNotificationActivity.this)
                .load(AlaBricks.imagePath+AlaBricks.notificationData.getNotificationImage()).into(imgList);

    }

    @Override
    public void onBackPressed() {
        if(AlaBricks.fromNotify.equals(""))
        {
            finish();
        }
        else
        {
            if(sharedPreferences.getString(AlaBricks.sharedUserType,"").equals("2"))
            {
                Intent intent = new Intent(SingleNotificationActivity.this,CustomerDashboard.class);
                startActivity(intent);
                finish();
            }
            if(sharedPreferences.getString(AlaBricks.sharedUserType,"").equals("3"))
            {
                Intent intent = new Intent(SingleNotificationActivity.this, ManufacturerDashboard.class);
                startActivity(intent);
                finish();
            }

            if(sharedPreferences.getString(AlaBricks.sharedUserType,"").equals("4"))
            {

                Intent intent = new Intent(SingleNotificationActivity.this, TransporterDashboard.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
