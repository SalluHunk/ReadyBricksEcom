package network;

import com.google.gson.annotations.SerializedName;

public class VehicleDriverResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("data")
    public VehicleAndDriverData vehicleAndDriverData;

    public VehicleDriverResponse(String userId)
    {
        this.userId = userId;
    }

    public int getSuccess() {
        return success;
    }

    public VehicleAndDriverData getVehicleAndDriverData() {
        return vehicleAndDriverData;
    }
}
