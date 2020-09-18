package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomerProductData {

    @SerializedName("ProductId")
    public String productId;

    @SerializedName("PManuId")
    public String productManufacturerId;

    @SerializedName("CompanyName")
    public String companyName;

    @SerializedName("PName")
    public String productName;

    @SerializedName("PImage")
    public String productImage;

    @SerializedName("OPrice")
    public String offerPrice;

    @SerializedName("PReviewAvg")
    public String avgRating;

    @SerializedName("PMinDeliveryDays")
    public String productMinDeliveryDays;

    @SerializedName("PPrice")
    public String productPrice;

    @SerializedName("PDescription")
    public String productDescription;

    @SerializedName("PAdditionalInfo")
    public String productAdditionalInfo;

    @SerializedName("PStatus")
    public String productStatus;

    @SerializedName("ReviewList")
    public ArrayList<ReviewData> reviewData;


    public ArrayList<ReviewData> getReviewData() {
        return reviewData;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductAdditionalInfo() {
        return productAdditionalInfo;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductManufacturerId() {
        return productManufacturerId;
    }

    public String getProductMinDeliveryDays() {
        return productMinDeliveryDays;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public String getAvgRating() {
        return avgRating;
    }
}
