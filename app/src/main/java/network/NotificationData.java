package network;

import com.google.gson.annotations.SerializedName;

public class NotificationData {

    @SerializedName("NId")
    public String notificationId;

    @SerializedName("NTitle")
    public String notificationTitle;

    @SerializedName("NDescription")
    public String notificationDescription;

    @SerializedName("NImage")
    public String notificationImage;

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public String getNotificationImage() {
        return notificationImage;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }
}
