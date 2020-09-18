package view;

import java.util.ArrayList;

import network.NotificationData;

public interface NotificationListView {
    void onSuccessNotification(ArrayList<NotificationData> notificationData);
    void onFailedNotification();
}
