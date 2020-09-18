package network;

import com.google.gson.annotations.SerializedName;

public class AddProductResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("UserId")
    public String UserId;

    @SerializedName("Name")
    public String Name;

    @SerializedName("MinDeliveryDays")
    public String MinDeliveryDays;

    @SerializedName("Price")
    public String Price;

    @SerializedName("Description")
    public String Description;

    @SerializedName("AdditionalInfo")
    public String AdditionalInfo;

    @SerializedName("Image")
    public String Image;

    public AddProductResponse(String userId,String Name,String Desc,String AdditionalInfo,String price,String Avail,String image)
    {
        this.UserId = userId;
        this.Name= Name;
        this.Description = Desc;
        this.AdditionalInfo = AdditionalInfo;
        this.Price = price;
        this.MinDeliveryDays = Avail;
        this.Image = image;
    }


    public int getSuccess() {
        return success;
    }
}
