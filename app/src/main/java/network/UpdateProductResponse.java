package network;

import com.google.gson.annotations.SerializedName;

public class UpdateProductResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("ProductId")
    public String productId;

    @SerializedName("Name")
    public String name;

    @SerializedName("MinDeliveryDays")
    public String minDeliveryDays;

    @SerializedName("Price")
    public String price;

    @SerializedName("Description")
    public String description;

    @SerializedName("AdditionalInfo")
    public String additionalInfo;

    @SerializedName("Image")
    public String image;

    @SerializedName("IsImage")
    public String isImage;

    public UpdateProductResponse(String productId,String name,String minDeliveryDays,String price,String description,String additionalInfo,String image,String IsImage)
    {
        this.productId = productId;
        this.name = name;
        this.minDeliveryDays = minDeliveryDays;
        this.price = price;
        this.description = description;
        this.additionalInfo = additionalInfo;
        this.image = image;
        this.isImage = IsImage;
    }

    public int getSuccess() {
        return success;
    }
}
