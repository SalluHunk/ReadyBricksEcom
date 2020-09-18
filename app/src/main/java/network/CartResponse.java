package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CartResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("data")
    public ArrayList<CartData> cartData;

    @SerializedName("UserId")
    public String userId;

    public CartResponse(String userId)
    {
        this.userId = userId;
    }

    public int getSuccess() {
        return success;
    }

    public ArrayList<CartData> getCartData() {
        return cartData;
    }

}
