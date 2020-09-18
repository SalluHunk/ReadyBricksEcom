package network;

import com.google.gson.annotations.SerializedName;

public class VehicleListData {

    @SerializedName("VNo")
    public String vehicleNo;

    @SerializedName("VRcNo")
    public String vehicleRcNo;

    @SerializedName("VId")
    public String vehicleId;

    @SerializedName("VRcImage")
    public String rcImage;

    @SerializedName("IsEdited")
    public String isEdit;

    @SerializedName("VStatus")
    public String status;

    @SerializedName("IsAccount")
    public String isAccount;

    @SerializedName("Reason")
    public String reason;

    public String getReason() {
        return reason;
    }

    public String getIsAccount() {
        return isAccount;
    }
    public String getStatus() {
        return status;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public String getRcImage() {
        return rcImage;
    }

    public String getVehicleRcNo() {
        return vehicleRcNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
