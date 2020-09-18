package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TruckListResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("data")
    public ArrayList<VehicleListData> vehicleListData;

    @SerializedName("UserId")
    public String userId;


    public TruckListResponse(String userId)
    {
        this.userId = userId;
    }

    public int getSuccess() {
        return success;
    }

    public ArrayList<VehicleListData> getVehicleListData() {
        return vehicleListData;
    }
}
