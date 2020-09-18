package network;

import com.google.gson.annotations.SerializedName;

public class DeleteCartResponse {

    @SerializedName("CartId")
    public String cartId;

    @SerializedName("success")
    public int success;

    public DeleteCartResponse(String cartId)
    {
        this.cartId = cartId;
    }

    public int getSuccess() {
        return success;
    }
}
