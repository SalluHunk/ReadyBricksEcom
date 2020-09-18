package network;

import com.google.gson.annotations.SerializedName;

public class AddTruckResponse {

    @SerializedName("TransporterId")
    public String transporterId;

    @SerializedName("RcNo")
    public String RcNo;

    @SerializedName("VNo")
    public String VNo;

    @SerializedName("RcImage")
    public String RcImage;

    @SerializedName("success")
    public int success;

    public AddTruckResponse(String transporterId,String rcNo,String vNo,String rcImage)
    {
        this.RcImage = rcImage;
        this.VNo = vNo;
        this.RcNo = rcNo;
        this.transporterId =transporterId;
    }

    public int getSuccess() {
        return success;
    }
}
