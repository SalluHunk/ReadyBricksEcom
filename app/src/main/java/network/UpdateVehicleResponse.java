package network;

import com.google.gson.annotations.SerializedName;

public class UpdateVehicleResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("VehicleId")
    public String vehicleId;

    @SerializedName("RcNo")
    public String rcNo;

    @SerializedName("VNo")
    public String vNo;

    @SerializedName("RcImage")
    public String rcImage;

    public UpdateVehicleResponse(String vehicleId,String rcNo,String vNo,String rcImage)
    {
        this.vehicleId = vehicleId;
        this.rcNo = rcNo;
        this.vNo = vNo;
        this.rcImage = rcImage;
    }

    public int getSuccess() {
        return success;
    }
}
