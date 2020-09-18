package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NotificationListResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("data")
    public ArrayList<NotificationData> notificationData;

    @SerializedName("RoleId")
    public String roleId;

    public NotificationListResponse(String roleId)
    {
        this.roleId = roleId;
    }

    public ArrayList<NotificationData> getNotificationData() {
        return notificationData;
    }

    public int getSuccess() {
        return success;
    }
}
