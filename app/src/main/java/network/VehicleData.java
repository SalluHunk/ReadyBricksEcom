package network;

import com.google.gson.annotations.SerializedName;

public class VehicleData {

    @SerializedName("VId")
    public String vehicleId;

    @SerializedName("VNo")
    public String vehicleNo;

    public String getVehicleId() {
        return vehicleId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }
}
