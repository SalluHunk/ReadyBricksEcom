package com.ananta.myapplication.customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.NotificationAdapter;

import java.util.ArrayList;

import impl.NotificationListPresenterImpl;
import network.NotificationData;
import presenter.NotificationListPresenter;
import util.AlaBricks;
import view.NotificationListView;

public class NotificationListActivity extends AppCompatActivity implements NotificationListView {

    private ListView lstNotification;
    NotificationListPresenter notificationListPresenter;
    SharedPreferences sharedPreferences;
    private ImageView imgBack;
    ArrayList<NotificationData> notificationData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        lstNotification = (ListView)findViewById(R.id.lstNotification);

        imgBack = (ImageView)findViewById(R.id.imgBack);
        lstNotification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NotificationListActivity.this,SingleNotificationActivity.class);
                AlaBricks.notificationData = notificationData.get(i);
                startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        notificationListPresenter = new NotificationListPresenterImpl(this);
        notificationListPresenter.listNotification(sharedPreferences.getString(AlaBricks.sharedUserType,""));
    }

    @Override
    public void onSuccessNotification(ArrayList<NotificationData> notificationData) {
        this.notificationData = notificationData;
        lstNotification.setAdapter(new NotificationAdapter(NotificationListActivity.this,notificationData));
    }

    @Override
    public void onFailedNotification() {

    }
}
