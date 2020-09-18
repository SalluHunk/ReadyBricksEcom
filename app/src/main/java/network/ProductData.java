package network;

import com.google.gson.annotations.SerializedName;

public class ProductData {

    @SerializedName("ProductId")
    public String productId;

    @SerializedName("PStock")
    public String productStock;

    @SerializedName("PName")
    public String productName;

    @SerializedName("PImage")
    public String productImage;

    @SerializedName("PMinDeliveryDays")
    public String minDays;

    @SerializedName("PPrice")
    public String productPrice;


    @SerializedName("PDescription")
    public String productDescription;

    @SerializedName("PAdditionalInfo")
    public String productAdditionalInfo;

    @SerializedName("PStatus")
    public String productStatus;

    @SerializedName("IsAccount")
    public String productAccount;

    @SerializedName("Reason")
    public String reason;

    @SerializedName("IsEdited")
    public String productEdit;


    public String getProductPrice() {
        return productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductAdditionalInfo() {
        return productAdditionalInfo;
    }

    public String getMinDays() {
        return minDays;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductStock() {
        return productStock;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public String getProductAccount() {
        return productAccount;
    }

    public String getProductEdit() {
        return productEdit;
    }

    public String getReason() {
        return reason;
    }

}
