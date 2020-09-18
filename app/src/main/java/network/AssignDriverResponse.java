package network;

import com.google.gson.annotations.SerializedName;

public class AssignDriverResponse {

    @SerializedName("OdId")
    public String orderId;

    @SerializedName("VehicleId")
    public String vehicleId;

    @SerializedName("DriverId")
    public String driverId;

    @SerializedName("success")
    public int success;

    public AssignDriverResponse(String orderId,String vehicleId,String driverId)
    {
        this.driverId = driverId;
        this.orderId = orderId;
        this.vehicleId = vehicleId;
    }

    public int getSuccess() {
        return success;
    }
}
