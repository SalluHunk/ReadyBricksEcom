package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DriverListResposne {

    @SerializedName("success")
    public int success;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("data")
    public ArrayList<DriverListData> driverListData;

    public int getSuccess() {
        return success;
    }

    public ArrayList<DriverListData> getDriverListData() {
        return driverListData;
    }

    public DriverListResposne(String userId)
    {
        this.userId = userId;
    }
}
