package network;

import com.google.gson.annotations.SerializedName;

public class CartData {

    @SerializedName("CartId")
    public String cartId;

    @SerializedName("ProductId")
    public String productId;

    @SerializedName("CQty")
    public String qty;

    @SerializedName("CPrice")
    public String price;

    @SerializedName("PName")
    public String productName;

    @SerializedName("PImage")
    public String prodcutImage;

    @SerializedName("PMinDeliveryDays")
    public String minDeliveryDays;

    @SerializedName("CStatus")
    public String status;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCartId() {
        return cartId;
    }

    public String getMinDeliveryDays() {
        return minDeliveryDays;
    }

    public String getProdcutImage() {
        return prodcutImage;
    }

    public String getQty() {
        return qty;
    }

    public String getStatus() {
        return status;
    }

    public String getPrice() {
        return price;
    }

}
