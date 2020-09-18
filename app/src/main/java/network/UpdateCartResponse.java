package network;

import com.google.gson.annotations.SerializedName;

public class UpdateCartResponse {

    @SerializedName("CartId")
    public String cartId;

    @SerializedName("Qty")
    public String qty;

    @SerializedName("success")
    public int success;

    public UpdateCartResponse(String cartId,String qty)
    {
        this.cartId = cartId;
        this.qty = qty;
    }
    public int getSuccess() {
        return success;
    }
}
