package network;

import com.google.gson.annotations.SerializedName;

public class AddInventoryResponse {

    @SerializedName("ProductId")
    public String productId;

    @SerializedName("Stock")
    public String stock;

    @SerializedName("success")
    public int successs;

    public AddInventoryResponse(String productId,String stock)
    {
        this.productId = productId;
        this.stock = stock;
    }

    public int getStock() {
        return successs;
    }
}
