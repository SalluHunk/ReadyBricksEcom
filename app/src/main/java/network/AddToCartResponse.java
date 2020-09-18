package network;

import com.google.gson.annotations.SerializedName;

public class AddToCartResponse {

    @SerializedName("ProductId")
    public String productId;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("Qty")
    public String qty;

    @SerializedName("IsOffer")
    public String offer;

    @SerializedName("Price")
    public String price;

    @SerializedName("success")
    public int success;


    public AddToCartResponse(String productId,String userId,String qty,String price,String offer)
    {
        this.productId = productId;
        this.userId = userId;
        this.qty = qty;
        this.price = price;
        this.offer = offer;
    }

    public int getSuccess() {
        return success;
    }
}
