package com.ananta.myapplication.general;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.customer.CustomerDashboard;
import com.ananta.myapplication.manufacturer.ManufacturerDashboard;
import com.ananta.myapplication.transporter.TransporterDashboard;

import util.AlaBricks;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=2000;
    private SharedPreferences sharedPreferences;
    boolean isLogin = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        isLogin = checkLogin();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isLogin)
                {
                    if(returnUserType().equals("2"))
                    {
                        Intent intent = new Intent(SplashActivity.this, CustomerDashboard.class);
                        startActivity(intent);
                        finish();
                    }
                    else if(returnUserType().equals("3"))
                    {
                        Intent intent = new Intent(SplashActivity.this, ManufacturerDashboard.class);
                        startActivity(intent);
                        finish();
                    }
                    else if(returnUserType().equals("4"))
                        {
                        Intent intent = new Intent(SplashActivity.this, TransporterDashboard.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra("path","simple");
                        startActivity(intent);
                        finish();
                    }
                }
                else
                {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("path","simple");
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
    public boolean checkLogin()
    {
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        if(sharedPreferences.getString(AlaBricks.sharedUserId,"").equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public String returnUserType()
    {
        String type="";
        type = sharedPreferences.getString(AlaBricks.sharedUserType,"");
        return type;
    }

}
