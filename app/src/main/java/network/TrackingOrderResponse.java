package network;

import com.google.gson.annotations.SerializedName;

public class TrackingOrderResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("data")
    public TrackingData trackingData;

    @SerializedName("OdId")
    public String orderId;

    public TrackingOrderResponse(String orderId)
    {
        this.orderId = orderId;
    }

    public int getSuccess() {
        return success;
    }

    public TrackingData getTrackingData() {
        return trackingData;
    }
}
