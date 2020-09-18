package network;

import com.google.gson.annotations.SerializedName;

public class AddOrderResponse {

    @SerializedName("UserId")
    public String userId;

    @SerializedName("IsSameAddress")
    public String isSameAddress;

    @SerializedName("Address")
    public String address;

    @SerializedName("Landmark")
    public String landmark;

    @SerializedName("CountryId")
    public String countryId;

    @SerializedName("StateId")
    public String stateId;

    @SerializedName("CityId")
    public String cityId;

    @SerializedName("DeliveryDate")
    public String deliveryDate;

    @SerializedName("success")
    public int success;

    @SerializedName("order")
    public String orderId;

    @SerializedName("DistrictId")
    public String dId;


    public AddOrderResponse(String userId,String isSameAddress,String address,String landmark,String countryId,String stateId,String cityId,String deliveryDate,String dId)
    {
        this.dId = dId;
        this.userId = userId;
        this.isSameAddress = isSameAddress;
        this.address =address;
        this.landmark = landmark;
        this.countryId = countryId;
        this.stateId = stateId;
        this.cityId = cityId;
        this.deliveryDate = deliveryDate;
    }
    public int getSuccess() {
        return success;
    }

    public String getOrderId() {
        return orderId;
    }
}
