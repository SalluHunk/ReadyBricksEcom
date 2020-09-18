package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TransporterOrderListResponse {

    @SerializedName("UserId")
    public String userId;

    @SerializedName("Status")
    public String status;

    @SerializedName("success")
    public int success;

    @SerializedName("data")
    ArrayList<OrderData> orderData;

    public TransporterOrderListResponse(String userId,String status)
    {
        this.userId = userId;
        this.status = status;
    }

    public int getSuccess() {
        return success;
    }

    public ArrayList<OrderData> getOrderData() {
        return orderData;
    }
}
