package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ManuOrderListResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("data")
    public ArrayList<OrderData> orderData;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("Status")
    public String status;

    public ManuOrderListResponse(String userId,String status)
    {
        this.userId = userId;
        this.status = status;
    }

    public ArrayList<OrderData> getOrderData() {
        return orderData;
    }

    public int getSuccess() {
        return success;
    }
}
