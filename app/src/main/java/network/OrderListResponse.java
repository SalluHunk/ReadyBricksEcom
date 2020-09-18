package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderListResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("Status")
    public String status;

    @SerializedName("data")
    public ArrayList<OrderData> orderData;

    public OrderListResponse (String userId,String status)
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
