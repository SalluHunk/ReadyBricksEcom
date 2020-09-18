package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductListResponse {



    @SerializedName("success")
    public int success;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("data")
    ArrayList<ProductData> productData;

    public ProductListResponse(String userId)
    {
        this.userId = userId;
    }

    public int getSuccess() {
        return success;
    }

    public ArrayList<ProductData> getProductData() {
        return productData;
    }
}
